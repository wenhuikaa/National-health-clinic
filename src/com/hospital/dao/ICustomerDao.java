/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.CustomerDomain;

public interface ICustomerDao {
	
	//��ѯ�ͻ���Ϣ
	List<CustomerDomain> queryCustomerList();
}