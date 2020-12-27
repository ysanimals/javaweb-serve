package com.nit.ssm.service;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.dto.TableReqDTO;
import com.nit.ssm.dto.TableRspDTO;
import com.nit.ssm.dto.UserDTO;
import com.nit.ssm.dto.OpResultDTO;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    /**
     * @Description: 用户登录
     * @Date: 2020/12/22
     */
    JSONObject loginCheck(String loginName, String loginPwd, Boolean rememberMe) throws Exception;

    /**
     * @Description: 用户注册
     * @Date: 2020/12/22
     */
    OpResultDTO  register(UserDTO userDTO) throws Exception;

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
}
