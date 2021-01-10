package com.offo.service;

import javax.servlet.http.HttpSession;

import com.offo.dao.UserDao;
import com.offo.dao.impl.UserDaoimpl;
import com.offo.entity.Users;

public class UserServer {
	UserDao  ud=new UserDaoimpl();
	public boolean checkUserName(String username) {
		return ud.checkUserName(username)!=null?true:false;
	}
	public boolean checkEmailName(String email) {
		return ud.checkEmailName(email)!=null?true:false;
	}
	public boolean addUser(Users user) {
		return ud.addUser(user)>0?true:false;
	}
	public boolean checkLogin(String username, String password, HttpSession session) {
		Users user=	ud.checkLogin(username,password);
		if(user!=null) {
			//保存前端页面的用户名到session域中
			session.setAttribute("user", user);
			return true;
		}
		return false;
	}
	

}
