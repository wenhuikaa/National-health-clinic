/**
 * 
 */
package com.hospital.service.impl;

import java.util.List;

import com.hospital.dao.ICustomerDao;
import com.hospital.dao.impl.CustomerDaoImpl;
import com.hospital.model.CustomerDomain;
import com.hospital.service.ICustomerService;


public class CustomerServiceImpl implements ICustomerService{

	@Override
	public List<CustomerDomain> queryCustomerList() {
		ICustomerDao customerDao = new CustomerDaoImpl();
		return customerDao.queryCustomerList();
	}

}
