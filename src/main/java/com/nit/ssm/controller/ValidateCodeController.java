package com.nit.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.nit.ssm.dto.OpResultDTO;
import com.nit.ssm.utils.HttpRequestReader;
import com.nit.ssm.utils.ImgCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api/code")
public class ValidateCodeController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/getImgCode", method = RequestMethod.GET)
    public Map<String, String> getImgCode() {
        Map<String, String> result = null;
        try {
            result = ImgCodeUtil.getImgCode();
            String imgCode = result.get("imgCode");
            UUID randomUUID = UUID.randomUUID();
            String imgCodeKey = randomUUID.toString();
            System.out.println("imgCodeKey:" + imgCodeKey);
            // 图片验证码有效时间 ：5 分钟
            redisTemplate.opsForValue().set(imgCodeKey, imgCode, 5, TimeUnit.MINUTES);
            result.put("imgCodeKey", imgCodeKey);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    /**
     * 校验验证码
     * @return 验证结果
     */
    @RequestMapping(value = "/checkImgCode", method = RequestMethod.POST)
    public OpResultDTO checkImgCode(HttpServletRequest request) {
        OpResultDTO op = new OpResultDTO();
        try {
            JSONObject jsonObject = HttpRequestReader.getJsonObject(request);
            String imgCodeKey = jsonObject.getString("imgCodeKey");
            String imgCode = jsonObject.getString("imgCode");
            String cacheCode = redisTemplate.opsForValue().get(imgCodeKey);
            if (null == cacheCode) {
                op.setMessage("error");
                op.setResult("图片验证码已过期，请重新获取");
            } else if (cacheCode.equals(imgCode.toUpperCase())) {
                op.setMessage("yes");
                op.setResult("验证码输入正确");
            } else {
                op.setMessage("error");
                op.setResult("验证码输入错误");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            op.setMessage("error");
            op.setResult("验证码错误");
        }
        return op;
    }


}
