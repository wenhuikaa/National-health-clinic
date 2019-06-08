/**
 * 
 */
package com.hospital.service;

import java.util.List;

import com.hospital.model.SysUserDomain;

public interface ISysUserService {
	
	SysUserDomain querySysUser(String userName, String password);
	
	Integer updateUser(SysUserDomain sysUserDomain);
}
