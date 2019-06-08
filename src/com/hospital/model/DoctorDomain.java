/**
 * 
 */
package com.hospital.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDomain {
	/**ҽʦID,����*/
	private Integer docId;
	
	/**ҽʦ����*/
	private String docName;
	
	/**ְ��*/
	private String docTitle;
	
	/**ҽʦ����(1-ҽʦ��2-����)*/
	private String docType;
	
	/**����ҽ��*/
	private String hosName;
	
	/**��������*/
	private String deptName;
	
	/**�ó�*/
	private String expertDesc;
	
	/**ԤԼ����*/
	private BigDecimal orderPrice;
	
	/**��ע*/
	private String memo;
	
	/**����ʱ��*/
	private Date createdDate;
	
	/**����޸�ʱ��*/
	private Date modifyDate;
	
	/**ϵͳ״̬*/
	private Integer dataState;
}
