package com.offo.entity;

import java.util.Date;

public class Doctor {
	private int did;
	private String name;
	private String cardno;
	private String phone;
	private int sex;
	private int age;
	private Date birthday;
	private String email;
	private Integer department;
	private int education;
	private String 	remark;
	
	public Doctor() {
		super();
	}
	

	public Doctor(int did, String name, String cardno, String phone, int sex, int age, Date birthday, String email,
			Integer department, int education, String remark) {
		super();
		this.did = did;
		this.name = name;
		this.cardno = cardno;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
		this.email = email;
		this.department = department;
		this.education = education;
		this.remark = remark;
	}


	public Doctor(String name, String cardno, String phone, int sex, int age, Date birthday, String email,
			Integer department, int education, String remark) {
		super();
		this.name = name;
		this.cardno = cardno;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
		this.email = email;
		this.department = department;
		this.education = education;
		this.remark = remark;
	}





	public int getDid() {
		return did;
	}


	public void setDid(int did) {
		this.did = did;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCardno() {
		return cardno;
	}


	public void setCardno(String cardno) {
		this.cardno = cardno;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}



	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	

	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getDepartment() {
		return department;
	}


	public void setDepartment(Integer department) {
		this.department = department;
	}


	public int getEducation() {
		return education;
	}


	public void setEducation(int education) {
		this.education = education;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


}
