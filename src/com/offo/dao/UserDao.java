package com.offo.dao;

import com.offo.entity.Users;

public interface UserDao {
	//�û���֤
	Users checkUserName(String username);
	//������֤
	Users checkEmailName(String email);
	//����û���Ϣ
	int addUser(Users user);
	
	Users checkLogin(String username, String password);

}
