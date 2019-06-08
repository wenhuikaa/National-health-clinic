/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.DoctorDomain;
import com.hospital.model.SysUserDomain;

public interface ISysUserDao {
	//登录验证
	SysUserDomain querySysUser(String userName,String password);
	
	//编辑信息
	Integer updateUser(SysUserDomain sysUserDomain);
}
