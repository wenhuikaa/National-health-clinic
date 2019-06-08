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
	/**ID,自增*/
	private Integer areaId;
	/**区代码*/
	private String areaCode;
	/**区名称*/
	private String areaName;
	/**短名称*/
	private String shortName;
	/**市代码*/
	private String cityCode;
	/**经度*/
	private String lng;
	/**纬度*/
	private String lat;
	/**排序*/
	private Integer sort;
	/**备注*/
	private String memo;
	/**创建时间*/
	private Date createdDate;
	/**最后修改时间*/
	private Date modifyDate;
	/**系统状态*/
	private Integer dataState;
}
