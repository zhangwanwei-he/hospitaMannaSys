package com.offo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.offo.entity.Medicine;
import com.offo.service.MedicineService;
import com.offo.utils.PageUtils;
import com.offo.utils.uplodeUtils;

@MultipartConfig
@WebServlet("/medicine")
public class MedicineServlet extends baseServlet {
	private static final long serialVersionUID = 1L;
	MedicineService ms=new MedicineService();
	public  void findMedicineByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ѯ����
		String name = request.getParameter("name");//ҩƷ����
		String type = request.getParameter("type");//ҩƿ����
		//��ȡ��ҳ��ѯ������
		String currentPage = request.getParameter("currentPage");
		
		int totalCount=  ms.getTotalCount(name,type);
		//�������������
		PageUtils page = new PageUtils(3, currentPage,totalCount );//ҳ����  ��ǰҳ��  ��¼���� 
		//����service �ķ�ҳ����
		List<Medicine> mlist=ms.findMedicineByPage(name,type,page);
		//System.out.println(mlist.size());
		//��ҳ��ʹ�õ����ݱ��浽����
		request.setAttribute("name", name);
		request.setAttribute("type", type);
		request.setAttribute("page", page);
		request.setAttribute("mlist", mlist);
		request.getRequestDispatcher("medicine/index.jsp").forward(request, response);
	
	}
	//���ҩƷ
	public  void insertMedicine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡҳ����
		String mid = request.getParameter("mid");
		String inPrice = request.getParameter("inPrice");
		String salPrice = request.getParameter("salPrice");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String descs = request.getParameter("descs");
		String qualityDate = request.getParameter("qualityDate");
		String description = request.getParameter("description");
		String produceFirm = request.getParameter("produceFirm");
		String readme = request.getParameter("readme");
		String remark = request.getParameter("remark");
		Part part = request.getPart("picture");
		String picture = uplodeUtils.fileUplode(part);

		Medicine mc = new Medicine(mid, picture, Double.valueOf(inPrice), Double.valueOf(salPrice), name, Integer.valueOf(type), descs, Integer.valueOf(qualityDate), description, produceFirm, readme, remark);
		boolean flag=ms.insertMedicine(mc);
		if(flag) {
			response.sendRedirect("medicine?method=findMedicineByPage");
		}
	}
	//��ϸ��Ϣ
	public  void findMedicineById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		Medicine mc= ms.findMedicineById(mid);
		request.setAttribute("mc", mc);
		request.getRequestDispatcher("medicine/look.jsp").forward(request, response);
		
	}
	//ɾ��
	public void deleMedicineById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mids = request.getParameter("mids");
		boolean flag= ms.deleMedicineById(mids);
		if(flag) {
			response.sendRedirect("medicine?method=findMedicineByPage");
		}
		
	}
	//�����޸ĵ���Ϣ
	public  void showUpdateMedicineById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		Medicine me=ms.showUpdateMedicineById(mid);
		request.setAttribute("me", me);
		request.getRequestDispatcher("medicine/edit.jsp").forward(request, response);
	}
	//�޸���Ϣ
	public void updateMedicine(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String mid = request.getParameter("mid");
		//String picture = request.getParameter("oldpicture");
		String inPrice = request.getParameter("inPrice");
		String salPrice = request.getParameter("salPrice");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String descs = request.getParameter("descs");
		String qualityDate = request.getParameter("qualityDate");
		String description = request.getParameter("description");
		String produceFirm = request.getParameter("produceFirm");
		String readme = request.getParameter("readme");
		String remark = request.getParameter("remark");
		//ͼƬ����
		Part part = request.getPart("picture");
		//��ȡ�ϵ�ͼƬ
		String oldpicture = request.getParameter("oldpicture");
		if(part.getSize()!=0) {
			oldpicture=uplodeUtils.fileUplode(part);
		}
		Medicine medicine = new Medicine(mid,oldpicture, Double.valueOf(inPrice), Double.valueOf(salPrice), name, Integer.valueOf(type), descs,Integer.valueOf(qualityDate), description, produceFirm, readme, remark);
		boolean flag= ms.updateMedicine(medicine);
		if(flag) {
			response.sendRedirect("medicine?method=findMedicineByPage");
		}
	}
}
