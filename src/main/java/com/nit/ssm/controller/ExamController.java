package com.nit.ssm.controller;

import com.nit.ssm.dto.*;
import com.nit.ssm.service.ExamService;
import com.nit.ssm.utils.JWTUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController

@RequestMapping(value = "/api/exam")
public class ExamController {

    @Resource
    private ExamService examService;

    /**
     * 获取题目
     * @return OpResult
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public OpResultDTO getList(@RequestHeader(name = "Access-Token")String token,
                               @RequestBody String numStr) {
        OpResultDTO op = new OpResultDTO();
        try {
            Integer num = Integer.valueOf(numStr);
            TokenDTO tokenDTO = JWTUtil.verifyToken(token);
            if (tokenDTO == null) {
                op.setMessage("error");
                op.setResult("获取用户信息失败，请重新登录");
            } else {
                op = examService.getList(num, tokenDTO.getUserId());
            }
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("获取失败");
        }
        return op;
    }

    /**
     * 批量导入回答信息
     * @return OpResult
     */
    @RequestMapping(value = "/addList",method = RequestMethod.POST)
    public OpResultDTO addList(@RequestHeader(name = "Access-Token")String token,
                               @RequestBody List<ExamDTO> examDTOS){
        OpResultDTO op = new OpResultDTO();
        try{
            TokenDTO tokenDTO = JWTUtil.verifyToken(token);
            if(tokenDTO == null){
                op.setMessage("error");
                op.setResult("获取用户信息失败，请重新登陆");
            }
            else{
                op = examService.addList(examDTOS, tokenDTO.getUserId());
            }
        } catch (Exception e) {
            op.setMessage("error");
            op.setResult("上传失败");
        }
        return op;
    }
}
