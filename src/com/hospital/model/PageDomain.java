package com.hospital.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ��ҳ��װ��
 */
@Setter
@Getter
public class PageDomain {

	private int currentPage = 1;//��ǰҳ
	
	private int pageCount = 4;//ÿҳ��ʾ����
	
	private int totalCount;//�ܼ�¼��
	
	private int totalPage;//��ҳ��
	
	//�����б�
	private List<HospitalDomain> pageData;
	
	private List<DoctorDomain> pageDoctor;
	
	private List<OrderInfoDomain> pageOrder;
	
	//ѡ���ҽ��
	private String hosName;
	
	/**
	 * ��ȡ��ҳ��
	 * @return
	 */
	public int getTotalPage() {
		if(totalCount % pageCount == 0) {
			totalPage = totalCount / pageCount;
		}else {
			//+1 ���ݵľ���%�����ȡģ��Ϊ0����Ϊ������
			totalPage = totalCount / pageCount + 1;
		}
		return totalPage;
	}
	
}

