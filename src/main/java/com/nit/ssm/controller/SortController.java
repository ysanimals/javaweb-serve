package com.nit.ssm.controller;

import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.dto.TableReqDTO;
import com.nit.ssm.dto.TableRspDTO;
import com.nit.ssm.service.SortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//用途： 控制层，负责具体模块的业务流程控制，需要调用service逻辑设计层的接口来控制业务流程。因为service中的方法是我们使用到的，controller通过接收前端H5或者App传过来的参数进行业务操作，再将处理结果返回到前端。
@RestController
@RequestMapping(value = "/api/sort")
public class SortController {

    @Resource
    private SortService sortService;
    private final Logger logger = LoggerFactory.getLogger(SortController.class);

    /**
     * @Description: 获取数据列表
     * @Author: SN
     * @Date: 2019/11/30 18:15
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public TableRspDTO list4Table(TableReqDTO tableReqDTO) {
        TableRspDTO tableRspDTO = new TableRspDTO();
        try {
            tableRspDTO = sortService.list4Table(tableReqDTO);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return tableRspDTO;
    }

    /**
     * @Description: 添加记录
     * @Author: SN
     * @Date: 2019/11/31 11:02
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OpResultDTO add(SortDTO sortDTO) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setIntResult(sortService.add(sortDTO));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return opResult;
    }

    /**
     * @Description: 编辑记录
     * @Author: SN
     * @Date: 2019/11/31 11:02
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public OpResultDTO update(SortDTO sortDTO) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setIntResult(sortService.edit(sortDTO));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return opResult;
    }

    /**
     * @Description: 删除记录
     * @Author: SN
     * @Date: 2019/11/31 11:02
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public OpResultDTO remove(Integer sortId) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setIntResult(sortService.remove(sortId));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return opResult;
    }
}
