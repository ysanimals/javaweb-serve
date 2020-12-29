package com.nit.ssm.config;

import com.nit.ssm.dto.TokenDTO;
import com.nit.ssm.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
         * 移动端不进行Token拦截与验证；用户登录不拦截与验证
         */
        System.out.print(request.getRequestURL());
        //request.getRequestURI().indexOf("1.jsp")的意思就是，求请求的url内“1.jsp”的位置，返回的是一个数字，代表出现的位置，-1表示不存在。通常和-1比较来表示是否包含指定的页面，常用于过滤器。
        if (request.getRequestURL().indexOf("/api/user/login") != -1 ||
                request.getRequestURL().indexOf("/api/code") != -1 ||
                request.getRequestURL().indexOf("/api/garbage/queryOne") != -1 ||
                request.getRequestURL().indexOf("/api/garbage/checkOne") != -1) {
            response.setHeader("token_status", "ok");
            return true;
        } else {
            /*
             * 通过客户端传递的Token参数进行验证，注意header中的属性名要小写
             */
            //不放行，进行别的操作
            TokenDTO tokenDTO = JWTUtil.verifyToken(request.getHeader("Access-Token"));
            if (tokenDTO != null && tokenDTO.getUserId() > 0) {
                response.setHeader("token_status", "ok");
                return true;
            } else {
                response.setHeader("token_status", "no");
                return false;
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

