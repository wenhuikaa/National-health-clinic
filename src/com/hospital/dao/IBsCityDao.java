package com.hospital.dao;

import java.util.HashSet;
import java.util.List;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;

public interface IBsCityDao {
	    //��ȡ���е�ʡ
		List<BsProvinceDomain> queryProvince();
		
		//����ʡ�����ȡʡ��
		BsProvinceDomain getProvince(String provinceCode);
		
		//���ݶ��ʡ�����ȡ���ʡ��
		List<BsProvinceDomain> getProvince(HashSet<String> provinceCodes);
		
		//����ʡ�����ȡ��
		List<BsCityDomain> queryCity(String provinceCode);
		
		//�����д����ȡ��
		BsCityDomain getCity(String cityCode);
		
		//���ݶ���д����ȡ��
		List<BsCityDomain> getCity(HashSet<String> cityCodes);
		
		//�����д����ȡ���е���
		List<BsAreaDomain> queryArea(String cityCode);
		
		//�����������ȡָ������
		BsAreaDomain getArea(String areaCode);
		
		//���ݶ���������ȡ��
		List<BsAreaDomain> getArea(HashSet<String> areaCodes);
}
