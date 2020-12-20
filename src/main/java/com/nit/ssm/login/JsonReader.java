package com.nit.ssm.login;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class JsonReader {
    public static JSONObject receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //将json字符串转换为json对象
        JSONObject json = JSONObject.parseObject(sb.toString());
        return json;
    }
}