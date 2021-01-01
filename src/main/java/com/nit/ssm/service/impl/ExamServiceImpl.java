package com.nit.ssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.*;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {
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
        UserDTO userDTO = userMapper.getByUserId(userId);
        String examSn = userDTO.getUserName() + System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            GarbageDTO garbageDTO;
            ExamDTO examDTO = new ExamDTO();
            do {
                garbageDTO = garbageMapper.getRandom();
            } while (usedMap.containsKey(garbageDTO.getGarbageId()));
            usedMap.put(garbageDTO.getGarbageId(), true);
            examDTO.setKey( (i + 1));
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
    @Transactional(rollbackFor = Exception.class)
    public OpResultDTO addList(List<ExamDTO> examDTOS,Integer userId) throws Exception {
        OpResultDTO op = new OpResultDTO();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserEntity userEntity = new UserEntity();
        userEntity.setTotal(0);
        userEntity.setRight(0);
        userEntity.setWrong(0);
        userEntity.setUserId(userId);
        for (ExamDTO examDTO : examDTOS) {
            GarbageDTO garbageDTO = garbageMapper.getGarbageById(examDTO.getGarbageId());
            examDTO.setSortId(garbageDTO.getSortId());
            userEntity.setTotal(userEntity.getTotal() + 1);
            GarbageEntity garbageEntity = new GarbageEntity();
            garbageEntity.setTotal(1);
            garbageEntity.setRight(0);
            garbageEntity.setWrong(0);
            garbageEntity.setGarbageId(examDTO.getGarbageId());
            if (examDTO.getAnswerId() != null) {
                if (examDTO.getAnswerId().equals(examDTO.getSortId())) {
                    userEntity.setRight(userEntity.getRight() + 1);
                    garbageEntity.setRight(1);
                    examDTO.setAnswerState(1);
                } else {
                    userEntity.setWrong(userEntity.getWrong() + 1);
                    garbageEntity.setWrong(1);
                    examDTO.setAnswerState(2);
                }
            } else {
                examDTO.setAnswerState(0);
            }
            garbageMapper.updateData(garbageEntity);
            examDTO.setGmtCreate(new Date(System.currentTimeMillis()));
            ExamEntity examEntity = mapperFactory.getMapperFacade().map(examDTO, ExamEntity.class);
            examMapper.add(examEntity);
        }
        userMapper.updateData(userEntity);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", examDTOS);
        jsonObject.put("total", userEntity.getTotal());
        jsonObject.put("right", userEntity.getRight());
        jsonObject.put("wrong", userEntity.getWrong());
        op.setResult(jsonObject);
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

    @Override
    public TableRspDTO listUserTable(TableReqDTO tableReqDTO, Integer userId) throws Exception {
        String examSn = tableReqDTO.parseQueryParam("examSn");
        String answerState = tableReqDTO.parseQueryParam("answerState");
        Long count = examMapper.countUserTable(
                userId,
                answerState,
                tableReqDTO.parseQueryParam("garbageName"),
                examSn
        );
        PagingDTO pagingDTO = new PagingDTO(
                tableReqDTO.getPageNo(),
                tableReqDTO.getPageSize(),
                count,
                examMapper.listUserTable(
                        userId,
                        answerState,
                        tableReqDTO.parseQueryParam("garbageName"),
                        examSn,
                        tableReqDTO.getStart(),
                        tableReqDTO.getPageSize(),
                        tableReqDTO.getSortField(),
                        tableReqDTO.getSortOrder()));
        return new TableRspDTO(pagingDTO);
    }
}
