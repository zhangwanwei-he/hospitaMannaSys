package com.offo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.offo.dao.MedicineDao;
import com.offo.entity.Medicine;
import com.offo.utils.PageUtils;

public class MedicDaoImpl implements MedicineDao{
	QueryRunner qr=new QueryRunner(new ComboPooledDataSource());
//查询记录数
	public int getTotalCount(String name, String type){
		int count=0;
		StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM medicine  WHERE  1=1");
		if(name!=null&&!"".equals(name)) {
			sql.append(" and name like '%"+name+"%'");
		}
		if(type!=null&&!"".equals(type)&&!"0".equals(type)) {
			sql.append(" and type="+type);
		}
		Long l;
		try {
			l = (Long)qr.query(sql.toString(), new ScalarHandler());
			count=l.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//分页查询
	public List<Medicine> findMedicineByPage(String name, String type, PageUtils page) {
		StringBuffer sql=new StringBuffer("SELECT * FROM medicine  WHERE  1=1");
		if(name!=null&&!"".equals(name)) {
			sql.append(" and name like '%"+name+"%'");
		}
		if(type!=null&&!"".equals(type)&&!"0".equals(type)) {
			sql.append(" and type="+type);
		}
		sql.append(" limit ?,?");
		try {
			return 	qr.query(sql.toString(),new BeanListHandler<Medicine>(Medicine.class), page.getStartIndex(),page.getPageSize());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
	}
	@Override
	//添加数据
	public int insertMedicine(Medicine mc) {
		String sql="INSERT INTO medicine(MID,picture,inPrice,salPrice,NAME,TYPE,descs,qualityDate,description,produceFirm,readme,remark)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			return qr.update(sql, mc.getMid(),mc.getPicture(),mc.getInPrice(),mc.getSalPrice(),mc.getName()
					,mc.getType(),mc.getDescs(),mc.getQualityDate(),mc.getDescription(),mc.getProduceFirm(),mc.getReadme(),mc.getRemark());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//详细信息
	public Medicine findMedicineById(String mid) {
		String sql="SELECT * FROM medicine WHERE MID = ?";
		try {
			return  qr.query(sql, new BeanHandler<Medicine>(Medicine.class), mid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	//批量删除

	public int deleMedicineById(String mids) {
		String sql="DELETE FROM medicine WHERE MID IN ("+mids+")";
		  try {
			return qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	//修改

	public Medicine showUpdateMedicineById(String mid) {
		String  sql="SELECT * FROM medicine WHERE MID=?";
		try {
		 return	qr.query(sql, new BeanHandler<Medicine>(Medicine.class), mid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateMedicine(Medicine medicine) {
		String sql="UPDATE medicine SET picture=?,inPrice=?,salPrice=?,NAME=?,TYPE=?,descs=?,qualityDate=?,description=?,"
				+ "produceFirm=?,readme=?,remark=? WHERE MID=?";
		try {
			return qr.update(sql, medicine.getPicture(),medicine.getInPrice(),medicine.getSalPrice(),medicine.getName(),
					medicine.getType(),medicine.getDescs(),medicine.getQualityDate(),medicine.getDescription(),medicine.getProduceFirm(),
					medicine.getReadme(),medicine.getRemark(),medicine.getMid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	
	
	
	

}
	

