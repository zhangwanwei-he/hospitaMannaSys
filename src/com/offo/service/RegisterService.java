package com.offo.service;

import java.util.List;

import com.offo.dao.DoctorDao;
import com.offo.dao.RegisterDao;
import com.offo.dao.impl.Doctorimpl;
import com.offo.dao.impl.RegisterDaoimpl;
import com.offo.entity.Doctor;
import com.offo.entity.Register;
import com.offo.utils.PageUtils;

public class RegisterService {
	RegisterDao rd=new RegisterDaoimpl();
	DoctorDao dd=new Doctorimpl();
	public int getTotalCount(String rid, String name, String department) {
		return  rd.totalCount(rid,name,department);
	}
	//查询患者的信息列表
	public List<Register> findRegisterByPage(String rid, String name, String department, PageUtils page) {
		List<Register> rlist = rd.findRegisterByPage(rid,name,department,page);
		for (Register register : rlist) {
			Doctor doctor = dd.findSelectById(register.getDid()+"");
			register.setDoctor(doctor);	
		}
		return rlist;
	}
	public boolean insertRegister(Register register) {
		// TODO Auto-generated method stub
		return rd.insertRegister(register)>0?true:false;
	}
	public Register ShowfindByIdRegist(String rid) {
		 Register re = rd.ShowfindByIdRegist(rid);
		 int did = re.getDid();
		 Doctor doctor = dd.findSelectById(did+"");
		 re.setDoctor(doctor);
		 return re;
	}
	//修改用户
	public boolean updateByRegist(Register register) {
		// TODO Auto-generated method stub
		return rd.updateByRegist(register)>0?true:false;
	}
	//删除信息
	public boolean deleRegisterById(int dids) {
		return rd.deleRegisterById(dids)>0?true:false;
	}
	public boolean deleRegisterById(String dids) {
		return  rd.deleRegisterById(dids)>0?true:false;
	}

}
