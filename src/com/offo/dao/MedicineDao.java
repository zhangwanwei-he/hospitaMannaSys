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
	//�޸�--������Ϣ
	Medicine showUpdateMedicineById(String mid);

	//�޸���Ϣ
	int updateMedicine(Medicine medicine);
}
