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
		System.out.println("请求的参数handler是:"+type);
		
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
				//String 强转成  BigDecimal
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
			//编辑医师前先查询
			String docIdStr=req.getParameter("docId");
			if (StringUtils.isNotBlank(docIdStr)) {
				// 数据库定义的是Integer类型，所以强转
				Integer docId = Integer.valueOf(docIdStr);
				IDoctorService doctorService = new DoctorServiceImpl();
				DoctorDomain doctor = doctorService.getDoctor(docId);
				req.setAttribute("doctor",doctor);
				

				//准备职称的数据
				List<DoctorTitleDomain> docTitles = doctorService.queryDoctorTitleList();
				req.setAttribute("docTitles",docTitles);

				// 准备医馆的数据
				IHospitalService hospitalService = new HospitalServiceImpl();
				List<HospitalDomain> hospitals = hospitalService.getAllHospital();
				req.setAttribute("hospitals", hospitals);

			    // 准备科室的数据
				IDepartmentService departmentService = new DepartmentServiceImpl();
				List<DepartmentDomain> depts = departmentService.queryAllDepartment();
				req.setAttribute("depts", depts);
				
			}
			
			//样式控制
			req.setAttribute("title", "修改医师");
			req.setAttribute("activeCss", "yishi");
		    req.getRequestDispatcher("/view/doctors_edit.jsp").forward(req, resp);
			
		}else if("del".equals(type)) {
			//删除医师
			String docIdStr=req.getParameter("docId");
			if (StringUtils.isNotBlank(docIdStr)) {
				// 数据库定义的是Integer类型，所以强转
				Integer docId = Integer.valueOf(docIdStr);
				IDoctorService doctorService = new DoctorServiceImpl();
				Integer index = doctorService.deleteDoctor(docId);
				//System.out.println("删除操作所影响的行数：" + index);
			}
			resp.sendRedirect(req.getContextPath() + "/doctorServlet.do");
			
		}else if("add".equals(type)) {
			//新增操作
			String docName=req.getParameter("docName");
			String docTitle=req.getParameter("docTitle");
			String hosName=req.getParameter("hosName");
			String deptName=req.getParameter("deptName");
			String orderPriceStr=req.getParameter("orderPrice");
			String expertDesc=req.getParameter("expertDesc");
			String memo=req.getParameter("memo");
			//String 强转成  BigDecimal
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
				System.out.println("新增成功");          
			}else {
				System.out.println("新增失败");
			}
			
			resp.sendRedirect(req.getContextPath() + "/doctorServlet.do");
			
		}else {
			IDoctorService doctorService = new DoctorServiceImpl();
			String docName = req.getParameter("docName");
			String docTitle = req.getParameter("docTitle");
			
			// 页面传入的当前页
			String currentPageStr = req.getParameter("currentPage");
			if (StringUtils.isEmpty(currentPageStr)) {
				currentPageStr = "1";
			}
			Integer currentPage = Integer.valueOf(currentPageStr);
			// 分页实体
			PageDomain pageDomain = new PageDomain();
			pageDomain.setCurrentPage(currentPage);
			
			//准备职称的数据
			List<DoctorTitleDomain> docTitles = doctorService.queryDoctorTitleList();
			req.setAttribute("docTitles",docTitles);

			// 准备医馆的数据
			IHospitalService hospitalService = new HospitalServiceImpl();
			List<HospitalDomain> hospitals = hospitalService.getAllHospital();
			req.setAttribute("hospitals", hospitals);

		    // 准备科室的数据
			IDepartmentService departmentService = new DepartmentServiceImpl();
			List<DepartmentDomain> depts = departmentService.queryAllDepartment();
			req.setAttribute("depts", depts);
			
			List<DoctorDomain> doctorList = doctorService.queryDoctorList(docName, docTitle, pageDomain);
			pageDomain.setPageDoctor(doctorList);
			req.setAttribute("pageDomain", pageDomain);

			// 样式控制
			req.setAttribute("title", "医师列表");
			req.setAttribute("activeCss", "yishi");
			req.getRequestDispatcher("/view/doctors_management.jsp").forward(req, resp);
			
		}
	}

}
