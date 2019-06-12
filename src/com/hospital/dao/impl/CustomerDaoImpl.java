/**
 * 
 */
package com.hospital.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hospital.dao.ICustomerDao;
import com.hospital.model.CustomerDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.util.DBUtil;

public class CustomerDaoImpl implements ICustomerDao{

	@Override
	public List<CustomerDomain> queryCustomerList() {
		String sql="select * from customer where 1=1 and data_state=1";
		List<CustomerDomain> customerList = DBUtil.exeQuery(sql, CustomerDomain.class);
	    return customerList;
	}

	@Override
	public Integer addCustomer(CustomerDomain customerDomain) {
		String sql = "insert into customer(CUST_NAME,PHONE,CUST_DESC,DATA_STATE) "
				+ "values(?,?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(customerDomain.getCustName());
		params.add(customerDomain.getPhone());
		params.add(customerDomain.getCustDesc());
		params.add(customerDomain.getDataState());
	
		Object[] array = params.toArray(new Object[params.size()]);
		int index = DBUtil.exeUpdate(sql, array);
		return index;
	}

	@Override
	public Integer getCustomerMaxId() {
		List<CustomerDomain> queryCustomerList = queryCustomerList();
		CustomerDomain customerDomain = queryCustomerList.get(queryCustomerList.size()-1);
		return customerDomain.getCustId();
	}

}
