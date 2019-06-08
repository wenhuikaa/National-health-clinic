/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.DepartmentDomain;
import com.hospital.model.DeptHosRefDomain;

public interface IDepartmentDao {
	//��ѯ����
	List<DepartmentDomain> queryAllDepartment();
	
	Integer insertDeptHosRef(String[] depts, Integer hosId);
	
	//����hosId��ѯDeptHosRefDomain
	List<Integer> queryDeptHosRef(Integer hosId);
	
	//����hosIdɾ������
	Integer deleteDeptHosRef(Integer hosId);
}
