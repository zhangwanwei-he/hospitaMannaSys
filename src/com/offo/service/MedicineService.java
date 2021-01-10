package com.offo.service;

import java.util.List;

import com.offo.dao.MedicineDao;
import com.offo.dao.impl.MedicDaoImpl;
import com.offo.entity.Medicine;
import com.offo.utils.PageUtils;

public class MedicineService {
MedicineDao md=new MedicDaoImpl();
	public int getTotalCount(String name, String type) {
		return md.getTotalCount(name,type);
	}
	public List<Medicine> findMedicineByPage(String name, String type, PageUtils page) {
		return md.findMedicineByPage(name,type,page);
	}
	public boolean insertMedicine(Medicine mc) {
		// TODO Auto-generated method stub
		return md.insertMedicine(mc)>0?true:false;
	}
	public Medicine findMedicineById(String mid) {
		return md.findMedicineById(mid);
	}
	public boolean deleMedicineById(String mids) {
		return md.deleMedicineById(mids)>0?true:false;
	}
	//ÐÞ¸ÄÐÅÏ¢
	public Medicine showUpdateMedicineById(String mid) {
		
		return md.showUpdateMedicineById(mid);
	}
	public boolean updateMedicine(Medicine medicine) {
		return md.updateMedicine(medicine)>0?true:false;
	}
	
	
	
}
