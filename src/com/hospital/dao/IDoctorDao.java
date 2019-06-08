/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import com.hospital.model.DoctorDomain;
import com.hospital.model.DoctorTitleDomain;
import com.hospital.model.HospitalDomain;

public interface IDoctorDao {

	List<DoctorDomain> queryDoctorList(String docName,String docTitle, int startRow , int pageRows);
	
	int countDoctor(String docName,String docTitle);
	
	//添加医师
	int insertDoctor(DoctorDomain doctorDomain);
	
	//获取所有的职称
	List<DoctorTitleDomain> queryDoctorTitleList();
	
	//删除医师
	Integer deleteDoctor(Integer docId);
	
	//编辑前先根据docId查询医师
	DoctorDomain getDoctor(Integer docId);
	
	//编辑医师
	Integer updateDoctor(DoctorDomain doctorDomain);
	
	//无条件查询医师列表
	List<DoctorDomain> getAllDoctors();
}
