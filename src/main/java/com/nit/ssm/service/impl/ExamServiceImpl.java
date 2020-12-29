package com.nit.ssm.service.impl;

import com.nit.ssm.dto.ExamDTO;
import com.nit.ssm.dto.GarbageDTO;
import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.dto.UserDTO;
import com.nit.ssm.entity.ExamEntity;
import com.nit.ssm.entity.GarbageEntity;
import com.nit.ssm.entity.UserEntity;
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
    public OpResultDTO addList(List<ExamDTO> examDTOS) throws Exception {
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
}