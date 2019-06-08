/**
 * 
 */
package com.bs.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BsAreaDomain {
	/**ID,����*/
	private Integer areaId;
	/**������*/
	private String areaCode;
	/**������*/
	private String areaName;
	/**������*/
	private String shortName;
	/**�д���*/
	private String cityCode;
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
