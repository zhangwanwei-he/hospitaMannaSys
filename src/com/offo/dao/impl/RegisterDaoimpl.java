package com.offo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.offo.dao.RegisterDao;
import com.offo.entity.Register;
import com.offo.utils.PageUtils;

public class RegisterDaoimpl implements RegisterDao {
	QueryRunner qr=new QueryRunner(new ComboPooledDataSource());
	//查询当前页数
	@Override
	public int totalCount(String rid, String name, String department) {
		StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM register WHERE 1=1");
		int count=0;
		if(rid!=null&&!"".equals(rid)) {
			sql.append(" and rid="+rid);
		}
		if(name!=null&&!"".equals(name)) {
			sql.append(" and name LIKE'%"+name+"%'");
		}
		if(department!=null&&!"".equals(department)&&!"0".equals(department)) {
			sql.append(" and department="+department);
		}
		try {
			Long l	=(Long)qr.query(sql.toString(),new ScalarHandler());
			count=l.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override
	//查询当前页的数据
	public List<Register> findRegisterByPage(String rid, String name, String department,PageUtils page) {
		StringBuffer sql=new StringBuffer("SELECT * FROM register WHERE 1=1");
		int count=0;
		if(rid!=null&&!"".equals(rid)) {
			sql.append(" and rid="+rid);
		}
		if(name!=null&&!"".equals(name)) {
			sql.append(" and name like '%"+name+"%'");
		}
		if(department!=null&&!"".equals(department)&&!"0".equals(department)) {
			sql.append(" and department="+department);
		}
		sql.append(" limit ?,?");
		try {
			return qr.query(sql.toString(), new BeanListHandler<Register>(Register.class),
					page.getStartIndex(),page.getPageSize());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	//添加数据
	public int insertRegister(Register register) {
		String sql="INSERT INTO register(rid,NAME,idCard,siNumber,registerMoney,phone,isPay,sex,age,consultation,department,did,remark)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		return	qr.update(sql, register.getRid(),register.getName(),register.getIdCard(),register.getSiNumber(),register.getRegisterMoney(),
					register.getPhone(),register.getIsPay(),register.getSex(),register.getAge(),register.getConsultation(),
					register.getDepartment(),register.getDid(),register.getRemark());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
@Override
	public Register ShowfindByIdRegist(String rid) {
	String sql="select * from register  where rid=?";
		 try {
			return qr.query(sql,new BeanHandler<Register>(Register.class) ,rid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return null;
}
@Override
//更新页面
public int updateByRegist(Register register) {
	String sql="update register set NAME=?,idCard=?,siNumber=?,registerMoney=?,phone=?,isPay=?,sex=?,age=?,consultation=?,department=?,did=?,remark=? where rid=?";
	 try {
		return qr.update(sql, register.getName(),register.getIdCard(),register.getSiNumber(),register.getRegisterMoney(),
				register.getPhone(),register.getIsPay(),register.getSex(),register.getAge(),register.getConsultation(),
				register.getDepartment(),register.getDid(),register.getRemark(),register.getRid());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
}
@Override
public int deleRegisterById(int dids) {
		String sql="delete from register where did in("+dids+")";
		System.out.println(sql);
		try {
		return	qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
@Override
public int deleRegisterById(String dids) {
	
		String sql="delete from register where rid in("+dids+")";
		System.out.println(sql);
		try {
		return	qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}



