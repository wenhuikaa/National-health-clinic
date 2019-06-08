package com.hospital.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTypeDomain {
	/**
	 * 门诊Id
	 */
	private Integer orderTypeId;
	
	/**
	 * 门诊类型
	 */
	private String orderType;
}
