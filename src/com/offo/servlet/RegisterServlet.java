package com.offo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offo.entity.Doctor;
import com.offo.entity.Register;
import com.offo.service.DoctorService;
import com.offo.service.RegisterService;
import com.offo.utils.PageUtils;


@WebServlet("/register")
public class RegisterServlet extends baseServlet {
	private static final long serialVersionUID = 1L;
       RegisterService rs=new RegisterService();
       DoctorService dd=new DoctorService();
   public void deleRegisterById(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   String dids = request.getParameter("dids");
	   boolean flag= rs.deleRegisterById(dids);
	  if(flag) {
		  response.sendRedirect("register?method=findRegisterByPage");
		  
	  }
   }
	public  void  findRegisterByPage (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rid = request.getParameter("rid");//���
		String name = request.getParameter("name");//����
		String department = request.getParameter("department");//����
		String currentPage = request.getParameter("currentPage");
		int totalCount=rs.getTotalCount(rid,name,department);
		//�������������
		PageUtils page = new PageUtils(3, currentPage, totalCount);
		List<Register>	rlist=rs.findRegisterByPage(rid,name,department,page);
		//�洢��
		request.setAttribute("rid", rid);
		request.setAttribute("name", name);
		request.setAttribute("department", department);
		request.setAttribute("rlist", rlist);

		request.setAttribute("page", page);

		request.getRequestDispatcher("register/index.jsp").forward(request, response);
	}
	//���
	public void insertRegister(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String rid = request.getParameter("rid");
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String siNumber = request.getParameter("siNumber");
		String registerMoney = request.getParameter("registerMoney");
		String phone = request.getParameter("phone");
		String isPay = request.getParameter("isPay");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String consultation = request.getParameter("consultation");
		String department = request.getParameter("department");
		String did = request.getParameter("did");
		String remark = request.getParameter("remark");
		Register register = new Register(rid, name, idCard, siNumber,Double.valueOf(registerMoney), 
						phone, Integer.valueOf(isPay), Integer.valueOf(sex),
						Integer.valueOf(age), Integer.valueOf(consultation), Integer.valueOf(department), Integer.valueOf(did), remark);
		//����service��ķ���
		System.out.println(register);
		boolean flag=rs.insertRegister(register);
		if(flag) {
			response.sendRedirect("register?method=findRegisterByPage");
		}
	}
	//���ݱ�Ų�ѯҽ����Ϣ(���)
	public void findDoctorByDepartMent(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String department = request.getParameter("department");
		//����ҽ��service�ķ���
		List<Doctor> dlist=dd.findDoctorByDepartMent(Integer.valueOf(department));
		//����ѯ��dlistת����json
		ObjectMapper mapper = new ObjectMapper();
		String jsonstr = mapper.writeValueAsString(dlist);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//ת����������Ӧ��ajax��
		response.getWriter().print(jsonstr);
	}
	//��ϸ
	public void  ShowfindByIdRegist(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String rid = request.getParameter("rid");
		Register register=	rs.ShowfindByIdRegist(rid);
		request.setAttribute("register", register);
		request.getRequestDispatcher("register/look.jsp").forward(request, response);
	
	}
	//����ʾ�޸�ҳ�����Ϣ
	public void showupdateRegist(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String rid = request.getParameter("rid");
		Register register=	rs.ShowfindByIdRegist(rid);
		request.setAttribute("register", register);
		request.getRequestDispatcher("register/edit.jsp").forward(request, response);
		
	}
	//�޸�ҳ����Ϣ
	public void updateByRegist(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String rid = request.getParameter("rid");
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String siNumber = request.getParameter("siNumber");
		String registerMoney = request.getParameter("registerMoney");
		String phone = request.getParameter("phone");
		String isPay = request.getParameter("isPay");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String consultation = request.getParameter("consultation");
		String department = request.getParameter("department");
		String did = request.getParameter("did");
		String remark = request.getParameter("remark");
		Register register = new Register(rid, name, idCard, siNumber,Double.valueOf(registerMoney), 
				phone, Integer.valueOf(isPay), Integer.valueOf(sex),
				Integer.valueOf(age), Integer.valueOf(consultation), Integer.valueOf(department), Integer.valueOf(did), remark);
		//����service��ķ���
			boolean flag=rs.updateByRegist(register);
			if(flag) {
				response.sendRedirect("register?method=findRegisterByPage");
			}
	}

}
