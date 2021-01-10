package com.offo.dao;

import java.util.List;

import com.offo.entity.Doctor;
import com.offo.utils.PageUtils;

public interface DoctorDao {

	int getTotalCount(String name, String department);

	List<Doctor> findDoctorByPage(String name, String department, PageUtils page);

	int insertDoctor(Doctor doctor);

	Doctor findSelectById(String did);

	int updateById(Doctor doctor);


	List<Doctor> findDoctorByDepartMent(Integer integer);

	int deleDoctorsByPage(String dids);


}
