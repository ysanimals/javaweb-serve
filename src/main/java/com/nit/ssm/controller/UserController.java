package com.nit.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.*;
import com.nit.ssm.service.UserService;
import com.nit.ssm.utils.JWTUtil;
import com.nit.ssm.utils.HttpRequestReader;
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
    public OpResultDTO login(HttpServletRequest request) {
        OpResultDTO op;
        try {
            JSONObject jsonObject = HttpRequestReader.getJsonObject(request);
            String loginName = jsonObject.getString("loginName");
            String loginPwd = jsonObject.getString("loginPwd");
            Boolean rememberMe = jsonObject.getBoolean("rememberMe");
            op = userService.loginCheck(loginName, loginPwd, rememberMe);
        } catch (Exception e) {
            op = new OpResultDTO();
            op.setMessage("error");
            op.setResult("登录失败");
            System.out.println(e.toString());
        }
        return op;
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public OpResultDTO register(@RequestBody UserDTO userDTO) {
        OpResultDTO op;
        try {
            op = userService.register(userDTO);
        } catch (Exception e) {
            op = new OpResultDTO();
            op.setMessage("error");
            op.setResult("注册失败");
            System.out.println(e.toString());
        }
        return op;
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public OpResultDTO checkName(@RequestBody String userName) {
        OpResultDTO op = new OpResultDTO();
        try {
            UserDTO userDTO = userService.checkName(userName);
            if (userDTO == null) {
                op.setMessage("ok");
            } else {
                op.setMessage("error");
                op.setResult("用户名已存在");
            }
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("异常错误");
            System.out.println(e.toString());
        }
        return op;
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
                op.setMessage("error");
                op.setResult("获取用户信息失败，请重新登录");
            } else {
                Integer userId = tokenDTO.getUserId();
                op = userService.getUserInfo(userId);
            }
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("获取用户信息失败");
            System.out.println(e.toString());
        }
        return op;
    }

    /**
     * 查询用户信息封装便于前端展示
     * @return OpResult
     */
    @RequestMapping(value = "/list4Table", method = RequestMethod.POST)
    public TableRspDTO list4Table(@RequestBody TableReqDTO req) {
        TableRspDTO rsp;
        try {
            rsp = userService.list4Table(req);
        } catch (Exception e) {
            rsp = new TableRspDTO();
            System.out.println(e.toString());
        }
        return rsp;
    }

    /**
     * 修改用户状态
     * @return OpResult
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public OpResultDTO update(@RequestBody UserDTO userDTO) {
        OpResultDTO op = new OpResultDTO();
        try {
            op = userService.update(userDTO);
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("修改失败");
        }
        return op;
    }

    /**
     * 查询用户统计信息
     * @return OpResult
     */
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public TableRspDTO statistics(@RequestBody TableReqDTO req) {
        TableRspDTO rsp;
        try {
            rsp = userService.statistics(req);
        } catch (Exception e) {
            rsp = new TableRspDTO();
            System.out.println(e.toString());
        }
        return rsp;
    }
}
