/**
 * 
 */
package com.hospital.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hospital.dao.IOrderInfoDao;
import com.hospital.model.TreatStateDomain;
import com.hospital.model.OrderInfoDomain;
import com.hospital.model.OrderTypeDomain;
import com.hospital.model.DoctorDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.model.OrderCountDomain;
import com.hospital.util.DBUtil;

public class OrderInfoDaoImpl implements IOrderInfoDao{

	/**
	 * 按预约单号查询
	 */
	@Override
	public List<OrderInfoDomain> queryOrderInfoByCode(String orderCode,int startRow,int pageRows) {
		List<Object> params=new ArrayList<>();
//		String sql="select * from order_info where 1=1 and data_state=1 and order_date>='2019-06-03 18:51:11' and order_date<='2019-06-03 18:51:12'";
		String sql="select * from order_info where 1=1 and data_state=1";
		if(orderCode!=null&&!"".equals(orderCode)) {
			params.add("%"+orderCode+"%");
			sql+=" and order_code like ?";
		}
//		
//		if(orderDate!=null&&!"".equals(orderDate)) {
//			params.add(orderDate);
//			sql+=" and order_date = ?";
//		}
//		
		
		System.out.println(sql);
		
		if( pageRows > 0) {
			//分页
			sql+= " limit ? , ?";
			params.add(startRow);
			params.add(pageRows);
		}
		
		//将集合转为数组
		Object[] array=params.toArray(new Object[params.size()]);
		List<OrderInfoDomain> orderInfoByCode=DBUtil.exeQuery(sql,OrderInfoDomain.class, array);
		System.out.println(orderInfoByCode.size());
		return orderInfoByCode;
	}

	@Override
	public List<OrderTypeDomain> queryOrderType() {
		String sql="select * from order_type where 1=1";
		List<OrderTypeDomain> orderTypes = DBUtil.exeQuery(sql,OrderTypeDomain.class);
		return orderTypes;
	}

	@Override
	public List<OrderCountDomain> queryOrderCount() {
		String sql="select * from order_count where 1=1";
		List<OrderCountDomain> orderCount = DBUtil.exeQuery(sql, OrderCountDomain.class);
		return orderCount;
	}

	@Override
	public int countOrder(String orderCode) {
		List<Object> params = new ArrayList<>();
		String sql = "select * from order_info where 1 = 1 and data_state = 1";
		if(orderCode != null && !"".equals(orderCode)) {
			params.add("%" + orderCode + "%");
			sql += " and order_code like ? ";
		}
		
		//将集合转换成数组
		Object[] array = params.toArray(new Object[params.size()]);
		List<OrderInfoDomain> orderResult = DBUtil.exeQuery(sql, OrderInfoDomain.class , array);
		if(orderResult  != null) {
			return orderResult .size();
		}
		return 0;
	}

	@Override
	public List<TreatStateDomain> queryTreatState() {
		String sql="select * from treat_state where 1=1";
		List<TreatStateDomain> treatStates = DBUtil.exeQuery(sql, TreatStateDomain.class);
		return treatStates;
	}

	@Override
	public Integer updateTreatState(OrderInfoDomain orderInfoDomain) {
		String sql = "update order_info set MEMO = ?, TREAT_STATE = ? ,DATA_STATE=? where ORDER_ID = ? ";
		List<Object> params = new ArrayList<>();
		params.add(orderInfoDomain.getMemo());
		params.add(orderInfoDomain.getTreatState());
		params.add(orderInfoDomain.getDataState());
		params.add(orderInfoDomain.getOrderId());
		Object[] array = params.toArray(new Object[params.size()]);
		int index = DBUtil.exeUpdate(sql, array);
		return index;
	}

	@Override
	public List<OrderInfoDomain> queryOrderInfoById(Integer orderId) {
		String sql="select * from order_info where data_state=1 and order_id=?";
		 List<OrderInfoDomain> orderInfoResult= DBUtil.exeQuery(sql, OrderInfoDomain.class, orderId);
		 return orderInfoResult;
	}

	@Override
	public Integer addOrderInfo(OrderInfoDomain orderInfoDomain) {
		String sql = "insert into order_info(ORDER_CODE,DOC_ID,CUST_ID,ORDER_TYPE,PRICE,ORDER_COUNT,TREAT_STATE,DATA_STATE) "
				+ "values(?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(orderInfoDomain.getOrderCode());
		params.add(orderInfoDomain.getDocId());
		params.add(orderInfoDomain.getCustId());
		params.add(orderInfoDomain.getOrderType());
		params.add(orderInfoDomain.getPrice());
		params.add(orderInfoDomain.getOrderCount());
		params.add(orderInfoDomain.getTreatState());
		params.add(orderInfoDomain.getDataState());
		
		Object[] array = params.toArray(new Object[params.size()]);
		int index = DBUtil.exeUpdate(sql, array);
		return index;
	}
	

}
