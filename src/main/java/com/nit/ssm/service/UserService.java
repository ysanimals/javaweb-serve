package com.nit.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.dto.TableReqDTO;
import com.nit.ssm.dto.TableRspDTO;
import com.nit.ssm.dto.UserDTO;
import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.dto.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    /**
     * @Description: 用户登录
     * @Date: 2020/12/22
     */
    OpResultDTO loginCheck(String loginName, String loginPwd, Boolean rememberMe) throws Exception;

    /**
     * @Description: 用户注册
     * @Date: 2020/12/22
     */
    OpResultDTO register(UserDTO userDTO) throws Exception;

    /**
     * @Description: 用户名重名检测
     * @Date: 2020/12/22
     */
    UserDTO checkName(String userName) throws Exception;

    /**
     * @Description: 获取用户信息
     * @Date: 2020/12/24
     */
    OpResultDTO getUserInfo(Integer userId) throws Exception;

    /**
     * @Description: 获取权限列表
     * @Date: 2020/12/27
     */
    List<MenuDTO> queryRoleMenu(Integer roleId) throws Exception;

    /**
     * 查询用户信息封装便于前端展示
     * @return OpResult
     */
    TableRspDTO list4Table(@RequestBody TableReqDTO req) throws Exception;

    /**
     * 修改用户状态
     * @return OpResult
     */
    OpResultDTO update(@RequestBody UserDTO userDTO) throws Exception;
}
