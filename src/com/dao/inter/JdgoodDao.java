package com.dao.inter;

import java.util.List;


import com.vo.Jdgood;

public interface JdgoodDao {
	//1.���
		public int addJdgood(Jdgood jdgood) throws Exception;
		//2.ɾ��
		public int deleteJdgood(Jdgood jdgood) throws Exception;
		//3.�޸�
		public int updateJdgood(Jdgood jdgood) throws Exception;
		//4.��1
		public Jdgood getJdgoodById(int id) throws Exception;
		//5.��SQL����
		public List<Jdgood> getPageByQuery(String sql) throws Exception;
		//6.��ѯ�ܵļ�¼��
		public int getTotalRecordCount(String sql) throws Exception;
}
