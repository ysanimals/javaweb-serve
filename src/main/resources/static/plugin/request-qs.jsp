<%@ page import="org.apache.commons.fileupload.* " %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    // 读取请求内容
    String name = request.getParameter("name");
    String age = request.getParameter("age");
    response.getWriter().println(name);
    response.getWriter().println(age);
%>