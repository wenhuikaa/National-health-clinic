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
	
	//���ҽʦ
	int insertDoctor(DoctorDomain doctorDomain);
	
	//��ȡ���е�ְ��
	List<DoctorTitleDomain> queryDoctorTitleList();
	
	//ɾ��ҽʦ
	Integer deleteDoctor(Integer docId);
	
	//�༭ǰ�ȸ���docId��ѯҽʦ
	DoctorDomain getDoctor(Integer docId);
	
	//�༭ҽʦ
	Integer updateDoctor(DoctorDomain doctorDomain);
	
	//��������ѯҽʦ�б�
	List<DoctorDomain> getAllDoctors();
}
