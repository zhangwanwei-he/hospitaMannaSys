package com.offo.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.offo.entity.Users;
import com.offo.service.UserServer;

@WebServlet("/user")
public class UserServlet extends baseServlet {
	private static final long serialVersionUID = 1L;
	UserServer us=new UserServer();
 //用户名注册验证
	public  void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面传来的用户名
		String username = request.getParameter("username");
		//调用service的方法
		boolean flag=us.checkUserName(username);
		//响应ajax
		response.getWriter().print(flag);
	
	}
//邮箱验证
	public  void checkEmailName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email=request.getParameter("email");
		//调用service的方法
		boolean flag=us.checkEmailName(email);
		//响应ajax
		response.getWriter().print(flag);
	}
	//用户注册
	public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String email = request.getParameter("email");	
		//构建用户对象
		Users user = new Users(name, email, 1, username, Integer.valueOf(password), new Date());
		//调用service层
		boolean flag= us.addUser(user);
		if(flag) {
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("regist.jsp");
		}
	}
	//登录方法
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		//调用service层的方法
		boolean flag = us.checkLogin(username,password,session);
		if(flag) {
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}

		
	}
	//获取验证码
	public void  checkVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取页面输入的验证码
		String verify = request.getParameter("verify");
		//获取seesion域保存验证码
		HttpSession session = request.getSession();
		String verCode = (String) session.getAttribute("verCode");
		if(verCode.equals(verify)) {
			response.getWriter().print(true);
		}else {
			response.getWriter().print(false);
		}
	}
	//强制退出
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取session
		HttpSession session = request.getSession();
		//强制session过期
		session.invalidate();
		//跳转到登录页面
		response.sendRedirect("login.jsp");
	}
}
