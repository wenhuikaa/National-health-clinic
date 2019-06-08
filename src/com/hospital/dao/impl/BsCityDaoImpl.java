package com.hospital.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;
import com.hospital.dao.IBsCityDao;
import com.hospital.model.HospitalDomain;
import com.hospital.util.DBUtil;

public class BsCityDaoImpl implements IBsCityDao{


	/**
	 * ��ȡ����ʡ
	 */
	@Override
	public List<BsProvinceDomain> queryProvince() {
		String sql = "select * from bs_province where 1 = 1";
		List<BsProvinceDomain> provinceResult = DBUtil.exeQuery(sql, BsProvinceDomain.class);
		return provinceResult;
	}

	/**
	 * ����ʡ�����ȡָ����ʡ
	 */
	@Override
	public BsProvinceDomain getProvince(String provinceCode) {
		String sql = "select * from bs_province where 1 = 1 ";
		List<Object> params = new ArrayList<>();
		if(provinceCode != null && !"".equals(provinceCode)) {
			params.add(provinceCode);
			sql += " and province_code = ? ";
		}
		List<BsProvinceDomain> provinceResult = DBUtil.exeQuery(sql, BsProvinceDomain.class , provinceCode);
		if(provinceResult != null && provinceResult.size() >0) {
			return provinceResult.get(0);
		}
		return null;
	}

	/**
	 * ����ʡ�����ȡָ���ĳ���
	 */
	@Override
	public List<BsCityDomain> queryCity(String provinceCode) {
		String sql = "select * from bs_city where 1 = 1 ";
		List<Object> params = new ArrayList<>();
		if(provinceCode != null && !"".equals(provinceCode)) {
			params.add(provinceCode);
			sql += " and province_code = ? ";
		}
		List<BsCityDomain> cityResult = DBUtil.exeQuery(sql, BsCityDomain.class , provinceCode);
		return cityResult;
	}

	/**
	 * �����д����ȡ��
	 */
	@Override
	public BsCityDomain getCity(String cityCode) {
		String sql = "select * from bs_city where 1 = 1 ";
		List<Object> params = new ArrayList<>();
		if(cityCode != null && !"".equals(cityCode)) {
			params.add(cityCode);
			sql += " and city_code = ? ";
		}
		List<BsCityDomain> cityResult = DBUtil.exeQuery(sql, BsCityDomain.class , cityCode);
		if(cityResult != null && cityResult.size() >0) {
			return cityResult.get(0);
		}
		return null;
	}

	/**
	 * �����д����ȡ��
	 */
	@Override
	public List<BsAreaDomain> queryArea(String cityCode) {
		String sql = "select * from bs_area where 1 = 1 ";
		List<Object> params = new ArrayList<>();
		if(cityCode != null && !"".equals(cityCode)) {
			params.add(cityCode);
			sql += " and city_code = ? ";
		}
		List<BsAreaDomain> areaResult = DBUtil.exeQuery(sql, BsAreaDomain.class , cityCode);
		return areaResult;
	}

	/**
	 * �����������ȡ��
	 */
	@Override
	public BsAreaDomain getArea(String areaCode) {
		String sql = "select * from bs_area where 1 = 1 ";
		List<Object> params = new ArrayList<>();
		if(areaCode != null && !"".equals(areaCode)) {
			params.add(areaCode);
			sql += " and area_code = ? ";
		}
		List<BsAreaDomain> areaResult = DBUtil.exeQuery(sql, BsAreaDomain.class , areaCode);
		if(areaResult != null && areaResult.size() >0) {
			return areaResult.get(0);
		}
		return null;
	}
	
	/**
	 * ����ʡ���뼯�ϻ�ȡʡ
	 */
	@Override
	public List<BsProvinceDomain> getProvince(HashSet<String> provinceCodes) {
		String sql = "select * from bs_province where 1 = 1 and province_code in (";
		int size = provinceCodes.size();
		
		for (int i =0 ; i < size ; i++) {
				sql += " ? ,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";
		//������ת��������
		Object[] array = provinceCodes.toArray(new Object[provinceCodes.size()]);
		List<BsProvinceDomain> provinceResult = DBUtil.exeQuery(sql, BsProvinceDomain.class , array);
		return provinceResult;
	}
	
	/**
	 * �����д��뼯�ϻ�ȡ��
	 */
	@Override
	public List<BsCityDomain> getCity(HashSet<String> cityCodes) {
		String sql = "select * from bs_city where 1 = 1 and city_code in (";
		int size = cityCodes.size();
		
		for (int i =0 ; i < size ; i++) {
				sql += " ? ,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";
		//������ת��������
		Object[] array = cityCodes.toArray(new Object[cityCodes.size()]);
		List<BsCityDomain> cityResult = DBUtil.exeQuery(sql, BsCityDomain.class , array);
		return cityResult;
	}
	
	/**
	 * ���������뼯�ϻ�ȡ��
	 */
	@Override
	public List<BsAreaDomain> getArea(HashSet<String> areaCodes) {
		String sql = "select * from bs_area where 1 = 1 and area_code in (";
		int size = areaCodes.size();
		
		for (int i =0 ; i < size ; i++) {
				sql += " ? ,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";
		//������ת��������
		Object[] array = areaCodes.toArray(new Object[areaCodes.size()]);
		List<BsAreaDomain> areaResult = DBUtil.exeQuery(sql, BsAreaDomain.class , array);
		return areaResult;
	}

}
