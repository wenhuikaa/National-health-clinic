/**
 * 
 */
package com.hospital.service;

import java.util.List;

import com.hospital.model.DepartmentDomain;

public interface IDepartmentService {
	
	//��ѯ����
	List<DepartmentDomain> queryAllDepartment();
	
	//�洢ҽ������ҵĶ�Ӧ��ϵ
	Integer insertDeptHosRef(String[] depts,Integer hosId);
	
	List<Integer> queryDeptHosRef(Integer hosId);
	
	Integer deleteDeptHosRef(Integer hosId);
	
}
