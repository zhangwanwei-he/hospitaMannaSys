package com.offo.service;

import java.util.List;

import com.offo.dao.DoctorDao;
import com.offo.dao.RegisterDao;
import com.offo.dao.impl.Doctorimpl;
import com.offo.dao.impl.RegisterDaoimpl;
import com.offo.entity.Doctor;
import com.offo.utils.PageUtils;

public class DoctorService {
	DoctorDao dd=new Doctorimpl();
	RegisterDao rd=new RegisterDaoimpl();
//查询数据条数
	public int getTotalCount(String name, String department) {
		return dd.getTotalCount(name,department);
	}
	public List<Doctor> findDoctorByPage(String name, String department, PageUtils page) {
		
		return dd.findDoctorByPage( name,  department,  page);

	}
	//添加
	public boolean insertDoctor(Doctor doctor) {
		return dd.insertDoctor(doctor)>0?true:false;
	}
	public Doctor findSelectById(String did) {
		return dd.findSelectById(did);
	}
	public boolean updateById(Doctor doctor) {
		return	dd.updateById(doctor)>0?true:false ;
	}
	//根据department查询医生
	public List<Doctor> findDoctorByDepartMent(Integer integer) {
		return dd.findDoctorByDepartMent(integer);
	}
	public boolean deleDoctorsByDid(String dids) {
		rd.deleRegisterById(dids);
		return dd.deleDoctorsByPage(dids)>0?true:false;
		
	}

	

}
