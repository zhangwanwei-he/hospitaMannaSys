package com.offo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.offo.dao.DoctorDao;
import com.offo.entity.Doctor;
import com.offo.utils.PageUtils;

public class Doctorimpl implements DoctorDao {

	QueryRunner qr=new QueryRunner(new ComboPooledDataSource());
	
	//记录总条数
	public int getTotalCount(String name, String department) {
		int count=0;
		StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM doctor WHERE 1=1");
		if(name!=null&&!"".equals(name)) {
			sql.append(" AND  NAME  LIKE '%"+name+"%'");
		}
		if(department!=null&&!"".equals(department)&&!"0".equals(department)) {
			sql.append(" and department="+Integer.valueOf(department));
		}
		try {
		Long l=	(Long) qr.query(sql.toString(), new ScalarHandler());
			count=l.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	//条件分页查询
	public List<Doctor> findDoctorByPage(String name,String department,PageUtils page){
		StringBuffer sql=new StringBuffer("SELECT * FROM doctor WHERE 1=1");
		if(name!=null&&!"".equals(name)) {
			sql.append(" AND NAME LIKE '%"+name+"%'");
		}
		if(department!=null&&!"".equals(department)&&!"0".equals(department)) {
			sql.append(" AND department="+Integer.valueOf(department));
		}
		sql.append(" limit ?,?");
			try {
				return qr.query(sql.toString(), new BeanListHandler<Doctor>(Doctor.class), 
					page.getStartIndex(),page.getPageSize());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	@Override
	//添加数据
	public int insertDoctor(Doctor doctor) {
		String sql="INSERT INTO doctor (NAME,cardno,phone,sex,age,birthday,"
				+ "email,department,education,remark)VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			return  qr.update(sql, doctor.getName(),doctor.getCardno(),doctor.getPhone(),
					doctor.getSex(),doctor.getAge(),doctor.getBirthday(),
					doctor.getEmail(),doctor.getDepartment(),doctor.getEducation(),doctor.getRemark());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	//详细查询
	public Doctor findSelectById(String did) {
		String sql="select * from doctor where did=?";
		try {
		 return	qr.query(sql, new BeanHandler<Doctor>(Doctor.class), did);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//修改数据
	public int updateById(Doctor doctor) {
		String sql="update  doctor set NAME=?,cardno=?,phone=?,sex=?,age=?,birthday=?,"
				+ "email=?,department=?,education=?,remark=?where did=?";
		try {
			return  qr.update(sql, doctor.getName(),doctor.getCardno(),doctor.getPhone(),doctor.getSex(),
					doctor.getAge(),doctor.getBirthday(),doctor.getEmail(),doctor.getDepartment(),doctor.getEducation(),
					doctor.getRemark(),doctor.getDid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<Doctor> findDoctorByDepartMent(Integer department) {
			String sql="SELECT * FROM doctor WHERE department =?";
			try {
			return	qr.query(sql, new BeanListHandler<Doctor>(Doctor.class), department);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	//删除
	@Override
	public int deleDoctorsByPage(String dids) {
		String sql="delete from doctor where did in("+dids+")";
		try {
		return	qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	
	
	
	}
	

}
