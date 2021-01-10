package com.offo.entity;

import java.util.Date;

public class Users {
	private int uid;
	private String name;
	private String email;
	private int status;
	private String username;
	private int password;
	private Date modifytime;
	
	public Users(String name, String email, int status, String username, int password, Date modifytime) {
		super();
		this.name = name;
		this.email = email;
		this.status = status;
		this.username = username;
		this.password = password;
		this.modifytime = modifytime;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	

}
