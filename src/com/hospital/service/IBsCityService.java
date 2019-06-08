package com.hospital.service;

import java.util.HashSet;
import java.util.List;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;

public interface IBsCityService {
	//获取所有的省
	List<BsProvinceDomain> queryProvince();		
	
	//根据省代码获取所有市
	List<BsCityDomain> queryCity(String provinceCode);
	
	//根据市代码获取所有的区
	List<BsAreaDomain> queryArea(String cityCode);
			
}
