/**
 * 
 */
package com.hospital.service;

import java.util.List;

import com.hospital.model.DoctorDomain;
import com.hospital.model.DoctorTitleDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;

public interface IDoctorService {
	
	 List<DoctorDomain> queryDoctorList(String docName, String docTitle,PageDomain pageDomain);
	
	 //判断添加医师是否成功
	 Boolean addDoctor(DoctorDomain doctorDomain);
	 
	 //获取所有的医师职称
	 List<DoctorTitleDomain> queryDoctorTitleList();
	 
	 //删除医师
	 Integer deleteDoctor(Integer docId);
	 
	//编辑医师，根据docId查询单条数据
	 DoctorDomain getDoctor(Integer docId);
		
	//修改医师信息
	Integer updateDoctor(DoctorDomain doctorDomain);
	
	//无条件查询医师列表
	List<DoctorDomain> getAllDoctors();
}
