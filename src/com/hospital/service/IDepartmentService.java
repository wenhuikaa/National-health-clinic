/**
 * 
 */
package com.hospital.service;

import java.util.List;

import com.hospital.model.DepartmentDomain;

public interface IDepartmentService {
	
	//查询科室
	List<DepartmentDomain> queryAllDepartment();
	
	//存储医馆与科室的对应关系
	Integer insertDeptHosRef(String[] depts,Integer hosId);
	
	List<Integer> queryDeptHosRef(Integer hosId);
	
	Integer deleteDeptHosRef(Integer hosId);
	
}
