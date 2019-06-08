/**
 * 
 */
package com.bs.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BsProvinceDomain{
	/**ID,����*/
	private Integer provinceId;
	/**ʡ����*/
	private String provinceCode;
	/**ʡ����*/
	private String provinceName;
	/**������*/
	private String shortName;
	/**����*/
	private String lng;
	/**γ��*/
	private String lat;
	/**����*/
	private Integer sort;
	/**��ע*/
	private String memo;
	/**����ʱ��*/
	private Date createdDate;
	/**����޸�ʱ��*/
	private Date modifyDate;
	/**ϵͳ״̬*/
	private Integer dataState;
}
