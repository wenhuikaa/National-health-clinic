/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDomain {
	/**����ID,����*/
	private Integer deptId;
	/**��������*/
	private String deptName;
	/**����ҽ��ID*/
	private String hosId;
	/**����ͼƬ��ַ*/
	private String imgUrl;
	/**��ע*/
	private String memo;
	/**����ʱ��*/
	private Date createdDate;
	/**����޸�ʱ��*/
	private Date modifyDate;
	/**ϵͳ״̬*/
	private Integer dataState;
}
