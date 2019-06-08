package com.hospital.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;
import com.hospital.dao.IBsCityDao;
import com.hospital.dao.IHospitalDao;
import com.hospital.model.HospitalDomain;
import com.hospital.util.DBUtil;


public class HospitalDaoImpl implements IHospitalDao {

	/**
	 * 获取医馆列表
	 */
	@Override
	public List<HospitalDomain> queryHospitalList(String hosName, String hosAddr , int startRow , int pageRows) {
		List<Object> params = new ArrayList<>();
		String sql = "select * from hospital where 1 = 1 and data_state=1";
		if(hosName != null && !"".equals(hosName)) {
			params.add("%" + hosName + "%");
			sql += " and hos_name like ? ";
		}
		
		if(hosAddr != null && !"".equals(hosAddr)) {
			params.add("%" + hosAddr + "%");
			sql += " and hos_addr like ? ";
		}
		
		if( pageRows > 0) {
			//分页
			sql+= " limit ? , ?";
			params.add(startRow);
			params.add(pageRows);
		}
		
		//将集合转换成数组
		Object[] array = params.toArray(new Object[params.size()]);
		List<HospitalDomain> hospitalResult = DBUtil.exeQuery(sql, HospitalDomain.class , array);
		
		//用于存放code集合
		HashSet<String> provinceCodes = new HashSet<String>();
		HashSet<String> cityCodes = new HashSet<String>();
		HashSet<String> areaCodes = new HashSet<String>();
		
		//将医馆中的省市区code全部取出，装到set集合中进行统一查询
		for (HospitalDomain hospital : hospitalResult) {
			provinceCodes.add(hospital.getHosProvince());
			cityCodes.add(hospital.getHosCity());
			areaCodes.add(hospital.getHosArea());
		}
		IBsCityDao bsCityDao = new BsCityDaoImpl();
		//根据set集合中的省代码进行查询，并返回，以code，name的形式封装至Map中，方便后续使用
		List<BsProvinceDomain> provinces = bsCityDao.getProvince(provinceCodes);
		Map<String,String> provinceMap = new HashMap<>();
		if(provinces != null) {
			for (BsProvinceDomain temp : provinces) {
				provinceMap.put(temp.getProvinceCode(), temp.getProvinceName());
			}
		}
		
		//根据set集合中的市代码进行查询，并返回，以code，name的形式封装至Map中，方便后续使用
		Map<String,String> cityMap = new HashMap<>();
		List<BsCityDomain> citys = bsCityDao.getCity(cityCodes);
		if(citys != null) {
			for (BsCityDomain temp : citys) {
				cityMap.put(temp.getCityCode(), temp.getCityName());
			}
		}
		
		//根据set集合中的区代码进行查询，并返回，以code，name的形式封装至Map中，方便后续使用
		List<BsAreaDomain> areas = bsCityDao.getArea(areaCodes);
		Map<String,String> areaMap = new HashMap<>();
		if(areas != null) {
			for (BsAreaDomain temp : areas) {
				areaMap.put(temp.getAreaCode(), temp.getAreaName());
			}
		}
		
		//统一再次进行封装设置省市区
		for (HospitalDomain hospital : hospitalResult) {
			hospital.setHosProvince(provinceMap.get(hospital.getHosProvince()));
			hospital.setHosCity(cityMap.get(hospital.getHosCity()));
			hospital.setHosArea(areaMap.get(hospital.getHosArea()));
		}
		
		return hospitalResult;
	}
	
	


	@Override
	public int countHospital(String hosName, String hosAddr) {
		List<Object> params = new ArrayList<>();
		String sql = "select * from hospital where 1 = 1 and data_state = 1";
		if(hosName != null && !"".equals(hosName)) {
			params.add("%" + hosName + "%");
			sql += " and hos_name like ? ";
		}
		
		if(hosAddr != null && !"".equals(hosAddr)) {
			params.add("%" + hosAddr + "%");
			sql += " and hos_addr like ? ";
		}
		
		//将集合转换成数组
		Object[] array = params.toArray(new Object[params.size()]);
		List<HospitalDomain> hospitalResult = DBUtil.exeQuery(sql, HospitalDomain.class , array);
		if(hospitalResult != null) {
			return hospitalResult.size();
		}
		return 0;
	}


	@Override
	public int insertHospital(HospitalDomain hospitalDomain) {
		String sql = "insert into hospital(HOS_NAME,HOS_PROVINCE,HOS_CITY,HOS_AREA,HOS_ADDR,DOCTOR_NUM,DEPT_NUM,MEMO,DATA_STATE) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(hospitalDomain.getHosName());
		params.add(hospitalDomain.getHosProvince());
		params.add(hospitalDomain.getHosCity());
		params.add(hospitalDomain.getHosArea());
		params.add(hospitalDomain.getHosAddr());
		params.add(hospitalDomain.getDoctorNum());
		params.add(hospitalDomain.getDeptNum());
		params.add(hospitalDomain.getMemo());
		params.add(hospitalDomain.getDataState());
		Object[] array = params.toArray(new Object[params.size()]);
		int index = DBUtil.exeUpdate(sql, array);
		return index;
	}




	@Override
	public Integer getHospitalMaxId() {
		List<HospitalDomain> queryHospitalList = queryHospitalList(null, null, 0, 0);
		HospitalDomain hospitalDomain = queryHospitalList.get(queryHospitalList.size()-1);
		return hospitalDomain.getHosId();
	}




	@Override
	public Integer deleteHospital(Integer hosId) {
		//删除操作，一般不做物理删除，而是做逻辑删除
		String sql = "update hospital set data_state = -1 where hos_id = ?";
		int index = DBUtil.exeUpdate(sql, hosId);
		return index;
	}


	@Override
	public HospitalDomain getHospital(Integer hosId) {
		String sql = "select * from hospital where 1 = 1 and hos_id = ?";
		List<HospitalDomain> hosResult = DBUtil.exeQuery(sql, HospitalDomain.class, hosId);
		if(hosResult != null && hosResult.size() > 0) {
			return hosResult.get(0);
		}
		return null;
	}




	@Override
	public Integer updateHospital(HospitalDomain hospitalDomain) {
		String sql = "update hospital set HOS_NAME = ?, HOS_PROVINCE = ? ,HOS_CITY = ? ,HOS_AREA = ? ,HOS_ADDR = ? ,MEMO = ? ,DATA_STATE = ? where HOS_ID = ? ";
		List<Object> params = new ArrayList<>();
		params.add(hospitalDomain.getHosName());
		params.add(hospitalDomain.getHosProvince());
		params.add(hospitalDomain.getHosCity());
		params.add(hospitalDomain.getHosArea());
		params.add(hospitalDomain.getHosAddr());
		params.add(hospitalDomain.getMemo());
		params.add(hospitalDomain.getDataState());
		params.add(hospitalDomain.getHosId());
		Object[] array = params.toArray(new Object[params.size()]);
		int index = DBUtil.exeUpdate(sql, array);
		return index;
	}




	@Override
	public List<HospitalDomain> getAllHospital() {
		String sql="select * from hospital where 1=1";
		return DBUtil.exeQuery(sql,HospitalDomain.class);
	}

}