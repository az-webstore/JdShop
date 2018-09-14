package com.dao.inter;

import java.util.List;

import com.vo.Jdproduct;

//��Ʒ�����DAO�ӿ�
public interface JdproductDao {
	//һ.����
	public int addJdproduct(Jdproduct product) throws Exception;
	//��.ɾ��
	public int deleteJdproduct(Jdproduct product) throws Exception;
	//��.�޸�
	public int updateJdproduct(Jdproduct product) throws Exception;
	//��.��1
	public Jdproduct getJdproductById(Integer pid) throws Exception;
	//��.��SQL����
	public List<Jdproduct> getPageByQuery(String sql) throws Exception;
	//��.��ѯ�ܹ��ж�������¼
	public int getTotalRecordSum(String sql) throws Exception;
	
}

