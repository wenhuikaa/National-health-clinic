/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SysUserDomain {
	
	/**系统用户ID,自增*/
	private Integer userId;
	/**用户代码*/
	private String userCode;
	/**用户名*/
	private String userName;
	/**密码*/
	private String password;
	/**真实姓名*/
	private String realName;
	/**用户状态(1-启用、-1-禁用)*/
	private Integer userStatus;
	/**用户类型(1-超级管理员、2-普通管理员)*/
	private Integer userType;
	/**备注*/
	private String memo;
	/**创建时间*/
	private Date createdDate;
	/**最后修改时间*/
	private Date modifyDate;
	/**系统状态*/
	private Integer dataState;
	
}
