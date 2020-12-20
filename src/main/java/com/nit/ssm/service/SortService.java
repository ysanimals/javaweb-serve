package com.nit.ssm.service;

import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.dto.TableReqDTO;
import com.nit.ssm.dto.TableRspDTO;


public interface SortService {
    /**
     * @Description: 获取表格数据
     * @Author: SN
     * @Date: 2019/11/31 11:02
     */
    TableRspDTO list4Table(TableReqDTO tableReqDTO) throws Exception;

    /**
     * @Description: 添加记录
     * @Author: SN
     * @Date: 2019/11/31 11:02
     */
    Integer add(SortDTO sortDTO) throws Exception;

    /**
     * @Description: 编辑记录
     * @Author: SN
     * @Date: 2019/11/31 11:02
     */
    Integer edit(SortDTO sortDTO) throws Exception;

    /**
     * @Description: 删除记录
     * @Author: SN
     * @Date: 2019/11/31 11:02
     */
    Integer remove(Integer sortId) throws Exception;
}
