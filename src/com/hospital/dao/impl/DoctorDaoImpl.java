/**
 * 
 */
package com.hospital.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hospital.dao.IDoctorDao;
import com.hospital.model.DoctorDomain;
import com.hospital.model.DoctorTitleDomain;
import com.hospital.model.HospitalDomain;
import com.hospital.util.DBUtil;

public class DoctorDaoImpl implements IDoctorDao{

	/**
	 * 	查询医师列表 
	 */
	@Override
	public List<DoctorDomain> queryDoctorList(String docName, String docTitle, int startRow , int pageRows) {
		List<Object> params=new ArrayList<>();
		String sql="select * from doctor where 1=1 and data_state=1";
		if(docName!=null&&!"".equals(docName)) {
			params.add("%"+docName+"%");
			sql+=" and doc_name like ?";
		}
		if(docTitle!=null&&!"".equals(docTitle)) {
			params.add("%"+docTitle+"%");
			sql+=" and doc_title like ?";
		}
		
		if( pageRows > 0) {
			//分页
			sql+= " limit ? , ?";
			params.add(startRow);
			params.add(pageRows);
		}
		//将集合转为数组
		Object[] array=params.toArray(new Object[params.size()]);
		List<DoctorDomain> doctorResult=DBUtil.exeQuery(sql, DoctorDomain.class, array);
		return doctorResult;
	}

	@Override
	public int countDoctor(String docName, String docTitle) {
		List<Object> params = new ArrayList<>();
		String sql = "select * from doctor where 1 = 1 and data_state = 1";
		if(docName != null && !"".equals(docName)) {
			params.add("%" + docName + "%");
			sql += " and doc_name like ? ";
		}
		
		if(docTitle != null && !"".equals(docTitle)) {
			params.add("%" + docTitle + "%");
			sql += " and doc_title like ? ";
		}
		
		//将集合转换成数组
		Object[] array = params.toArray(new Object[params.size()]);
		List<DoctorDomain> doctorResult = DBUtil.exeQuery(sql, DoctorDomain.class , array);
		if(doctorResult != null) {
			return doctorResult.size();
		}
		return 0;
	}

	@Override
	public int insertDoctor(DoctorDomain doctorDomain) {
		String sql="insert into doctor(DOC_NAME,DOC_TITLE,HOS_NAME,DEPT_NAME,EXPERT_DESC,ORDER_PRICE,MEMO,DATA_STATE)"+"value(?,?,?,?,?,?,?,?)";
		List<Object> params=new ArrayList<>();
		params.add(doctorDomain.getDocName());
		params.add(doctorDomain.getDocTitle());
		params.add(doctorDomain.getHosName());
		params.add(doctorDomain.getDeptName());
		params.add(doctorDomain.getExpertDesc());
		params.add(doctorDomain.getOrderPrice());
		params.add(doctorDomain.getMemo());
		params.add(doctorDomain.getDataState());
		
		//集合转为数组
		Object[] array=params.toArray(new Object[params.size()]);
		//index:更新的行数
		int index=DBUtil.exeUpdate(sql, array);
		return index;
	}

	@Override
	public List<DoctorTitleDomain> queryDoctorTitleList() {
		String sql="select * from doc_title where 1=1";
		return DBUtil.exeQuery(sql,DoctorTitleDomain.class);
	}

	@Override
	public Integer deleteDoctor(Integer docId) {
		//逻辑性删除，非物理性删除
		String sql = "update doctor set data_state = -1 where doc_id = ?";
		int index = DBUtil.exeUpdate(sql, docId);
		return index;
	}

	@Override
	public DoctorDomain getDoctor(Integer docId) {
		String sql="select * from doctor where 1=1 and doc_id=?";
		List<DoctorDomain> docResult  = DBUtil.exeQuery(sql, DoctorDomain.class, docId);
		if(docResult != null && docResult.size() > 0) {
			return docResult.get(0);
		}
		return null;
	}

	@Override
	public Integer updateDoctor(DoctorDomain doctorDomain) {
		String sql = "update doctor set DOC_NAME = ?, DOC_TITLE = ? ,HOS_NAME = ? ,DEPT_NAME = ? ,EXPERT_DESC = ? ,ORDER_PRICE = ? ,MEMO=?,DATA_STATE = ? where DOC_ID = ? ";
		List<Object> params = new ArrayList<>();
		params.add(doctorDomain.getDocName());
		params.add(doctorDomain.getDocTitle());
		params.add(doctorDomain.getHosName());
		params.add(doctorDomain.getDeptName());
		params.add(doctorDomain.getExpertDesc());
		params.add(doctorDomain.getOrderPrice());
		params.add(doctorDomain.getMemo());
		params.add(doctorDomain.getDataState());
		params.add(doctorDomain.getDocId());
		Object[] array = params.toArray(new Object[params.size()]);
		int index = DBUtil.exeUpdate(sql, array);
		return index;
	}

	@Override
	public List<DoctorDomain> getAllDoctors() {
		String sql="select * from doctor where 1=1";
		List<DoctorDomain> doctors = DBUtil.exeQuery(sql, DoctorDomain.class);
		return doctors;
	}

}
