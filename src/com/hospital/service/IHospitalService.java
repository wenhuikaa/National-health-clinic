/**
 * 
 */
package com.hospital.service;


import java.util.List;

import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;


public interface IHospitalService {
	//查询医馆列表
	List<HospitalDomain> queryHospitalList(String hosName,String hosAddr,PageDomain pageDomain);
	
	List<HospitalDomain> getAllHospital();
	
	//添加医馆
	Integer addHospital(HospitalDomain hospitalDomain);
	
	//获取最大hosId
	Integer getHospitalMaxId();
	
	//删除医馆
	Integer deleteHospital(Integer hosId);
	
	//编辑医馆，根据hosId查询单条数据
	HospitalDomain getHospital(Integer hosId);
	
	//修改医馆信息
	Integer updateHospital(HospitalDomain hospitalDomain);
}
