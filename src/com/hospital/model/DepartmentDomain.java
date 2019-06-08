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
	/**科室ID,自增*/
	private Integer deptId;
	/**科室名称*/
	private String deptName;
	/**所属医馆ID*/
	private String hosId;
	/**科室图片地址*/
	private String imgUrl;
	/**备注*/
	private String memo;
	/**创建时间*/
	private Date createdDate;
	/**最后修改时间*/
	private Date modifyDate;
	/**系统状态*/
	private Integer dataState;
}
