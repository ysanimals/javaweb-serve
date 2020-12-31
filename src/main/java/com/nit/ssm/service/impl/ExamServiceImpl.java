package com.nit.ssm.service.impl;

import com.nit.ssm.dto.*;
import com.nit.ssm.entity.ExamEntity;
import com.nit.ssm.mapper.ExamMapper;
import com.nit.ssm.mapper.GarbageMapper;
import com.nit.ssm.mapper.UserMapper;
import com.nit.ssm.service.ExamService;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService{
    @Resource
    private ExamMapper examMapper;
    @Resource
    private GarbageMapper garbageMapper;
    @Resource
    private UserMapper userMapper;
/*123123123*/
    @Override
    public OpResultDTO getList(Integer num, Integer userId) throws Exception {
        OpResultDTO op = new OpResultDTO();
        Map<Integer, Boolean> usedMap = new HashMap<>();
        List<ExamDTO> examDTOS = new ArrayList<>();
        String examSn = UUID.randomUUID().toString();
        for (int i = 0; i < num; i++) {
            GarbageDTO garbageDTO;
            ExamDTO examDTO = new ExamDTO();
            do {
                garbageDTO = garbageMapper.getRandom();
            } while (usedMap.containsKey(garbageDTO.getGarbageId()));
            usedMap.put(garbageDTO.getGarbageId(), true);
            examDTO.setKey((Integer) (i + 1));
            examDTO.setUserId(userId);
            examDTO.setGarbageId(garbageDTO.getGarbageId());
            examDTO.setGarbageName(garbageDTO.getGarbageName());
            examDTO.setExamSn(examSn);
            examDTO.setImageUrl(garbageDTO.getImageUrl());
            examDTOS.add(examDTO);
        }
        op.setResult(examDTOS);
        return op;
    }


    @Override
    public OpResultDTO addList(List<ExamDTO> examDTOS, Integer userId) throws Exception {
        OpResultDTO op = new OpResultDTO();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        for(ExamDTO examDTO : examDTOS){
            GarbageDTO garbageDTO = garbageMapper.getGarbageById(examDTO.getGarbageId());
            examDTO.setSortId(garbageDTO.getSortId());
            examDTO.setGmtCreate(new Date(System.currentTimeMillis()));
            ExamEntity examEntity = mapperFactory.getMapperFacade().map(examDTO, ExamEntity.class);
            examMapper.add(examEntity);
        }
        op.setResult(examDTOS);
        return op;
    }

    @Override
    public TableRspDTO list4Table(TableReqDTO tableReqDTO) throws Exception {
        String examSn = tableReqDTO.parseQueryParam("examSn");
        Long count = examMapper.count4Table(
                tableReqDTO.parseQueryParam("userName"),
                tableReqDTO.parseQueryParam("garbageName"),
                examSn
        );
        PagingDTO pagingDTO = new PagingDTO(
                tableReqDTO.getPageNo(),
                tableReqDTO.getPageSize(),
                count,
                examMapper.list4Table(
                        tableReqDTO.parseQueryParam("userName"),
                        tableReqDTO.parseQueryParam("garbageName"),
                        examSn,
                        tableReqDTO.getStart(),
                        tableReqDTO.getPageSize(),
                        tableReqDTO.getSortField(),
                        tableReqDTO.getSortOrder()));
        return new TableRspDTO(pagingDTO);
    }
}