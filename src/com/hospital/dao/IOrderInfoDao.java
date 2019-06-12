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

	//����ԤԼ���Ų�ѯ����
  List<OrderInfoDomain> queryOrderInfoByCode(String orderCode,int startRow,int pageRows);
  
  //��orderId��ѯ
  List<OrderInfoDomain> queryOrderInfoById(Integer orderId);
  //��ѯ��������
  List<OrderTypeDomain> queryOrderType();
  
  //��ѯ�Ƿ��ǳ��ﻹ�Ǹ���
  List<OrderCountDomain> queryOrderCount();
  
  //ͳ�ƶ�����
  int countOrder(String orderCode);
  
  //��ѯ����״̬
  List<TreatStateDomain> queryTreatState();
  
  //�޸ľ���״̬
  Integer updateTreatState(OrderInfoDomain orderInfoDomain);
  
  //���ԤԼ��Ϣ
  Integer addOrderInfo(OrderInfoDomain orderInfoDomain);
}
