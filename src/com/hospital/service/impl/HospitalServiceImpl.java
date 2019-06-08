package com.hospital.service.impl;

import java.util.List;

import com.hospital.dao.IHospitalDao;
import com.hospital.dao.impl.HospitalDaoImpl;
import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;
import com.hospital.service.IHospitalService;

public class HospitalServiceImpl implements IHospitalService{

	/**
	 * ��ȡҽ���б�
	 */
	@Override
	public List<HospitalDomain> queryHospitalList(String hosName, String hosAddr,PageDomain pageDomain) {
		IHospitalDao hospitalDao = new HospitalDaoImpl();
		
		//�ܼ�¼��
		int totalCount = hospitalDao.countHospital(hosName, hosAddr);
		pageDomain.setTotalCount(totalCount);
		int totalPage = pageDomain.getTotalPage();
		//��ҳ�㷨
//		��ǰҳΪ��1ҳ��ÿҳ��ʾ4�У���0�п�ʼ
//		��ǰҳΪ��2ҳ��ÿҳ��ʾ4�У���4�п�ʼ
//		��ǰҳΪ��3ҳ��ÿҳ��ʾ4�У���8�п�ʼ
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
	 * �ж����ҽ���Ƿ�ɹ�,����ҽ������
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
	 * ɾ��ҽ��
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

