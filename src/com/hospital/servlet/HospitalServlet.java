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

@WebServlet("/hospitalServlet.do")
public class HospitalServlet extends HttpServlet {

	private static final long serialVersionUID = -7778725834971291283L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("handlerType");
		if("editorCommit".equals(type)) {
			String hosIdStr = req.getParameter("hosId");
			IDepartmentService departmentService = new DepartmentServiceImpl();
			if(StringUtils.isNotBlank(hosIdStr)) {
				Integer hosId = Integer.valueOf(hosIdStr);
				IHospitalService hospitalService = new HospitalServiceImpl();
				String hosName = req.getParameter("hosName");
				String hosAddr = req.getParameter("hosAddr");
				String hosProvince = req.getParameter("hosProvince");
				String hosCity = req.getParameter("hosCity");
				String hosArea = req.getParameter("hosArea");
				String memo = req.getParameter("memo");
				
				HospitalDomain hospitalDomain = new HospitalDomain();
				hospitalDomain.setHosId(hosId);
				hospitalDomain.setHosName(hosName);
				hospitalDomain.setHosProvince(hosProvince);
				hospitalDomain.setHosCity(hosCity);
				hospitalDomain.setHosArea(hosArea);
				hospitalDomain.setHosAddr(hosAddr);
				hospitalDomain.setDataState(1);
				hospitalDomain.setMemo(memo);
				Integer index = hospitalService.updateHospital(hospitalDomain);
				
				//ɾ������֮��Ĺ�ϵ 
				departmentService.deleteDeptHosRef(hosId);
				
				//����ҽ��֮��Ķ�Ӧ��ϵ�洢
				String deptStr = req.getParameter("depts");
				deptStr = deptStr.substring(1, deptStr.length());
				String[] depts = deptStr.split(",");
				index = departmentService.insertDeptHosRef(depts, hosId);
				resp.sendRedirect(req.getContextPath() + "/hospitalServlet.do");
			}
		}else if("editorQuery".equals(type)) {
			String hosIdStr=req.getParameter("hosId");
			if(StringUtils.isNotBlank(hosIdStr)) {
				Integer hosId=Integer.valueOf(hosIdStr);
				IHospitalService hospitalService = new HospitalServiceImpl();
			    HospitalDomain hospital= hospitalService.getHospital(hosId);
			    req.setAttribute("hospital",hospital);
			    
			    //׼��ʡ������
				IBsCityService bsCityService = new BsCityServiceImpl();
				List<BsProvinceDomain> provinces = bsCityService.queryProvince();
				req.setAttribute("provinces", provinces);
				
				// �������еĿ�����Ϣ
				IDepartmentService departmentService = new DepartmentServiceImpl();
				List<DepartmentDomain> depts = departmentService.queryAllDepartment();
//				req.setAttribute("depts", depts);
				
		
				
				//��ѯ��ҽ�ݵ����п���
				List<Integer> listRef = departmentService.queryDeptHosRef(hosId);
				System.out.println("��ҽ�ݵĿ�������Ϊ:"+listRef.size());
				List<DepartmentDomain> hosDeparts=new ArrayList();
				for(DepartmentDomain temp:depts) {
					for(Integer i:listRef) {
						if(i==temp.getDeptId()) {
							hosDeparts.add(temp);
							//�����������Լ�¼Ĭ��ѡ��
							temp.setMemo("1");
						}
					}
				}
				
	
				req.setAttribute("refs", hosDeparts);
				req.setAttribute("depts", depts);
			    
			    //��ʽ����
				req.setAttribute("title", "�޸�ҽ��");
				req.setAttribute("activeCss", "yiguan");
			    req.getRequestDispatcher("/view/hospital_edit.jsp").forward(req, resp);
			}
		}else if ("del".equals(type)) {
			String hosIdStr = req.getParameter("hosId");
			if (StringUtils.isNotBlank(hosIdStr)) {
				// ���ݿⶨ�����Integer���ͣ�����ǿת
				Integer hosId = Integer.valueOf(hosIdStr);
				IHospitalService hospitalService = new HospitalServiceImpl();
				Integer index = hospitalService.deleteHospital(hosId);
				//System.out.println("ɾ��������Ӱ���������" + index);
			}
			resp.sendRedirect(req.getContextPath() + "/hospitalServlet.do");
		} else if ("add".equals(type)) {
			// ��������
			String hosName = req.getParameter("hosName");
			String hosAddr = req.getParameter("hosAddr");
			String hosProvince = req.getParameter("hosProvince");
			String hosCity = req.getParameter("hosCity");
			String hosArea = req.getParameter("hosArea");
			String memo = req.getParameter("memo");

			HospitalDomain hospitalDomain = new HospitalDomain();
			hospitalDomain.setHosName(hosName);
			hospitalDomain.setHosName(hosName);
			hospitalDomain.setHosProvince(hosProvince);
			hospitalDomain.setHosCity(hosCity);
			hospitalDomain.setHosArea(hosArea);
			hospitalDomain.setHosAddr(hosAddr);
			hospitalDomain.setDataState(1);
			hospitalDomain.setMemo(memo);

			IHospitalService hospitalService = new HospitalServiceImpl();
			hospitalService.addHospital(hospitalDomain);
			Integer hosId = hospitalService.getHospitalMaxId();
			System.out.println("��ȡ�������IDΪ�� " + hosId);
			// ����֮��Ķ�Ӧ��ϵ�洢
			String deptStr = req.getParameter("depts");
			// ��ȡ����һ������
			deptStr = deptStr.substring(1, deptStr.length());
			String[] depts = deptStr.split(",");
			IDepartmentService departmentService = new DepartmentServiceImpl();
			Integer index = departmentService.insertDeptHosRef(depts, hosId);
			if (index > 0) {
				System.out.println("�����ɹ�");
			} else {
				System.out.println("����ʧ��");
			}
			resp.sendRedirect(req.getContextPath() + "/hospitalServlet.do");

		} else {
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
			req.getRequestDispatcher("/view/hospital_management.jsp").forward(req, resp);
			return;
		}
	}

}