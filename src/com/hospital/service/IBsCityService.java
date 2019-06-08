package com.hospital.service;

import java.util.HashSet;
import java.util.List;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;

public interface IBsCityService {
	//��ȡ���е�ʡ
	List<BsProvinceDomain> queryProvince();		
	
	//����ʡ�����ȡ������
	List<BsCityDomain> queryCity(String provinceCode);
	
	//�����д����ȡ���е���
	List<BsAreaDomain> queryArea(String cityCode);
			
}
