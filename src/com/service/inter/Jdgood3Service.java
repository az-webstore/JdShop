package com.service.inter;

import java.util.List;

import com.page.PageInfo;
import com.vo.Jdgood;
import com.vo.Jdgood3;

public interface Jdgood3Service {
	//���
	public int addJdgood(Jdgood3 jdgood) throws Exception;
	//��ѯ������Ʒ�����µ�������Ʒ��������
	public int getJdgood3SumByJdgood(String jid) throws Exception;
	//��ѯ������Ʒ�����¶�Ӧ��������Ʒ����
	public List<Jdgood3> getAllJdgood3ByJdgood(int jid) throws Exception;
	//��1
	public Jdgood3 getJdgoodById(String jid) throws Exception;

	//������Ʒ�������ֵõ�Jdgood3
	public Jdgood3 getJdgoodByName(String jname) throws Exception;
	//��ѯָ����������ĸ��׶��������jid  
	//�β�: jid ���������jid
	//����ֵ: �������������jid
	public int getParentJdgoodById(int jid) throws Exception;
	
	//��ѯ���ж�����Ʒ����
	public List<Jdgood3> getAllJdgoods() throws Exception;
	//ɾ������������Ʒ����
	public int deleteJdgoodById(String jid) throws Exception;
	//�޸�������Ʒ����
	public int updateJdgood(Jdgood3 jdgood) throws Exception;
	//���շ�ҳ��ѯ������Ʒ����
	public List<Jdgood3> getPageByQuery(PageInfo pageInfo) throws Exception;
	//��ѯ�ܹ��ж�������¼
	public int getTotalRecordCount() throws Exception;
	//����������ѯ Ȼ���ҳ
	public List<Jdgood3> getPageByQuery(Jdgood3 jdgood,PageInfo pageInfo) throws Exception;
	//����������ѯ �ܹ��ж�������¼
	public int getTotalRecordCount(Jdgood3 jdgood) throws Exception;
	
}
