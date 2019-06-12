/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.OrderCountDomain;
import com.hospital.model.OrderInfoDomain;
import com.hospital.model.OrderTypeDomain;
import com.hospital.model.TreatStateDomain;

public interface IOrderInfoDao {

	//根据预约单号查询订单
  List<OrderInfoDomain> queryOrderInfoByCode(String orderCode,int startRow,int pageRows);
  
  //按orderId查询
  List<OrderInfoDomain> queryOrderInfoById(Integer orderId);
  //查询门诊类型
  List<OrderTypeDomain> queryOrderType();
  
  //查询是否是初诊还是复诊
  List<OrderCountDomain> queryOrderCount();
  
  //统计订单数
  int countOrder(String orderCode);
  
  //查询就诊状态
  List<TreatStateDomain> queryTreatState();
  
  //修改就诊状态
  Integer updateTreatState(OrderInfoDomain orderInfoDomain);
  
  //添加预约信息
  Integer addOrderInfo(OrderInfoDomain orderInfoDomain);
}
