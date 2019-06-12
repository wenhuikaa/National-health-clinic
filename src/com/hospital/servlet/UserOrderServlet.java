package com.hospital.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital.dao.ICustomerDao;
import com.hospital.dao.IOrderInfoDao;
import com.hospital.dao.impl.CustomerDaoImpl;
import com.hospital.dao.impl.OrderInfoDaoImpl;
import com.hospital.model.CustomerDomain;
import com.hospital.model.DoctorDomain;
import com.hospital.model.OrderInfoDomain;
import com.hospital.service.ICustomerService;
import com.hospital.service.IDoctorService;
import com.hospital.service.IOrderInfoService;
import com.hospital.service.impl.CustomerServiceImpl;
import com.hospital.service.impl.DoctorServiceImpl;
import com.hospital.service.impl.OrderInfoServiceImpl;
import com.hospital.util.StringUtils;

/**
 * Servlet implementation class UserOrderServlet
 */
@WebServlet("/UserOrderServlet.do")
public class UserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String docIdStr = request.getParameter("docId");
		String type = request.getParameter("handlerType");
		if ("add".equals(type)) {
			String orderTypeStr = request.getParameter("orderType");
			String orderCountStr = request.getParameter("orderCount");
			String custName = request.getParameter("custName");
			String phone = request.getParameter("phone");
			String custDesc = request.getParameter("custDesc");
			String priceStr=request.getParameter("orderPrice");

			if (StringUtils.isNotBlank(docIdStr)) {
				if (StringUtils.isNotBlank(orderTypeStr)) {
					if (StringUtils.isNotBlank(orderCountStr)) {
						Integer orderType = Integer.valueOf(orderTypeStr.trim());
						Integer orderCount = Integer.valueOf(orderCountStr.trim());
						Integer docId = Integer.valueOf(docIdStr.trim());
						BigDecimal orderPrice=new BigDecimal(priceStr);
						CustomerDomain customerDomain = new CustomerDomain();
						customerDomain.setCustName(custName);
						customerDomain.setPhone(phone);
						customerDomain.setCustDesc(custDesc);
						customerDomain.setDataState(1);
						ICustomerService customerService = new CustomerServiceImpl();
						Integer index = customerService.addCustomer(customerDomain);
						if (index > 0) {
							System.out.println("添加到customer表成功!");
						} else {
							System.out.println("添加到customer表失败!");
						}

						// 获取最大的custId
						ICustomerDao customerDao = new CustomerDaoImpl();
						Integer customerMaxId = customerDao.getCustomerMaxId();

						
						// 插入order_info表
						OrderInfoDomain orderInfoDomain = new OrderInfoDomain();
						int orderCode=(int) (Math.random()*9000+1000);
						orderInfoDomain.setOrderCode(String.valueOf(orderCode));
						orderInfoDomain.setDocId(docId);
						orderInfoDomain.setCustId(customerMaxId);
						orderInfoDomain.setOrderType(orderType);
						orderInfoDomain.setPrice(orderPrice);
						orderInfoDomain.setOrderCount(orderCount);
						orderInfoDomain.setTreatState(1);
						orderInfoDomain.setDataState(1);

						IOrderInfoService orderService = new OrderInfoServiceImpl();
						Integer order = orderService.addOrderInfo(orderInfoDomain);
						if(order>0) {
							request.getSession().setAttribute("message", "您已预约成功！");
						}else {
							 request.getSession().setAttribute("message", "本次预约失败！");
						}
						request.setAttribute("title", "预约");
						request.setAttribute("activeCss", "yuyue");
						request.getRequestDispatcher("/view/reservation_edit.jsp").forward(request, response);
					}
				}

			}
			// request.getRequestDispatcher("/view/reservation_edit.jsp").forward(request,
			// response);
		} else {
			if (StringUtils.isNotBlank(docIdStr)) {
				Integer docId = Integer.valueOf(docIdStr.trim());
				System.out.println("docId:" + docId);
				// 先查询选择的医师信息
				IDoctorService doctorService = new DoctorServiceImpl();
				DoctorDomain doctor = doctorService.getDoctor(docId);
				request.setAttribute("doctor", doctor);
				request.setAttribute("title", "预约");
				request.setAttribute("activeCss", "yuyue");
				request.getRequestDispatcher("/view/reservation_edit.jsp").forward(request, response);

			}
		}

	}

}
