package com.service.inter;

import java.util.List;

import com.page.PageInfo;
import com.vo.Jdgood;


public interface JdgoodService {
	    //���һ����Ʒ����
		public int addJdgood(Jdgood jdgood) throws Exception;
		//��ѯ����һ����Ʒ����
		public List<Jdgood> getAllJdgoods() throws Exception;
		//ɾ������һ����Ʒ����
		public int deleteJdgoodById(String jid) throws Exception;
		//��ԃ����һ����Ʒ����
		public Jdgood getJdgoodById(String jid) throws Exception;
		//�޸�һ����Ʒ����
		public int updateJdgood(Jdgood jdgood) throws Exception;
		//���շ�ҳ��ѯһ����Ʒ����
		public List<Jdgood> getPageByQuery(PageInfo pageInfo) throws Exception;
		//��ѯ�ܹ��ж�������¼
		public int getTotalRecordCount() throws Exception;
		//����������ѯ Ȼ���ҳ
		public List<Jdgood> getPageByQuery(Jdgood jdgood,PageInfo pageInfo) throws Exception;
		//����������ѯ �ܹ��ж�������¼
		public int getTotalRecordCount(Jdgood jdgood) throws Exception;
}
