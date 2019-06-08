package com.hospital.service.impl;

import java.util.List;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.bs.model.BsProvinceDomain;
import com.hospital.dao.IBsCityDao;
import com.hospital.dao.impl.BsCityDaoImpl;
import com.hospital.service.IBsCityService;

public class BsCityServiceImpl implements IBsCityService{

	@Override
	public List<BsProvinceDomain> queryProvince() {
		IBsCityDao bsCityDao=new BsCityDaoImpl();
		return bsCityDao.queryProvince();
	}

	@Override
	public List<BsCityDomain> queryCity(String provinceCode) {
		IBsCityDao bsCityDao=new BsCityDaoImpl();
		return bsCityDao.queryCity(provinceCode);
	}

	@Override
	public List<BsAreaDomain> queryArea(String cityCode) {
		IBsCityDao bsCityDao=new BsCityDaoImpl();
		return bsCityDao.queryArea(cityCode);
	}

}
