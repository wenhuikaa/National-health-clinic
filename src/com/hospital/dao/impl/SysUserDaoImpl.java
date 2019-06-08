/**
 * 
 */
package com.hospital.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hospital.dao.ISysUserDao;
import com.hospital.model.SysUserDomain;
import com.hospital.util.DBUtil;

public class SysUserDaoImpl implements ISysUserDao {

	@Override
	public SysUserDomain querySysUser(String userName, String password) {
//		data_state=1说明这条数据有效
		String sql = "select * from sys_user where user_name=? and password=? and data_state=1";
		List<SysUserDomain> result = DBUtil.exeQuery(sql, SysUserDomain.class, userName, password);
		if(result != null&&result.size()>0) {
			return result.get(0);
		}
		return null;
	}
	
	public static void main(String[] args) {
		SysUserDaoImpl sud=new SysUserDaoImpl();
		sud.querySysUser("admin", "123456");
	}

	@Override
	public Integer updateUser(SysUserDomain sysUserDomain) {
		String sql = "update sys_user set USER_NAME = ?,REAL_NAME = ? where USER_CODE = ? ";
		List<Object> params = new ArrayList<>();
		params.add(sysUserDomain.getUserName());
		params.add(sysUserDomain.getRealName());
		params.add(sysUserDomain.getUserCode());
		Object[] array = params.toArray(new Object[params.size()]);
		int index = DBUtil.exeUpdate(sql, array);
		return index;
	}
}
