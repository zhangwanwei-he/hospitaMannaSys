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
 //�û���ע����֤
	public  void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�洫�����û���
		String username = request.getParameter("username");
		//����service�ķ���
		boolean flag=us.checkUserName(username);
		//��Ӧajax
		response.getWriter().print(flag);
	
	}
//������֤
	public  void checkEmailName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email=request.getParameter("email");
		//����service�ķ���
		boolean flag=us.checkEmailName(email);
		//��Ӧajax
		response.getWriter().print(flag);
	}
	//�û�ע��
	public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String email = request.getParameter("email");	
		//�����û�����
		Users user = new Users(name, email, 1, username, Integer.valueOf(password), new Date());
		//����service��
		boolean flag= us.addUser(user);
		if(flag) {
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("regist.jsp");
		}
	}
	//��¼����
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		//����service��ķ���
		boolean flag = us.checkLogin(username,password,session);
		if(flag) {
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("login.jsp");
		}

		
	}
	//��ȡ��֤��
	public void  checkVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡҳ���������֤��
		String verify = request.getParameter("verify");
		//��ȡseesion�򱣴���֤��
		HttpSession session = request.getSession();
		String verCode = (String) session.getAttribute("verCode");
		if(verCode.equals(verify)) {
			response.getWriter().print(true);
		}else {
			response.getWriter().print(false);
		}
	}
	//ǿ���˳�
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡsession
		HttpSession session = request.getSession();
		//ǿ��session����
		session.invalidate();
		//��ת����¼ҳ��
		response.sendRedirect("login.jsp");
	}
}
