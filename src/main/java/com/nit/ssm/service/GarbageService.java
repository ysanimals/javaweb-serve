package com.nit.ssm.service;

import com.nit.ssm.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface GarbageService {

    /**
     * 查询所有垃圾信息
     * @return OpResult
     */
    OpResultDTO listAllGarbage() throws Exception;

    /**
     * 查询垃圾信息封装便于前端展示
     * @return TableRspDTO
     */
    TableRspDTO list4Table(TableReqDTO req) throws Exception;

    /**
     * 批量导入垃圾信息
     * @return OpResult
     */
    OpResultDTO addList(List<GarbageDTO> garbageDTOS) throws Exception;

    /**
     * 添加垃圾信息
     * @return OpResult
     */
    OpResultDTO add(GarbageDTO garbageDTO) throws Exception;

    /**
     * 修改垃圾信息
     * @return OpResult
     */
    OpResultDTO update(GarbageDTO garbageDTO) throws Exception;

    /**
     * 删除垃圾信息
     * @return OpResult
     */
    OpResultDTO remove(Long garbageId) throws Exception;

    /**
     * 查询一条不带答案的垃圾信息
     * @return GarbageDTO
     */
    GarbageDTO queryOne() throws Exception;

    /**
     * 检查答案是否正确
     * @return OpResult
     */
    OpResultDTO checkOne(GarbageDTO garbageDTO) throws Exception;


}
