/**
 * 
 */
package com.hospital.service;

import java.util.List;

import com.hospital.model.OrderCountDomain;
import com.hospital.model.OrderInfoDomain;
import com.hospital.model.OrderTypeDomain;
import com.hospital.model.PageDomain;
import com.hospital.model.TreatStateDomain;

public interface IOrderInfoService {
	
	//����ԤԼ���Ų�ѯ����
	List<OrderInfoDomain> queryOrderInfoByCode(String orderCode,PageDomain pageDomain);
	
	//��ѯ��������
	List<OrderTypeDomain> queryOrderType() ;
	
	//��ѯ���ﻹ�Ǹ���
	List<OrderCountDomain> queryOrderCount();
	
	//ͳ�ƶ�����
	int countOrder(String orderCode);
	
	//��ѯ����״̬
	 List<TreatStateDomain> queryTreatState();
	
	 Integer updateTreatState(OrderInfoDomain orderInfoDomain);
	
	 List<OrderInfoDomain> queryOrderInfoById(Integer orderId);
}
