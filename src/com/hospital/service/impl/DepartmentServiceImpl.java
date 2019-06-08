/**
 * 
 */
package com.hospital.service.impl;

import java.util.List;

import com.hospital.dao.IDepartmentDao;
import com.hospital.dao.impl.DepartmentDaoImpl;
import com.hospital.model.DepartmentDomain;
import com.hospital.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService{

	@Override
	public List<DepartmentDomain> queryAllDepartment() {
		IDepartmentDao deptDao=new DepartmentDaoImpl();
		return deptDao.queryAllDepartment();
	}

	@Override
	public Integer insertDeptHosRef(String[] depts, Integer hosId) {
		IDepartmentDao deptDao = new DepartmentDaoImpl();
		return deptDao.insertDeptHosRef(depts , hosId);
	}

	@Override
	public List<Integer> queryDeptHosRef(Integer hosId) {
		IDepartmentDao deptDao = new DepartmentDaoImpl();
		return deptDao.queryDeptHosRef(hosId);
	}

	@Override
	public Integer deleteDeptHosRef(Integer hosId) {
		IDepartmentDao deptDao = new DepartmentDaoImpl();
		return deptDao.deleteDeptHosRef(hosId);
	}
}
