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
				// ���ݿⶨ�����Integer���ͣ�����ǿת
				Integer orderId = Integer.valueOf(orderIdStr);
				OrderInfoDomain orderInfoDomain = new OrderInfoDomain();
				orderInfoDomain.setOrderId(orderId);
				orderInfoDomain.setMemo("����ɹ�");
				orderInfoDomain.setTreatState(2);
				orderInfoDomain.setDataState(-1);
			
				Integer index= orderInfoService.updateTreatState(orderInfoDomain);
				if(index>0) {
					System.out.println("�޸ĳɹ�");          
				}else {
					System.out.println("�޸�ʧ��");
				}
				
			}
			resp.sendRedirect(req.getContextPath() + "/orderInfoServlet.do");
		}else if("treatFailed".equals(type)){
			if (StringUtils.isNotBlank(orderIdStr)) {
				// ���ݿⶨ�����Integer���ͣ�����ǿת
				Integer orderId = Integer.valueOf(orderIdStr);
				OrderInfoDomain orderInfoDomain = new OrderInfoDomain();
				orderInfoDomain.setOrderId(orderId);
				orderInfoDomain.setMemo("����ʧ�ܻ�ԤԼ��ȡ��");
				orderInfoDomain.setTreatState(3);
				orderInfoDomain.setDataState(1);
			
				Integer index= orderInfoService.updateTreatState(orderInfoDomain);
				if(index>0) {
					System.out.println("�޸ĳɹ�");          
				}else {
					System.out.println("�޸�ʧ��");
				}
				
			}
			resp.sendRedirect(req.getContextPath() + "/orderInfoServlet.do");
		}
		
		else {
			// ҳ�洫��ĵ�ǰҳ
			String currentPageStr = req.getParameter("currentPage");
			if (StringUtils.isEmpty(currentPageStr)) {
				currentPageStr = "1";
			}
			Integer currentPage = Integer.valueOf(currentPageStr);
			// ��ҳʵ��
			PageDomain pageDomain = new PageDomain();
			pageDomain.setCurrentPage(currentPage);

			// ҽ���б�
			IDoctorService doctorService = new DoctorServiceImpl();
			List<DoctorDomain> doctors = doctorService.getAllDoctors();
			req.setAttribute("doctors", doctors);

			// �ͻ��б�
			ICustomerService customerService = new CustomerServiceImpl();
			List<CustomerDomain> customers = customerService.queryCustomerList();
			req.setAttribute("customers", customers);

			// ��������
			List<OrderTypeDomain> orderTypes = orderInfoService.queryOrderType();
			req.setAttribute("orderTypes", orderTypes);

			// ���ﻹ�Ǹ���
			List<OrderCountDomain> orderCount = orderInfoService.queryOrderCount();
			req.setAttribute("orderCount", orderCount);
			
			//����״̬
			List<TreatStateDomain> treatStates=orderInfoService.queryTreatState();
			req.setAttribute("treatStates", treatStates);

			//��ԤԼ���Ų�ѯԤԼ���б�
			List<OrderInfoDomain> orderList = orderInfoService.queryOrderInfoByCode(orderCode, pageDomain);
			pageDomain.setPageOrder(orderList);
			req.setAttribute("pageDomain", pageDomain);
			
			// ��ʽ����
			req.setAttribute("title", "ԤԼ��");
			req.setAttribute("activeCss", "yuyue");
			req.getRequestDispatcher("/view/reservation_form.jsp").forward(req, resp);
			
		}

	}

}
