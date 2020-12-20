<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    // 读取请求内容
    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
    String line = null;
    StringBuilder sb = new StringBuilder();
    while ((line = br.readLine()) != null) {
        sb.append(line);
    }
    response.getWriter().println(sb.toString());
%>