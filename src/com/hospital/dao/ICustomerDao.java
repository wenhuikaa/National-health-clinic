/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.CustomerDomain;

public interface ICustomerDao {
	
	//查询客户信息
	List<CustomerDomain> queryCustomerList();
}
