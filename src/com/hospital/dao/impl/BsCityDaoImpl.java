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
	 * 获取所有省
	 */
	@Override
	public List<BsProvinceDomain> queryProvince() {
		String sql = "select * from bs_province where 1 = 1";
		List<BsProvinceDomain> provinceResult = DBUtil.exeQuery(sql, BsProvinceDomain.class);
		return provinceResult;
	}

	/**
	 * 根据省代码获取指定的省
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
	 * 根据省代码获取指定的城市
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
	 * 根据市代码获取市
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
	 * 根据市代码获取区
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
	 * 根据区代码获取区
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
	 * 根据省代码集合获取省
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
		//将集合转换成数组
		Object[] array = provinceCodes.toArray(new Object[provinceCodes.size()]);
		List<BsProvinceDomain> provinceResult = DBUtil.exeQuery(sql, BsProvinceDomain.class , array);
		return provinceResult;
	}
	
	/**
	 * 根据市代码集合获取市
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
		//将集合转换成数组
		Object[] array = cityCodes.toArray(new Object[cityCodes.size()]);
		List<BsCityDomain> cityResult = DBUtil.exeQuery(sql, BsCityDomain.class , array);
		return cityResult;
	}
	
	/**
	 * 根据区代码集合获取区
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
		//将集合转换成数组
		Object[] array = areaCodes.toArray(new Object[areaCodes.size()]);
		List<BsAreaDomain> areaResult = DBUtil.exeQuery(sql, BsAreaDomain.class , array);
		return areaResult;
	}

}
