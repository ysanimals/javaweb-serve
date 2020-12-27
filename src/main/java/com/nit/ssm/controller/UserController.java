package com.nit.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.UserDTO;
import com.nit.ssm.dto.TokenDTO;
import com.nit.ssm.service.UserService;
import com.nit.ssm.utils.JWTUtil;
import com.nit.ssm.utils.HttpRequestReader;
import com.nit.ssm.dto.OpResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(HttpServletRequest request) {
        JSONObject json;
        try {
            JSONObject jsonObject = HttpRequestReader.getJsonObject(request);
            String loginName = jsonObject.getString("loginName");
            String loginPwd = jsonObject.getString("loginPwd");
            Boolean rememberMe = jsonObject.getBoolean("rememberMe");
            json = userService.loginCheck(loginName, loginPwd, rememberMe);
        } catch (Exception e) {
            json = new JSONObject();
            System.out.println(e.toString());
        }
        return json;
    }
    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public OpResultDTO userInfo(@RequestHeader(name = "Access-Token")String token) {
        OpResultDTO op = new OpResultDTO();
        try {
            TokenDTO tokenDTO = JWTUtil.verifyToken(token);
            if (token == null) {
                op.setIntResult(200);
                op.setObjResult("获取用户信息失败，请重新登录");
            } else {
                Integer userId = tokenDTO.getUserId();
                op = userService.getUserInfo(userId);
            }
        } catch (Exception e) {
            op.setIntResult(200);
            op.setObjResult("获取用户信息失败");
            System.out.println(e.toString());
        }
        return op;
    }
}
