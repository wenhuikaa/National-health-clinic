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
	
	/**ϵͳ�û�ID,����*/
	private Integer userId;
	/**�û�����*/
	private String userCode;
	/**�û���*/
	private String userName;
	/**����*/
	private String password;
	/**��ʵ����*/
	private String realName;
	/**�û�״̬(1-���á�-1-����)*/
	private Integer userStatus;
	/**�û�����(1-��������Ա��2-��ͨ����Ա)*/
	private Integer userType;
	/**��ע*/
	private String memo;
	/**����ʱ��*/
	private Date createdDate;
	/**����޸�ʱ��*/
	private Date modifyDate;
	/**ϵͳ״̬*/
	private Integer dataState;
	
}
