package com.nit.ssm.service;

import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.dto.TableReqDTO;
import com.nit.ssm.dto.TableRspDTO;
import com.nit.ssm.dto.UserDTO;

import javax.servlet.http.HttpSession;

public interface UserService {
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



    /**
     * @Description: 重置用户密码
     * @Author: JYX
     * @Date: 2020/12/14 10:34
     */
    Integer resetPwd(Integer userId, String userPwd) throws Exception;

    /**
     * @Description: 登录验证
     * @Author: JYX
     * @Date: 2020/12/14 10:34
     */
    UserDTO loginCheck(String userName) throws Exception;

    /**
     * @Description: 根据UserId获取用户信息
     * @Author: JYX
     * @Date: 2020/12/14 10:34
     */
    UserDTO getByUserId(Integer userId) throws Exception;


    UserDTO login(UserDTO userDTO, HttpSession session);
}
