package com.dao.inter;

import java.util.List;

import com.vo.Jdgood3;

public interface Jdgood3Dao {
	public int addJdgood(Jdgood3 jdgood3) throws Exception;
	//3.ɾ��
	public int deleteJdgood(Jdgood3 jdgood3) throws Exception;
	//3.�޸�
	public int updateJdgood(Jdgood3 jdgood3) throws Exception;
	//4.��1
	public Jdgood3 getJdgoodById(int id) throws Exception;
	//5.��SQL����
	public List<Jdgood3> getPageByQuery(String sql) throws Exception;
	//6.��ѯ�ܵļ�¼��
	public int getTotalRecordCount(String sql) throws Exception;
}
