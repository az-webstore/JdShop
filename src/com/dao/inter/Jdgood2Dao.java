package com.dao.inter;

import java.util.List;

import com.vo.Jdgood2;

public interface Jdgood2Dao {
	public int addJdgood(Jdgood2 jdgood2) throws Exception;
	//2.ɾ��
	public int deleteJdgood(Jdgood2 jdgood2) throws Exception;
	//3.�޸�
	public int updateJdgood(Jdgood2 jdgood2) throws Exception;
	//4.��1
	public Jdgood2 getJdgoodById(int id) throws Exception;
	//5.��SQL����
	public List<Jdgood2> getPageByQuery(String sql) throws Exception;
	//6.��ѯ�ܵļ�¼��
	public int getTotalRecordCount(String sql) throws Exception;
	
}
