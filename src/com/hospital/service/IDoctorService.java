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
	
	 //�ж����ҽʦ�Ƿ�ɹ�
	 Boolean addDoctor(DoctorDomain doctorDomain);
	 
	 //��ȡ���е�ҽʦְ��
	 List<DoctorTitleDomain> queryDoctorTitleList();
	 
	 //ɾ��ҽʦ
	 Integer deleteDoctor(Integer docId);
	 
	//�༭ҽʦ������docId��ѯ��������
	 DoctorDomain getDoctor(Integer docId);
		
	//�޸�ҽʦ��Ϣ
	Integer updateDoctor(DoctorDomain doctorDomain);
	
	//��������ѯҽʦ�б�
	List<DoctorDomain> getAllDoctors();
}
