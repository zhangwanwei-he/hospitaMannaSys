package com.offo.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class baseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charater=utf-8");
	//获取页面传来的参数
	String method = request.getParameter("method");
	//获取当前类字节码对象
	Class<? extends baseServlet> clazz = this.getClass();
	//使用字节码对象获取方法
	try {
		Method mz = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
		mz.invoke(this, request,response);
	} catch (Exception e) {
		}
	}
}
