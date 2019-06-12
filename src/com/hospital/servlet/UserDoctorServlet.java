package com.hospital.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hospital.constants.HospitalConstants;
import com.hospital.model.DepartmentDomain;
import com.hospital.model.DoctorDomain;
import com.hospital.model.DoctorTitleDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;
import com.hospital.service.IDepartmentService;
import com.hospital.service.IDoctorService;
import com.hospital.service.IHospitalService;
import com.hospital.service.impl.DepartmentServiceImpl;
import com.hospital.service.impl.DoctorServiceImpl;
import com.hospital.service.impl.HospitalServiceImpl;
import com.hospital.util.StringUtils;

/**
 * Servlet implementation class UserDoctorServlet
 */
@WebServlet("/UserDoctorServlet.do")
public class UserDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		IDoctorService doctorService = new DoctorServiceImpl();
		String docName = req.getParameter("docName");
		String docTitle = req.getParameter("docTitle");
		String hosName=req.getParameter("hosName");
		//System.out.println("ѡ���ҽ���ǣ�"+hosName);
		
		
		// ҳ�洫��ĵ�ǰҳ
		String currentPageStr = req.getParameter("currentPage");
		if (StringUtils.isEmpty(currentPageStr)) {
			currentPageStr = "1";
		}
		Integer currentPage = Integer.valueOf(currentPageStr);
		// ��ҳʵ��
		PageDomain pageDomain = new PageDomain();
		pageDomain.setCurrentPage(currentPage);

		// ׼��ְ�Ƶ�����
		List<DoctorTitleDomain> docTitles = doctorService.queryDoctorTitleList();
		req.setAttribute("docTitles", docTitles);

		// ׼��ҽ�ݵ�����
		IHospitalService hospitalService = new HospitalServiceImpl();
		List<HospitalDomain> hospitals = hospitalService.getAllHospital();
		req.setAttribute("hospitals", hospitals);

		// ׼�����ҵ�����
		IDepartmentService departmentService = new DepartmentServiceImpl();
		List<DepartmentDomain> depts = departmentService.queryAllDepartment();
		req.setAttribute("depts", depts);

		List<DoctorDomain> doctorList = doctorService.queryDoctorList(docName, docTitle,hosName, pageDomain);
		pageDomain.setPageDoctor(doctorList);
		pageDomain.setHosName(hosName);
		req.setAttribute("pageDomain", pageDomain);

		// ��ʽ����
		req.setAttribute("title", "ҽʦ�б�");
		req.setAttribute("activeCss", "yishi");
		req.getRequestDispatcher("/view/doctors_user.jsp").forward(req, resp);

	}
}
