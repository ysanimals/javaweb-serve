package com.nit.ssm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class HttpRequestReader {
    public static JSONObject getJsonObject(HttpServletRequest req) throws Exception {
        ServletInputStream is;
        is = req.getInputStream();
        int nRead = 1;
        int nTotalRead = 0;
        byte[] bytes = new byte[10240];
        while (nRead > 0) {
            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
            if (nRead > 0) {
                nTotalRead = nTotalRead + nRead;
            }
        }
        String str = new String(bytes, 0, nTotalRead, StandardCharsets.UTF_8);
        return JSON.parseObject(str);
    }

    public static String getJsonString(HttpServletRequest req) throws Exception {
        ServletInputStream is;
        is = req.getInputStream();
        int nRead = 1;
        int nTotalRead = 0;
        byte[] bytes = new byte[10240];
        while (nRead > 0) {
            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
            if (nRead > 0) {
                nTotalRead = nTotalRead + nRead;
            }
        }
        return new String(bytes, 0, nTotalRead, StandardCharsets.UTF_8);
    }
}