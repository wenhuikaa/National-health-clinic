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
	
	//根据预约单号查询订单
	List<OrderInfoDomain> queryOrderInfoByCode(String orderCode,PageDomain pageDomain);
	
	//查询门诊类型
	List<OrderTypeDomain> queryOrderType() ;
	
	//查询初诊还是复诊
	List<OrderCountDomain> queryOrderCount();
	
	//统计订单数
	int countOrder(String orderCode);
	
	//查询就诊状态
	 List<TreatStateDomain> queryTreatState();
	
	 Integer updateTreatState(OrderInfoDomain orderInfoDomain);
	
	 List<OrderInfoDomain> queryOrderInfoById(Integer orderId);
}
