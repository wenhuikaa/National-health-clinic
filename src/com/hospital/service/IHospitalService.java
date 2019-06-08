/**
 * 
 */
package com.hospital.service;


import java.util.List;

import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;


public interface IHospitalService {
	//��ѯҽ���б�
	List<HospitalDomain> queryHospitalList(String hosName,String hosAddr,PageDomain pageDomain);
	
	List<HospitalDomain> getAllHospital();
	
	//���ҽ��
	Integer addHospital(HospitalDomain hospitalDomain);
	
	//��ȡ���hosId
	Integer getHospitalMaxId();
	
	//ɾ��ҽ��
	Integer deleteHospital(Integer hosId);
	
	//�༭ҽ�ݣ�����hosId��ѯ��������
	HospitalDomain getHospital(Integer hosId);
	
	//�޸�ҽ����Ϣ
	Integer updateHospital(HospitalDomain hospitalDomain);
}
