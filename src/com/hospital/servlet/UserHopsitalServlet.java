package com.hospital.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.BsProvinceDomain;
import com.hospital.model.DepartmentDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;
import com.hospital.service.IBsCityService;
import com.hospital.service.IDepartmentService;
import com.hospital.service.IHospitalService;
import com.hospital.service.impl.BsCityServiceImpl;
import com.hospital.service.impl.DepartmentServiceImpl;
import com.hospital.service.impl.HospitalServiceImpl;
import com.hospital.util.StringUtils;

/**
 * Servlet implementation class UserHopsitalServlet
 */
@WebServlet("/UserHopsitalServlet.do")
public class UserHopsitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ��ȡ���µ�ҽ���б�
		IHospitalService hospitalService = new HospitalServiceImpl();
		String hosName = req.getParameter("hosName");
		String hosAddr = req.getParameter("hosAddr");

		// ҳ�洫��ĵ�ǰҳ
		String currentPageStr = req.getParameter("currentPage");
		if (StringUtils.isEmpty(currentPageStr)) {
			currentPageStr = "1";
		}
		Integer currentPage = Integer.valueOf(currentPageStr);
		// ��ҳʵ��
		PageDomain pageDomain = new PageDomain();
		pageDomain.setCurrentPage(currentPage);
		List<HospitalDomain> hospitalList = hospitalService.queryHospitalList(hosName, hosAddr, pageDomain);
		pageDomain.setPageData(hospitalList);
		req.setAttribute("pageDomain", pageDomain);

		// ׼��ʡ������
		IBsCityService bsCityService = new BsCityServiceImpl();
		List<BsProvinceDomain> provinces = bsCityService.queryProvince();
		req.setAttribute("provinces", provinces);

		// �������еĿ�����Ϣ
		IDepartmentService departmentService = new DepartmentServiceImpl();
		List<DepartmentDomain> depts = departmentService.queryAllDepartment();
		req.setAttribute("depts", depts);

		// ��ʽ����
		req.setAttribute("title", "ҽ���б�");
		req.setAttribute("activeCss", "yiguan");
		req.getRequestDispatcher("/view/hospital_user.jsp").forward(req, resp);
		return;
	}
}
