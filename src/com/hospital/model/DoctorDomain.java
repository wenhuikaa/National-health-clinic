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
	/**医师ID,自增*/
	private Integer docId;
	
	/**医师姓名*/
	private String docName;
	
	/**职称*/
	private String docTitle;
	
	/**医师类型(1-医师、2-助理)*/
	private String docType;
	
	/**所属医馆*/
	private String hosName;
	
	/**所属科室*/
	private String deptName;
	
	/**擅长*/
	private String expertDesc;
	
	/**预约费用*/
	private BigDecimal orderPrice;
	
	/**备注*/
	private String memo;
	
	/**创建时间*/
	private Date createdDate;
	
	/**最后修改时间*/
	private Date modifyDate;
	
	/**系统状态*/
	private Integer dataState;
}
