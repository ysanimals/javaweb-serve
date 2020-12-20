package com.nit.ssm.login;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        JSONObject json = JsonReader.receivePost(request);
        //登录成功创建一个Session对象，记录当前连接
        HttpSession session = request.getSession(true);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 今后从数据库中获取验证
        PrintWriter out = response.getWriter();

        Object word = session.getAttribute("randomCode");
        System.out.println(word);
        String real = json.getString("userCode");
        System.out.println(real);
        if (json.getString("userPhone").equals("12345678900") && json.getString("userPwd").equals("nit123456")&&word.equals(real) ) {
            // 登陆成功设置Session，用来记录当前连接
            session.setAttribute("loginInfo", json);
            session.setAttribute("flag","True");
            //System.out.println(session.getAttribute("flag"));
            out.println("{\"resultMsg\":\"Success\"}");

        } else if(json.getString("userPhone").equals("12345678900") && json.getString("userPwd").equals("nit123456")&&!word.equals(real)){
            session.setAttribute("flag","yzmFalse");
            //System.out.println(session.getAttribute("flag"));
            out.println("{\"resultMsg\":\"Yzmfailed\"}");
        }else{
            out.println("{\"resultMsg\":\"Failed\"}");
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}