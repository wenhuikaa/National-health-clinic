/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class HospitalDomain {
	/**ҽ��ID,����*/
	private Integer hosId;
	/**ҽ������*/
	private String hosName;
	/**����ʡ����*/
	private String hosProvince;
	/**�����д���*/
	private String hosCity;
	/**����������*/
	private String hosArea;
	/**��ϸ��ַ*/
	private String hosAddr;
	/**ҽʦ����*/
	private Integer doctorNum;
	/**��������*/
	private Integer deptNum;
	/**��ע*/
	private String memo;
	/**����ʱ��*/
	private Date createdDate;
	/**����޸�ʱ��*/
	private Date modifyDate;
	/**ϵͳ״̬*/
	private Integer dataState;	
	
}
