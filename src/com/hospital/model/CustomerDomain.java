/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDomain {
	/**ԤԼ�ͻ�ID,����*/
	private Integer custId;
	/**�ͻ�����*/
	private String custName;
	/**�ͻ��ֻ���*/
	private String phone;
	/**����*/
	private String custDesc;
	/**����*/
	private Integer age;
	/**'*/
	private String sex;
	/**��ע*/
	private String memo;
	/**����ʱ��*/
	private Date createdDate;
	/**����޸�ʱ��*/
	private Date modifyDate;
	/**ϵͳ״̬*/
	private Integer dataState;	
}
