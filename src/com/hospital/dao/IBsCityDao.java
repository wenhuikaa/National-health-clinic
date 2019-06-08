package com.hospital.dao;

import java.util.HashSet;
import java.util.List;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;

public interface IBsCityDao {
	    //获取所有的省
		List<BsProvinceDomain> queryProvince();
		
		//根据省代码获取省份
		BsProvinceDomain getProvince(String provinceCode);
		
		//根据多个省代码获取多个省份
		List<BsProvinceDomain> getProvince(HashSet<String> provinceCodes);
		
		//根据省代码获取市
		List<BsCityDomain> queryCity(String provinceCode);
		
		//根据市代码获取市
		BsCityDomain getCity(String cityCode);
		
		//根据多个市代码获取市
		List<BsCityDomain> getCity(HashSet<String> cityCodes);
		
		//根据市代码获取所有的区
		List<BsAreaDomain> queryArea(String cityCode);
		
		//根据区代码获取指定的区
		BsAreaDomain getArea(String areaCode);
		
		//根据多个区代码获取区
		List<BsAreaDomain> getArea(HashSet<String> areaCodes);
}
