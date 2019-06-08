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
public class OrderInfoDomain {
	/**ID,����*/
	private Integer orderId;
	
	/**ԤԼ����*/
	private String orderCode;
	
	/**ԤԼҽʦID*/
	private Integer docId;
	
	/**��ϵ��ID*/
	private Integer custId;
	
	/**ԤԼ����(1-ר������)*/
	private Integer orderType;
	
	/**ԤԼ����*/
	private BigDecimal price;
	
	/**��/����(1-���2-����)*/
	private Integer orderCount;
	
	/**��ע*/
	private String memo;
	
	/**ԤԼ����*/
	private Date orderDate;
	
	/**��������*/
	private Date treatDate;
	
	/**����״̬(1-�����2-�Ѿ���)*/
	private Integer treatState;

	/**����ʱ��*/
	private Date createdDate;
	
	/**����޸�ʱ��*/
	private Date modifyDate;
	
	/**ϵͳ״̬*/
	private Integer dataState;
}
