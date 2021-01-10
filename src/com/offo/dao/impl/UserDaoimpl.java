package com.offo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.offo.dao.UserDao;
import com.offo.entity.Users;

public class UserDaoimpl implements UserDao {
QueryRunner qr=new QueryRunner(new ComboPooledDataSource());
	public Users checkUserName(String username) {
		String sql="SELECT * FROM users WHERE username=?";
		 try {
			 return qr.query(sql, new BeanHandler<Users>(Users.class), username);
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 return null;
	}
	@Override
	public Users checkEmailName(String email) {
			String sql="SELECT * FROM users WHERE email=?";
			try {
				return qr.query(sql,new BeanHandler<Users>(Users.class),email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}
	@Override
	public int addUser(Users user) {
		String sql="INSERT INTO users(NAME,email,STATUS,username,PASSWORD,modifytime)VALUES(?,?,?,?,?,?)";
		try {
			 return  qr.update(sql, user.getName(),user.getEmail(),user.getStatus(),
					user.getUsername(),user.getPassword(),user.getModifytime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	//用户登录
	public Users checkLogin(String username, String password) {
		String sql="SELECT * FROM users WHERE username=? AND PASSWORD=? AND STATUS=1";
		 try {
			return  qr.query(sql, new  BeanHandler<Users>(Users.class), username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	//查询记录的总条数
}
