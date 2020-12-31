package com.nit.ssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.*;
import com.nit.ssm.entity.SortEntity;
import com.nit.ssm.entity.UserEntity;
import com.nit.ssm.mapper.UserMapper;
import com.nit.ssm.service.UserService;
import com.nit.ssm.utils.JWTUtil;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public OpResultDTO loginCheck(String loginName, String loginPwd, Boolean rememberMe) throws Exception {
        OpResultDTO op = new OpResultDTO();
        UserDTO userDTO = userMapper.getByUserName(loginName);
        if (userDTO == null || !userDTO.getUserPwd().equals(loginPwd)) {
            op.setMessage("error");
            op.setResult("账户或密码错误");
        } else {
            if (userDTO.getUserType() != 1) {
                op.setMessage("error");
                op.setResult(userDTO.getUserType() == 0? "注册审核中，请耐心等待": "注册未通过，请联系管理员");
            } else {
                TokenDTO tokenDTO = new TokenDTO(userDTO.getUserId(), loginName, userDTO.getRoleId());
                String token;
                if (rememberMe) {
                    token = JWTUtil.createSign(tokenDTO.toString(), 20160);
                } else {
                    token = JWTUtil.createSign(tokenDTO.toString(), 240);
                }
                AccountDTO accountDTO = new AccountDTO(
                        userDTO.getUserId(),
                        userDTO.getUserName(),
                        userDTO.getRoleId(),
                        token,
                        queryRoleMenu(userDTO.getRoleId()));
                op.setMessage("success");
                op.setResult(accountDTO);
            }
        }
        return op;
    }

    @Override
    public OpResultDTO register(UserDTO userDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        OpResultDTO op = new OpResultDTO();
        UserEntity userEntity = mapperFactory.getMapperFacade().map(userDTO, UserEntity.class);
        userEntity.setRoleId(2);
        userEntity.setUserType(0);
        userEntity.setTotal(0);
        userEntity.setRight(0);
        userEntity.setWrong(0);
        userEntity.setGmtCreate(new Date(System.currentTimeMillis()));
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

    @Override
    public List<MenuDTO> queryRoleMenu(Integer roleId) throws Exception {
        List<MenuDTO> menuDTOS = userMapper.queryRoleMenu(roleId);
        List<MenuDTO> router = new ArrayList<>();
        menuDTOS.forEach(p -> {
            if (p.getLevelType() == 1) {
                List<MenuDTO> children = new ArrayList<>();
                p.setChildren(children);
                router.add(p);
            }
        });
        router.forEach(p -> {
            for (MenuDTO m : menuDTOS) {
                if (m.getLevelType() == 2 && m.getFatherId().equals(p.getMenuId())) {
                    List<MenuDTO> children = new ArrayList<>();
                    m.setChildren(children);
                    p.getChildren().add(m);
                    for (MenuDTO n : menuDTOS) {
                        if (n.getLevelType() == 3 && n.getFatherId().equals(m.getMenuId())) {
                            m.getChildren().add(n);
                        }
                    }
                }
            }
        });
        return router;
    }

    @Override
    public TableRspDTO list4Table(@RequestBody TableReqDTO req) throws Exception {
        String userName = req.parseQueryParam("userName");
        String userType = req.parseQueryParam("userType");
        String phone = req.parseQueryParam("userPhone");
        Long count = userMapper.count4Table(
                userName,
                userType,
                phone);
        PagingDTO pagingDTO = new PagingDTO(
                req.getPageNo(),
                req.getPageSize(),
                count,
                userMapper.list4Table(
                        userName,
                        userType,
                        phone,
                        req.getStart(),
                        req.getPageSize(),
                        req.getSortField(),
                        req.getSortOrder()));
        return new TableRspDTO(pagingDTO);
    }

    @Override
    public TableRspDTO statistics(@RequestBody TableReqDTO req) throws Exception {
        String userName = req.parseQueryParam("userName");
        String phone = req.parseQueryParam("userPhone");
        Long count = userMapper.countStatistics(
                userName,
                phone);
        List<UserDTO> userDTOS = userMapper.statistics(
                userName,
                phone,
                req.getStart(),
                req.getPageSize(),
                req.getSortField(),
                req.getSortOrder());
        PagingDTO pagingDTO = new PagingDTO(
                req.getPageNo(),
                req.getPageSize(),
                count,
                userDTOS);
        return new TableRspDTO(pagingDTO);
    }


    @Override
    public OpResultDTO update(@RequestBody UserDTO userDTO) throws Exception {
        OpResultDTO op = new OpResultDTO();
        op.setResult(userMapper.updateUserType(userDTO.getUserId(), userDTO.getUserType()));
        return op;
    }
}
