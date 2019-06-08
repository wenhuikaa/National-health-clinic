/**
 * 
 */
package com.hospital.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hospital.constants.HospitalConstants;
import com.hospital.model.PageDomain;
import com.hospital.model.CustomerDomain;
import com.hospital.model.DepartmentDomain;
import com.hospital.model.DoctorDomain;
import com.hospital.model.DoctorTitleDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.model.OrderCountDomain;
import com.hospital.model.OrderInfoDomain;
import com.hospital.model.OrderTypeDomain;
import com.hospital.model.TreatStateDomain;
import com.hospital.service.ICustomerService;
import com.hospital.service.IDepartmentService;
import com.hospital.service.IDoctorService;
import com.hospital.service.IHospitalService;
import com.hospital.service.IOrderInfoService;
import com.hospital.service.ISysUserService;
import com.hospital.service.impl.CustomerServiceImpl;
import com.hospital.service.impl.DepartmentServiceImpl;
import com.hospital.service.impl.DoctorServiceImpl;
import com.hospital.service.impl.HospitalServiceImpl;
import com.hospital.service.impl.OrderInfoServiceImpl;
import com.hospital.service.impl.SysUserServiceImpl;
import com.hospital.util.MD5Util;
import com.hospital.util.StringUtils;

@WebServlet("/orderInfoServlet.do")
public class OrderInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1072998621968835986L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		IOrderInfoService orderInfoService = new OrderInfoServiceImpl();
		String orderCode = req.getParameter("orderCode");
		String type=req.getParameter("handlerType");
		String orderIdStr=req.getParameter("orderId");
		String memo=req.getParameter("memo");
		
		if("treatSucceed".equals(type)){
			if (StringUtils.isNotBlank(orderIdStr)) {
				// 数据库定义的是Integer类型，所以强转
				Integer orderId = Integer.valueOf(orderIdStr);
				OrderInfoDomain orderInfoDomain = new OrderInfoDomain();
				orderInfoDomain.setOrderId(orderId);
				orderInfoDomain.setMemo("就诊成功");
				orderInfoDomain.setTreatState(2);
				orderInfoDomain.setDataState(-1);
			
				Integer index= orderInfoService.updateTreatState(orderInfoDomain);
				if(index>0) {
					System.out.println("修改成功");          
				}else {
					System.out.println("修改失败");
				}
				
			}
			resp.sendRedirect(req.getContextPath() + "/orderInfoServlet.do");
		}else if("treatFailed".equals(type)){
			if (StringUtils.isNotBlank(orderIdStr)) {
				// 数据库定义的是Integer类型，所以强转
				Integer orderId = Integer.valueOf(orderIdStr);
				OrderInfoDomain orderInfoDomain = new OrderInfoDomain();
				orderInfoDomain.setOrderId(orderId);
				orderInfoDomain.setMemo("就诊失败或预约单取消");
				orderInfoDomain.setTreatState(3);
				orderInfoDomain.setDataState(1);
			
				Integer index= orderInfoService.updateTreatState(orderInfoDomain);
				if(index>0) {
					System.out.println("修改成功");          
				}else {
					System.out.println("修改失败");
				}
				
			}
			resp.sendRedirect(req.getContextPath() + "/orderInfoServlet.do");
		}
		
		else {
			// 页面传入的当前页
			String currentPageStr = req.getParameter("currentPage");
			if (StringUtils.isEmpty(currentPageStr)) {
				currentPageStr = "1";
			}
			Integer currentPage = Integer.valueOf(currentPageStr);
			// 分页实体
			PageDomain pageDomain = new PageDomain();
			pageDomain.setCurrentPage(currentPage);

			// 医生列表
			IDoctorService doctorService = new DoctorServiceImpl();
			List<DoctorDomain> doctors = doctorService.getAllDoctors();
			req.setAttribute("doctors", doctors);

			// 客户列表
			ICustomerService customerService = new CustomerServiceImpl();
			List<CustomerDomain> customers = customerService.queryCustomerList();
			req.setAttribute("customers", customers);

			// 门诊类型
			List<OrderTypeDomain> orderTypes = orderInfoService.queryOrderType();
			req.setAttribute("orderTypes", orderTypes);

			// 初诊还是复诊
			List<OrderCountDomain> orderCount = orderInfoService.queryOrderCount();
			req.setAttribute("orderCount", orderCount);
			
			//就诊状态
			List<TreatStateDomain> treatStates=orderInfoService.queryTreatState();
			req.setAttribute("treatStates", treatStates);

			//按预约单号查询预约单列表
			List<OrderInfoDomain> orderList = orderInfoService.queryOrderInfoByCode(orderCode, pageDomain);
			pageDomain.setPageOrder(orderList);
			req.setAttribute("pageDomain", pageDomain);
			
			// 样式控制
			req.setAttribute("title", "预约单");
			req.setAttribute("activeCss", "yuyue");
			req.getRequestDispatcher("/view/reservation_form.jsp").forward(req, resp);
			
		}

	}

}
