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
		//System.out.println("选择的医馆是："+hosName);
		
		
		// 页面传入的当前页
		String currentPageStr = req.getParameter("currentPage");
		if (StringUtils.isEmpty(currentPageStr)) {
			currentPageStr = "1";
		}
		Integer currentPage = Integer.valueOf(currentPageStr);
		// 分页实体
		PageDomain pageDomain = new PageDomain();
		pageDomain.setCurrentPage(currentPage);

		// 准备职称的数据
		List<DoctorTitleDomain> docTitles = doctorService.queryDoctorTitleList();
		req.setAttribute("docTitles", docTitles);

		// 准备医馆的数据
		IHospitalService hospitalService = new HospitalServiceImpl();
		List<HospitalDomain> hospitals = hospitalService.getAllHospital();
		req.setAttribute("hospitals", hospitals);

		// 准备科室的数据
		IDepartmentService departmentService = new DepartmentServiceImpl();
		List<DepartmentDomain> depts = departmentService.queryAllDepartment();
		req.setAttribute("depts", depts);

		List<DoctorDomain> doctorList = doctorService.queryDoctorList(docName, docTitle,hosName, pageDomain);
		pageDomain.setPageDoctor(doctorList);
		pageDomain.setHosName(hosName);
		req.setAttribute("pageDomain", pageDomain);

		// 样式控制
		req.setAttribute("title", "医师列表");
		req.setAttribute("activeCss", "yishi");
		req.getRequestDispatcher("/view/doctors_user.jsp").forward(req, resp);

	}
}
