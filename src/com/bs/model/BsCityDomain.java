/**
 * 
 */
package com.bs.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BsCityDomain {
	/**ID,����*/
	private Integer cityId;
	/**�д���*/
	private String cityCode;
	/**������*/
	private String cityName;
	/**������*/
	private String shortName;
	/**ʡ����*/
	private String provinceCode;
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
