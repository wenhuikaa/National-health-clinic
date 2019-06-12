/**
 * 
 */
package com.hospital.service.impl;

import java.util.List;

import com.hospital.dao.ISysUserDao;
import com.hospital.dao.impl.SysUserDaoImpl;
import com.hospital.model.SysUserDomain;
import com.hospital.service.ISysUserService;

public class SysUserServiceImpl implements ISysUserService{

	@Override
	public SysUserDomain querySysUser(String userName, String password){
		ISysUserDao userDao=new SysUserDaoImpl();
		SysUserDomain result=userDao.querySysUser(userName, password);
		return result;
	}

	@Override
	public Integer updateUser(SysUserDomain sysUserDomain) {
		ISysUserDao userDao=new SysUserDaoImpl();
		return userDao.updateUser(sysUserDomain);
	}

	@Override
	public Integer registerSysUser(SysUserDomain sysUserDomain) {
		ISysUserDao userDao=new SysUserDaoImpl();
		return userDao.registerSysUser(sysUserDomain);
	}



}
