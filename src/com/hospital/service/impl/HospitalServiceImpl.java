package com.hospital.service.impl;

import java.util.List;

import com.hospital.dao.IHospitalDao;
import com.hospital.dao.impl.HospitalDaoImpl;
import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;
import com.hospital.service.IHospitalService;

public class HospitalServiceImpl implements IHospitalService{

	/**
	 * 获取医馆列表
	 */
	@Override
	public List<HospitalDomain> queryHospitalList(String hosName, String hosAddr,PageDomain pageDomain) {
		IHospitalDao hospitalDao = new HospitalDaoImpl();
		
		//总记录数
		int totalCount = hospitalDao.countHospital(hosName, hosAddr);
		pageDomain.setTotalCount(totalCount);
		int totalPage = pageDomain.getTotalPage();
		//分页算法
//		当前页为第1页，每页显示4行，从0行开始
//		当前页为第2页，每页显示4行，从4行开始
//		当前页为第3页，每页显示4行，从8行开始
		int currentPage = pageDomain.getCurrentPage();
		int pageCount = pageDomain.getPageCount();
		//System.out.println(currentPage);
		//System.out.println(totalPage);
		if(currentPage > totalPage) {
			currentPage = totalPage;
		}else if(currentPage < 1){
			currentPage = 1;
		}
		pageDomain.setCurrentPage(currentPage);
		int startRow = (currentPage - 1) * pageCount;
		List<HospitalDomain> hospitalList = hospitalDao.queryHospitalList(hosName, hosAddr , startRow, pageCount);
		return hospitalList;
	}

	/**
	 * 判断添加医馆是否成功,保存医馆数据
	 * 
	 */
	@Override
	public Integer addHospital(HospitalDomain hospitalDomain) {
		IHospitalDao hd=new HospitalDaoImpl();
		int index = hd.insertHospital(hospitalDomain);
		System.out.println(index);
		System.out.println(hospitalDomain.getHosId());
		return index;
	}

	@Override
	public Integer getHospitalMaxId() {
		IHospitalDao hd=new HospitalDaoImpl();
		return hd.getHospitalMaxId();
	}

	/**
	 * 删除医馆
	 */
	@Override
	public Integer deleteHospital(Integer hosId) {
		IHospitalDao hd = new HospitalDaoImpl();
		return hd.deleteHospital(hosId);
	}

	@Override
	public HospitalDomain getHospital(Integer hosId) {
		IHospitalDao hd = new HospitalDaoImpl();
		return hd.getHospital(hosId);
	}

	@Override
	public Integer updateHospital(HospitalDomain hospitalDomain) {
		IHospitalDao hd = new HospitalDaoImpl();
		return hd.updateHospital(hospitalDomain);
	}

	@Override
	public List<HospitalDomain> getAllHospital() {
		IHospitalDao hd = new HospitalDaoImpl();
		return hd.getAllHospital();
	}
}

