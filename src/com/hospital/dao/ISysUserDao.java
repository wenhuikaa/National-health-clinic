/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.DoctorDomain;
import com.hospital.model.SysUserDomain;

public interface ISysUserDao {
	//��¼��֤
	SysUserDomain querySysUser(String userName,String password);
	
	//�༭��Ϣ
	Integer updateUser(SysUserDomain sysUserDomain);
}
