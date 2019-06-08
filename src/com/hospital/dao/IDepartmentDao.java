/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.DepartmentDomain;
import com.hospital.model.DeptHosRefDomain;

public interface IDepartmentDao {
	//查询科室
	List<DepartmentDomain> queryAllDepartment();
	
	Integer insertDeptHosRef(String[] depts, Integer hosId);
	
	//根据hosId查询DeptHosRefDomain
	List<Integer> queryDeptHosRef(Integer hosId);
	
	//根据hosId删除科室
	Integer deleteDeptHosRef(Integer hosId);
}
