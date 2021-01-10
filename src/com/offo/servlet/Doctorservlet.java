package com.offo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.offo.entity.Doctor;
import com.offo.service.DoctorService;
import com.offo.utils.DateUtils;
import com.offo.utils.PageUtils;

/**
 * Servlet implementation class Doctorservlet
 */
@WebServlet("/doctor")
public class Doctorservlet extends baseServlet {
	private static final long serialVersionUID = 1L;
	DoctorService ds=new DoctorService();
	
	//ɾ��
	public void deleDoctorById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String dids = request.getParameter("did");
		//����service��ķ���
		boolean flag=ds.deleDoctorsByDid(dids);
		if(flag) {
			response.sendRedirect("doctor?method=findDoctorsByPage");
		}
		
	}
	public void findDoctorsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ���ѯ������
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		//��ȡ��ҳ��ѯ�Ľ��
		String currentPage = request.getParameter("currentPage");
		 int totalCount=ds.getTotalCount(name,department);
		//������ҳ������
		PageUtils page = new PageUtils(3, currentPage, totalCount);
		List<Doctor> dlist = ds.findDoctorByPage(name,department,page);
		//�������е�����
		request.setAttribute("page",page);
		request.setAttribute("dlist",dlist);
		request.setAttribute("name",name);
		request.setAttribute("department",department);
		//ת��
		request.getRequestDispatcher("doctor/index.jsp").forward(request, response);

	}
	//�������
	public  void insertDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String department = request.getParameter("department");
		String education = request.getParameter("education");
		String remark = request.getParameter("remark");
		//��װ����
		Doctor doctor = new Doctor(name, cardno, phone, Integer.valueOf(sex), Integer.valueOf(age),DateUtils.stringDate(birthday),email, Integer.valueOf(department), Integer.valueOf(education), remark);
		//���÷����ķ���
		boolean flag= ds.insertDoctor(doctor);
		if(flag) {
			response.sendRedirect("doctor?method=findDoctorsByPage");
		}
	
	}
	//��ϸ��ѯ
		public void findSelectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String did = request.getParameter("did");
			//����service����
			Doctor doctor=ds.findSelectById(did);
			//����ѯ�����ݱ��浽����
			request.setAttribute("doctor", doctor);
			//��ת��look.jsp
			request.getRequestDispatcher("doctor/look.jsp").forward(request, response);
	}
		//������Ϣ
		public void UpdateshowById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String did = request.getParameter("did");
			//����service����
			Doctor doctor=ds.findSelectById(did);
			//����ѯ�����ݱ��浽����
			request.setAttribute("doctor", doctor);
			//��ת��look.jsp
			request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
		}
		//�޸���Ϣ
	public void	updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String did = request.getParameter("did");
		String name = request.getParameter("name");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String department = request.getParameter("department");
		String education = request.getParameter("education");
		String remark = request.getParameter("remark");
		Doctor doctor = new Doctor(Integer.valueOf(did), name, cardno, phone, Integer.valueOf(sex), Integer.valueOf(age), DateUtils.stringDate(birthday), email, Integer.valueOf(department),Integer.valueOf(education), remark);
		//����service�㷽��
		boolean flag=ds.updateById(doctor);
		//��ת
		if(flag) {
			response.sendRedirect("doctor?method=findDoctorsByPage");
		}
	}
	public void deleRegisterById(HttpServletRequest request, HttpServletResponse response) {
		
	}
}


