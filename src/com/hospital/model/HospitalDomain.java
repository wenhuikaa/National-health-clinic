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
	/**医馆ID,自增*/
	private Integer hosId;
	/**医馆名称*/
	private String hosName;
	/**所在省代码*/
	private String hosProvince;
	/**所在市代码*/
	private String hosCity;
	/**所在区代码*/
	private String hosArea;
	/**详细地址*/
	private String hosAddr;
	/**医师总数*/
	private Integer doctorNum;
	/**科室总数*/
	private Integer deptNum;
	/**备注*/
	private String memo;
	/**创建时间*/
	private Date createdDate;
	/**最后修改时间*/
	private Date modifyDate;
	/**系统状态*/
	private Integer dataState;	
	
}
