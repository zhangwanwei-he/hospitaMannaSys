package com.offo.dao;

import com.offo.entity.Users;

public interface UserDao {
	//用户验证
	Users checkUserName(String username);
	//邮箱验证
	Users checkEmailName(String email);
	//添加用户信息
	int addUser(Users user);
	
	Users checkLogin(String username, String password);

}
