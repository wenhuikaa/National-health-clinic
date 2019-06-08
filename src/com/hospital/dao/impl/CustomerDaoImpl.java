/**
 * 
 */
package com.hospital.dao.impl;

import java.util.List;

import com.hospital.dao.ICustomerDao;
import com.hospital.model.CustomerDomain;
import com.hospital.util.DBUtil;

public class CustomerDaoImpl implements ICustomerDao{

	@Override
	public List<CustomerDomain> queryCustomerList() {
		String sql="select * from customer where 1=1 and data_state=1";
		List<CustomerDomain> customerList = DBUtil.exeQuery(sql, CustomerDomain.class);
	    return customerList;
	}

}
