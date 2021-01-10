package com.offo.dao;

import java.util.List;

import com.offo.entity.Medicine;
import com.offo.utils.PageUtils;

public interface MedicineDao {

	List<Medicine> findMedicineByPage(String name, String type, PageUtils page);

	int getTotalCount(String name, String type);

	int insertMedicine(Medicine mc);

	int deleMedicineById(String mids);
	
	Medicine findMedicineById(String mid);
	//修改--回显信息
	Medicine showUpdateMedicineById(String mid);

	//修改信息
	int updateMedicine(Medicine medicine);
}
