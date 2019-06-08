/**
 * 
 */
package com.hospital.dao;

import java.util.HashSet;
import java.util.List;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;
import com.hospital.model.HospitalDomain;

public interface IHospitalDao {
	List<HospitalDomain> queryHospitalList(String hosName,String hosAddr,int startRow,int pageRows);
	
	List<HospitalDomain> getAllHospital();
	
	int countHospital(String hosName,String hosAddr);
	
	//新增医馆
	int insertHospital(HospitalDomain hospitalDomain);
	
	//获取最大hosId
	Integer getHospitalMaxId();
	
	//删除医馆
	Integer deleteHospital(Integer hosId);
	
	//编辑前先查询
	HospitalDomain getHospital(Integer hosId);
	
	Integer updateHospital(HospitalDomain hospitalDomain);
	
}
