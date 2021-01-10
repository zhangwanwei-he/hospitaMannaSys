package com.offo.dao;

import java.util.List;

import com.offo.entity.Register;
import com.offo.utils.PageUtils;

public interface RegisterDao {
	//当前页的数量
	int totalCount(String rid, String name, String department);
	//查询当前页的数据
	List<Register> findRegisterByPage(String rid, String name, String department, PageUtils page);
	//添加操作
	int insertRegister(Register register);
	Register ShowfindByIdRegist(String rid);
	//修改用户
	int updateByRegist(Register register);
	//删除患者信息
	int deleRegisterById(String dids);
	//根据医生删除患者信息
	int deleRegisterById(int dids);
	
}
