package com.dao.inter;

import java.util.List;

import com.vo.Jdadmin;

public interface JdadminDao {
	//һ.����
		public int addUser(Jdadmin user) throws Exception;
		//��.ɾ��
		public int deleteUser(Jdadmin user) throws Exception;
		//��.�޸�
		public int updateUser(Jdadmin user) throws Exception;
		
		//��.��1
		public Jdadmin getUserById(Integer userid) throws Exception;
		
		//��.��SQL����
		public List<Jdadmin> getPageByQuery(String sql) throws Exception;
		
		//��.��ѯ�ܹ��ж�������¼
		public int getTotalRecordSum(String sql) throws Exception;
		
}
