package com.nit.ssm.service;

import com.nit.ssm.dto.*;

import java.util.List;
public interface ExamService{
    /**
     * 添加回答信息
     * @return OpResult
     */
    OpResultDTO getList(Integer num,Integer userId) throws Exception;

    /**
     * 批量导入回答信息
     * @return OpResult
     */
    OpResultDTO addList(List<ExamDTO> examDTOS, Integer userId) throws Exception;

    /**
     * list答题信息
     * @return OpResult
     */
    TableRspDTO list4Table(TableReqDTO tableReqDTO) throws Exception;

}