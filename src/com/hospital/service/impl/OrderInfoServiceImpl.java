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

		//�ܼ�¼��
		int totalCount = orderInfoDao.countOrder(orderCode);
		pageDomain.setTotalCount(totalCount);
		int totalPage = pageDomain.getTotalPage();
		//��ҳ�㷨
//		��ǰҳΪ��1ҳ��ÿҳ��ʾ4�У���0�п�ʼ
//		��ǰҳΪ��2ҳ��ÿҳ��ʾ4�У���4�п�ʼ
//		��ǰҳΪ��3ҳ��ÿҳ��ʾ4�У���8�п�ʼ
		int currentPage = pageDomain.getCurrentPage();
		int pageCount = pageDomain.getPageCount();
		//System.out.println("��ǰҳΪ"+currentPage);
		//System.out.println("��ҳ��Ϊ"+totalPage);
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
