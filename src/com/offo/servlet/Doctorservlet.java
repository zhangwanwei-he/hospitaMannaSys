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
	
	//删除
	public void deleDoctorById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String dids = request.getParameter("did");
		//调用service层的方法
		boolean flag=ds.deleDoctorsByDid(dids);
		if(flag) {
			response.sendRedirect("doctor?method=findDoctorsByPage");
		}
		
	}
	public void findDoctorsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面查询的条件
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		//获取分页查询的结果
		String currentPage = request.getParameter("currentPage");
		 int totalCount=ds.getTotalCount(name,department);
		//创建分页工具类
		PageUtils page = new PageUtils(3, currentPage, totalCount);
		List<Doctor> dlist = ds.findDoctorByPage(name,department,page);
		//将集合中的数据
		request.setAttribute("page",page);
		request.setAttribute("dlist",dlist);
		request.setAttribute("name",name);
		request.setAttribute("department",department);
		//转发
		request.getRequestDispatcher("doctor/index.jsp").forward(request, response);

	}
	//添加数据
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
		//封装数据
		Doctor doctor = new Doctor(name, cardno, phone, Integer.valueOf(sex), Integer.valueOf(age),DateUtils.stringDate(birthday),email, Integer.valueOf(department), Integer.valueOf(education), remark);
		//调用服务层的方法
		boolean flag= ds.insertDoctor(doctor);
		if(flag) {
			response.sendRedirect("doctor?method=findDoctorsByPage");
		}
	
	}
	//详细查询
		public void findSelectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String did = request.getParameter("did");
			//调用service方法
			Doctor doctor=ds.findSelectById(did);
			//将查询的数据保存到域中
			request.setAttribute("doctor", doctor);
			//跳转到look.jsp
			request.getRequestDispatcher("doctor/look.jsp").forward(request, response);
	}
		//回显信息
		public void UpdateshowById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String did = request.getParameter("did");
			//调用service方法
			Doctor doctor=ds.findSelectById(did);
			//将查询的数据保存到域中
			request.setAttribute("doctor", doctor);
			//跳转到look.jsp
			request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
		}
		//修改信息
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
		//调用service层方法
		boolean flag=ds.updateById(doctor);
		//跳转
		if(flag) {
			response.sendRedirect("doctor?method=findDoctorsByPage");
		}
	}
	public void deleRegisterById(HttpServletRequest request, HttpServletResponse response) {
		
	}
}


