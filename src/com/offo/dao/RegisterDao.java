package com.offo.dao;

import java.util.List;

import com.offo.entity.Register;
import com.offo.utils.PageUtils;

public interface RegisterDao {
	//��ǰҳ������
	int totalCount(String rid, String name, String department);
	//��ѯ��ǰҳ������
	List<Register> findRegisterByPage(String rid, String name, String department, PageUtils page);
	//��Ӳ���
	int insertRegister(Register register);
	Register ShowfindByIdRegist(String rid);
	//�޸��û�
	int updateByRegist(Register register);
	//ɾ��������Ϣ
	int deleRegisterById(String dids);
	//����ҽ��ɾ��������Ϣ
	int deleRegisterById(int dids);
	
}
