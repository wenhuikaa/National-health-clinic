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
				
				//删除科室之间的关系 
				departmentService.deleteDeptHosRef(hosId);
				
				//科室医馆之间的对应关系存储
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
			    
			    //准备省的数据
				IBsCityService bsCityService = new BsCityServiceImpl();
				List<BsProvinceDomain> provinces = bsCityService.queryProvince();
				req.setAttribute("provinces", provinces);
				
				// 加载所有的科室信息
				IDepartmentService departmentService = new DepartmentServiceImpl();
				List<DepartmentDomain> depts = departmentService.queryAllDepartment();
//				req.setAttribute("depts", depts);
				
		
				
				//查询该医馆的所有科室
				List<Integer> listRef = departmentService.queryDeptHosRef(hosId);
				System.out.println("该医馆的科室总数为:"+listRef.size());
				List<DepartmentDomain> hosDeparts=new ArrayList();
				for(DepartmentDomain temp:depts) {
					for(Integer i:listRef) {
						if(i==temp.getDeptId()) {
							hosDeparts.add(temp);
							//利用现有属性记录默认选择
							temp.setMemo("1");
						}
					}
				}
				
	
				req.setAttribute("refs", hosDeparts);
				req.setAttribute("depts", depts);
			    
			    //样式控制
				req.setAttribute("title", "修改医馆");
				req.setAttribute("activeCss", "yiguan");
			    req.getRequestDispatcher("/view/hospital_edit.jsp").forward(req, resp);
			}
		}else if ("del".equals(type)) {
			String hosIdStr = req.getParameter("hosId");
			if (StringUtils.isNotBlank(hosIdStr)) {
				// 数据库定义的是Integer类型，所以强转
				Integer hosId = Integer.valueOf(hosIdStr);
				IHospitalService hospitalService = new HospitalServiceImpl();
				Integer index = hospitalService.deleteHospital(hosId);
				//System.out.println("删除操作所影响的行数：" + index);
			}
			resp.sendRedirect(req.getContextPath() + "/hospitalServlet.do");
		} else if ("add".equals(type)) {
			// 新增操作
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
			System.out.println("获取到的最大ID为： " + hosId);
			// 科室之间的对应关系存储
			String deptStr = req.getParameter("depts");
			// 截取掉第一个逗号
			deptStr = deptStr.substring(1, deptStr.length());
			String[] depts = deptStr.split(",");
			IDepartmentService departmentService = new DepartmentServiceImpl();
			Integer index = departmentService.insertDeptHosRef(depts, hosId);
			if (index > 0) {
				System.out.println("新增成功");
			} else {
				System.out.println("新增失败");
			}
			resp.sendRedirect(req.getContextPath() + "/hospitalServlet.do");

		} else {
			// 获取最新的医馆列表
			IHospitalService hospitalService = new HospitalServiceImpl();
			String hosName = req.getParameter("hosName");
			String hosAddr = req.getParameter("hosAddr");

			// 页面传入的当前页
			String currentPageStr = req.getParameter("currentPage");
			if (StringUtils.isEmpty(currentPageStr)) {
				currentPageStr = "1";
			}
			Integer currentPage = Integer.valueOf(currentPageStr);
			// 分页实体
			PageDomain pageDomain = new PageDomain();
			pageDomain.setCurrentPage(currentPage);
			List<HospitalDomain> hospitalList = hospitalService.queryHospitalList(hosName, hosAddr, pageDomain);
			pageDomain.setPageData(hospitalList);
			req.setAttribute("pageDomain", pageDomain);

			// 准备省的数据
			IBsCityService bsCityService = new BsCityServiceImpl();
			List<BsProvinceDomain> provinces = bsCityService.queryProvince();
			req.setAttribute("provinces", provinces);

			// 加载所有的科室信息
			IDepartmentService departmentService = new DepartmentServiceImpl();
			List<DepartmentDomain> depts = departmentService.queryAllDepartment();
			req.setAttribute("depts", depts);

			// 样式控制
			req.setAttribute("title", "医馆列表");
			req.setAttribute("activeCss", "yiguan");
			req.getRequestDispatcher("/view/hospital_management.jsp").forward(req, resp);
			return;
		}
	}

}