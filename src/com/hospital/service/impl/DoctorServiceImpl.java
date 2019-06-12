/**
 * 
 */
package com.hospital.service.impl;

import java.util.List;

import com.hospital.dao.IDoctorDao;
import com.hospital.dao.impl.DoctorDaoImpl;
import com.hospital.model.DoctorDomain;
import com.hospital.model.DoctorTitleDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.model.PageDomain;
import com.hospital.service.IDoctorService;

public class DoctorServiceImpl implements IDoctorService {

	@Override
	public List<DoctorDomain> queryDoctorList(String docName, String docTitle,String hosName, PageDomain pageDomain) {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		// 总记录数
		int totalCount = doctorDao.countDoctor(docName, docTitle,hosName);
		pageDomain.setTotalCount(totalCount);
		int totalPage = pageDomain.getTotalPage();
		// 分页算法
        // 当前页为第1页，每页显示4行，从0行开始
        // 当前页为第2页，每页显示4行，从4行开始
        // 当前页为第3页，每页显示4行，从8行开始
		int currentPage = pageDomain.getCurrentPage();
		int pageCount = pageDomain.getPageCount();
		// System.out.println(currentPage);
		// System.out.println(totalPage);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		} else if (currentPage < 1) {
			currentPage = 1;
		}
		pageDomain.setCurrentPage(currentPage);
		int startRow = (currentPage - 1) * pageCount;
		List<DoctorDomain> doctorList = doctorDao.queryDoctorList(docName, docTitle,hosName, startRow, pageCount);
		return doctorList;
	}

	@Override
	public Boolean addDoctor(DoctorDomain doctorDomain) {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		int index=doctorDao.insertDoctor(doctorDomain);
		if(index>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<DoctorTitleDomain> queryDoctorTitleList() {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		return doctorDao.queryDoctorTitleList();
	}

	@Override
	public Integer deleteDoctor(Integer docId) {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		return doctorDao.deleteDoctor(docId);
	}

	@Override
	public DoctorDomain getDoctor(Integer docId) {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		return doctorDao.getDoctor(docId);
	}

	@Override
	public Integer updateDoctor(DoctorDomain doctorDomain) {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		return doctorDao.updateDoctor(doctorDomain);
	}

	@Override
	public List<DoctorDomain> getAllDoctors() {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		return doctorDao.getAllDoctors();
	}

	@Override
	public Integer updateHosSelected(String hosName) {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		return doctorDao.updateHosSelected(hosName);
	}

	@Override
	public HospitalDomain getHosSelected() {
		IDoctorDao doctorDao = new DoctorDaoImpl();
		return doctorDao.getHosSelected();
	}

}
