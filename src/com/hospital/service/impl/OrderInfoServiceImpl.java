/**
 * 
 */
package com.hospital.service.impl;

import java.util.List;

import com.hospital.dao.IOrderInfoDao;
import com.hospital.dao.impl.OrderInfoDaoImpl;
import com.hospital.model.HospitalDomain;
import com.hospital.model.OrderCountDomain;
import com.hospital.model.OrderInfoDomain;
import com.hospital.model.OrderTypeDomain;
import com.hospital.model.PageDomain;
import com.hospital.model.TreatStateDomain;
import com.hospital.service.IOrderInfoService;

public class OrderInfoServiceImpl implements IOrderInfoService{

	@Override
	public List<OrderInfoDomain> queryOrderInfoByCode(String orderCode,PageDomain pageDomain) {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();

		//总记录数
		int totalCount = orderInfoDao.countOrder(orderCode);
		pageDomain.setTotalCount(totalCount);
		int totalPage = pageDomain.getTotalPage();
		//分页算法
//		当前页为第1页，每页显示4行，从0行开始
//		当前页为第2页，每页显示4行，从4行开始
//		当前页为第3页，每页显示4行，从8行开始
		int currentPage = pageDomain.getCurrentPage();
		int pageCount = pageDomain.getPageCount();
		//System.out.println("当前页为"+currentPage);
		//System.out.println("总页数为"+totalPage);
		if(currentPage > totalPage) {
			currentPage = totalPage;
		}else if(currentPage < 1){
			currentPage = 1;
		}
		pageDomain.setCurrentPage(currentPage);
		int startRow = (currentPage - 1) * pageCount;
		List<OrderInfoDomain> orderList =orderInfoDao.queryOrderInfoByCode(orderCode, startRow, pageCount); 
		return orderList;
	}

	@Override
	public List<OrderTypeDomain> queryOrderType() {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.queryOrderType();
	}

	@Override
	public List<OrderCountDomain> queryOrderCount() {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.queryOrderCount();
	}

	@Override
	public int countOrder(String orderCode) {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.countOrder(orderCode);
	}

	@Override
	public List<TreatStateDomain> queryTreatState() {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.queryTreatState();
	}

	@Override
	public Integer updateTreatState(OrderInfoDomain orderInfoDomain) {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		Integer index = orderInfoDao.updateTreatState(orderInfoDomain);
		return index;
	}

	@Override
	public List<OrderInfoDomain> queryOrderInfoById(Integer orderId) {
		IOrderInfoDao orderInfoDao = new OrderInfoDaoImpl();
		return orderInfoDao.queryOrderInfoById(orderId);
	}

	
}
