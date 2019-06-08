/**
 * 
 */
package com.hospital.service;

import java.util.List;

import com.hospital.model.CustomerDomain;

public interface ICustomerService {
	
	List<CustomerDomain> queryCustomerList();
}
