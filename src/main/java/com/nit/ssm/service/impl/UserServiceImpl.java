package com.nit.ssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.dto.SortDTO;
import com.nit.ssm.dto.UserDTO;
import com.nit.ssm.dto.TokenDTO;

import com.nit.ssm.entity.UserEntity;
import com.nit.ssm.service.UserService;
import com.nit.ssm.mapper.UserMapper;
import com.nit.ssm.utils.JWTUtil;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public JSONObject loginCheck(String loginName, String loginPwd, Boolean rememberMe) throws Exception {
        JSONObject jsonObject = new JSONObject();
        UserDTO userDTO = userMapper.getByUserName(loginName);
        if (userDTO == null || !userDTO.getUserPwd().equals(loginPwd)) {
            jsonObject.put("message", "账户或密码错误");
            jsonObject.put("status", 401);
        } else {
            TokenDTO tokenDTO = new TokenDTO(userDTO.getUserId(), loginName);
            String token;
            if (rememberMe) {
                token = JWTUtil.createSign(tokenDTO.toString(), 20160);
            } else {
                token = JWTUtil.createSign(tokenDTO.toString(), 240);
            }
            jsonObject.put("message", "");
            jsonObject.put("status", 200);
            jsonObject.put("token", token);
        }
        return jsonObject;
    }

    @Override
    public OpResultDTO register(UserDTO userDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        OpResultDTO op = new OpResultDTO();
        UserEntity userEntity = mapperFactory.getMapperFacade().map(userDTO, UserEntity.class);
        op.setResult(userMapper.add(userEntity));
        return op;
    }

    @Override
    public UserDTO checkName(String userName) throws Exception {
        return userMapper.getByUserName(userName);
    }


    @Override
    public OpResultDTO getUserInfo(Integer userId) throws Exception {
        OpResultDTO op = new OpResultDTO();
        UserDTO userDTO = userMapper.getByUserId(userId);
        userDTO.setUserPwd(null);
        userDTO.setUserId(null);
        userDTO.setKey(null);
        op.setResult(userDTO);
        return op;
    }
}
