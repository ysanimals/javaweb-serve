package com.nit.ssm.service;

import com.nit.ssm.dto.ExamDTO;
import com.nit.ssm.dto.GarbageDTO;
import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.dto.UserDTO;

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
    OpResultDTO addList(List<ExamDTO> examDTOS) throws Exception;
}