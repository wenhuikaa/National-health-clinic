/**
 * 
 */
package com.hospital.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hospital.dao.IDepartmentDao;
import com.hospital.model.DepartmentDomain;
import com.hospital.model.DeptHosRefDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.util.DBUtil;

public class DepartmentDaoImpl implements IDepartmentDao{
	
	/**
	 * 查询所有科室
	 */
	@Override
	public List<DepartmentDomain> queryAllDepartment() {
		String sql="select * from department where 1=1";
		List<DepartmentDomain> deptResult= DBUtil.exeQuery(sql,DepartmentDomain.class);
		return deptResult;
	}

	@Override
	public Integer insertDeptHosRef(String[] depts, Integer hosId) {
		String sql = "insert into dept_hos_ref values(?,?)";
		for (String temp : depts) {
			int index = DBUtil.exeUpdate(sql,  temp , hosId);
			System.out.println("批量插入影响行数->" + index);
		}
		return 1;
	}

	@Override
	public List<Integer> queryDeptHosRef(Integer hosId) {
		List<Integer> deptIds = new ArrayList<>();
		String sql = "select * from dept_hos_ref where 1 = 1  and hos_id = ?";
		List<DeptHosRefDomain> refResult = DBUtil.exeQuery(sql, DeptHosRefDomain.class, hosId);
		if(refResult != null && refResult.size() > 0) {
			for (DeptHosRefDomain deptHosRefDomain : refResult) {
				deptIds.add(deptHosRefDomain.getDeptId());
			}
		}
		return deptIds;
	}
	
	@Override
	public Integer deleteDeptHosRef(Integer hosId) {
		String sql = "delete from dept_hos_ref where 1 = 1  and hos_id = ?";
		return DBUtil.exeUpdate(sql, hosId);
	}

}
