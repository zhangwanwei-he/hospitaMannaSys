<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.offo.entity.Users" %>

<%
	// 获取session登录信息
	 Users  user = (Users)session.getAttribute("user");
	//判断用户名是否为空
		if(user==null){
		response.sendRedirect("login.jsp");
}
%>
