/**
 * 
 */
package com.hospital.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.BsProvinceDomain;
import com.hospital.model.DepartmentDomain;
import com.hospital.model.DoctorDomain;
import com.hospital.model.DoctorTitleDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;
import com.hospital.service.IBsCityService;
import com.hospital.service.IDepartmentService;
import com.hospital.service.IDoctorService;
import com.hospital.service.IHospitalService;
import com.hospital.service.impl.BsCityServiceImpl;
import com.hospital.service.impl.DepartmentServiceImpl;
import com.hospital.service.impl.DoctorServiceImpl;
import com.hospital.service.impl.HospitalServiceImpl;
import com.hospital.util.StringUtils;

@WebServlet("/doctorServlet.do")
public class DoctorServlet extends HttpServlet {

	private static final long serialVersionUID = -5029683889738931803L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("handlerType");
		System.out.println("����Ĳ���handler��:"+type);
		
		if("editorCommit".equals(type)) {
			String docIdStr = req.getParameter("docId");
			if(StringUtils.isNotBlank(docIdStr)) {
				Integer docId = Integer.valueOf(docIdStr);
				IDoctorService doctorService = new DoctorServiceImpl();
				String docName=req.getParameter("docName");
				String docTitle=req.getParameter("docTitle");
				String hosName=req.getParameter("hosName");
				String deptName=req.getParameter("deptName");
				String orderPriceStr=req.getParameter("orderPrice");
				//String ǿת��  BigDecimal
				BigDecimal orderPrice=new BigDecimal(orderPriceStr);
				String expertDesc=req.getParameter("expertDesc");
				String memo=req.getParameter("memo");
				
				DoctorDomain doctorDomain = new DoctorDomain();
				doctorDomain.setDocId(docId);
				doctorDomain.setDocName(docName);
				doctorDomain.setDocTitle(docTitle);
				doctorDomain.setHosName(hosName);
				doctorDomain.setDeptName(deptName);
				doctorDomain.setOrderPrice(orderPrice);
				doctorDomain.setExpertDesc(expertDesc);
				doctorDomain.setMemo(memo);
				doctorDomain.setDataState(1);
				Integer index = doctorService.updateDoctor(doctorDomain);
				
				resp.sendRedirect(req.getContextPath() + "/doctorServlet.do");
			}
		}else if("editorQuery".equals(type)) {
			//�༭ҽʦǰ�Ȳ�ѯ
			String docIdStr=req.getParameter("docId");
			if (StringUtils.isNotBlank(docIdStr)) {
				// ���ݿⶨ�����Integer���ͣ�����ǿת
				Integer docId = Integer.valueOf(docIdStr);
				IDoctorService doctorService = new DoctorServiceImpl();
				DoctorDomain doctor = doctorService.getDoctor(docId);
				req.setAttribute("doctor",doctor);
				

				//׼��ְ�Ƶ�����
				List<DoctorTitleDomain> docTitles = doctorService.queryDoctorTitleList();
				req.setAttribute("docTitles",docTitles);

				// ׼��ҽ�ݵ�����
				IHospitalService hospitalService = new HospitalServiceImpl();
				List<HospitalDomain> hospitals = hospitalService.getAllHospital();
				req.setAttribute("hospitals", hospitals);

			    // ׼�����ҵ�����
				IDepartmentService departmentService = new DepartmentServiceImpl();
				List<DepartmentDomain> depts = departmentService.queryAllDepartment();
				req.setAttribute("depts", depts);
				
			}
			
			//��ʽ����
			req.setAttribute("title", "�޸�ҽʦ");
			req.setAttribute("activeCss", "yishi");
		    req.getRequestDispatcher("/view/doctors_edit.jsp").forward(req, resp);
			
		}else if("del".equals(type)) {
			//ɾ��ҽʦ
			String docIdStr=req.getParameter("docId");
			if (StringUtils.isNotBlank(docIdStr)) {
				// ���ݿⶨ�����Integer���ͣ�����ǿת
				Integer docId = Integer.valueOf(docIdStr);
				IDoctorService doctorService = new DoctorServiceImpl();
				Integer index = doctorService.deleteDoctor(docId);
				//System.out.println("ɾ��������Ӱ���������" + index);
			}
			resp.sendRedirect(req.getContextPath() + "/doctorServlet.do");
			
		}else if("add".equals(type)) {
			//��������
			String docName=req.getParameter("docName");
			String docTitle=req.getParameter("docTitle");
			String hosName=req.getParameter("hosName");
			String deptName=req.getParameter("deptName");
			String orderPriceStr=req.getParameter("orderPrice");
			String expertDesc=req.getParameter("expertDesc");
			String memo=req.getParameter("memo");
			//String ǿת��  BigDecimal
			BigDecimal orderPrice=new BigDecimal(orderPriceStr);
			
			DoctorDomain doctorDomain = new DoctorDomain();
			doctorDomain.setDocName(docName);
			doctorDomain.setDocTitle(docTitle);
			doctorDomain.setHosName(hosName);
			doctorDomain.setDeptName(deptName);
			doctorDomain.setOrderPrice(orderPrice);
			doctorDomain.setExpertDesc(expertDesc);
			doctorDomain.setMemo(memo);
			doctorDomain.setDataState(1);
			
			
			
			IDoctorService doctorService = new DoctorServiceImpl();
			Boolean flag = doctorService.addDoctor(doctorDomain);
			if(flag) {
				System.out.println("�����ɹ�");          
			}else {
				System.out.println("����ʧ��");
			}
			
			resp.sendRedirect(req.getContextPath() + "/doctorServlet.do");
			
		}else {
			IDoctorService doctorService = new DoctorServiceImpl();
			String docName = req.getParameter("docName");
			String docTitle = req.getParameter("docTitle");
			
			// ҳ�洫��ĵ�ǰҳ
			String currentPageStr = req.getParameter("currentPage");
			if (StringUtils.isEmpty(currentPageStr)) {
				currentPageStr = "1";
			}
			Integer currentPage = Integer.valueOf(currentPageStr);
			// ��ҳʵ��
			PageDomain pageDomain = new PageDomain();
			pageDomain.setCurrentPage(currentPage);
			
			//׼��ְ�Ƶ�����
			List<DoctorTitleDomain> docTitles = doctorService.queryDoctorTitleList();
			req.setAttribute("docTitles",docTitles);

			// ׼��ҽ�ݵ�����
			IHospitalService hospitalService = new HospitalServiceImpl();
			List<HospitalDomain> hospitals = hospitalService.getAllHospital();
			req.setAttribute("hospitals", hospitals);

		    // ׼�����ҵ�����
			IDepartmentService departmentService = new DepartmentServiceImpl();
			List<DepartmentDomain> depts = departmentService.queryAllDepartment();
			req.setAttribute("depts", depts);
			
			List<DoctorDomain> doctorList = doctorService.queryDoctorList(docName, docTitle, pageDomain);
			pageDomain.setPageDoctor(doctorList);
			req.setAttribute("pageDomain", pageDomain);

			// ��ʽ����
			req.setAttribute("title", "ҽʦ�б�");
			req.setAttribute("activeCss", "yishi");
			req.getRequestDispatcher("/view/doctors_management.jsp").forward(req, resp);
			
		}
	}

}
