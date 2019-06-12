package com.hospital.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页封装类
 */
@Setter
@Getter
public class PageDomain {

	private int currentPage = 1;//当前页
	
	private int pageCount = 4;//每页显示数量
	
	private int totalCount;//总记录数
	
	private int totalPage;//总页数
	
	//数据列表
	private List<HospitalDomain> pageData;
	
	private List<DoctorDomain> pageDoctor;
	
	private List<OrderInfoDomain> pageOrder;
	
	//选择的医馆
	private String hosName;
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		if(totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		}else {
			//+1 依据的就是%，如果取模不为0，则为有余数
			totalPage = totalCount / pageCount + 1;
		}
		return totalPage;
	}
	
}

