package com.nit.ssm.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 取得根目录所对应的绝对路径
        String currentURL = request.getRequestURI();
        String targetURL = currentURL.substring(currentURL.lastIndexOf("/"), currentURL.length());
        System.out.println("过滤器######################>" + targetURL);
        HttpSession session = request.getSession(false);
        // 排除登录页面，进行Session验证
        if ("/LoginServlet".equals(targetURL)) {
            System.out.println("过滤器**********************>不进行Session验证");
        } else {

            System.out.println("过滤器**********************>Session验证：" + session);
        }
        chain.doFilter(req, resp);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
