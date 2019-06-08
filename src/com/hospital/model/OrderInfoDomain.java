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
	/**ID,自增*/
	private Integer orderId;
	
	/**预约单号*/
	private String orderCode;
	
	/**预约医师ID*/
	private Integer docId;
	
	/**联系人ID*/
	private Integer custId;
	
	/**预约类型(1-专家门诊)*/
	private Integer orderType;
	
	/**预约费用*/
	private BigDecimal price;
	
	/**初/复诊(1-初诊、2-复诊)*/
	private Integer orderCount;
	
	/**备注*/
	private String memo;
	
	/**预约日期*/
	private Date orderDate;
	
	/**就诊日期*/
	private Date treatDate;
	
	/**就诊状态(1-待就诊、2-已就诊)*/
	private Integer treatState;

	/**创建时间*/
	private Date createdDate;
	
	/**最后修改时间*/
	private Date modifyDate;
	
	/**系统状态*/
	private Integer dataState;
}
