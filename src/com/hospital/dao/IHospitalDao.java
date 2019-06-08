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
	
	//����ҽ��
	int insertHospital(HospitalDomain hospitalDomain);
	
	//��ȡ���hosId
	Integer getHospitalMaxId();
	
	//ɾ��ҽ��
	Integer deleteHospital(Integer hosId);
	
	//�༭ǰ�Ȳ�ѯ
	HospitalDomain getHospital(Integer hosId);
	
	Integer updateHospital(HospitalDomain hospitalDomain);
	
}
