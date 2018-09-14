package com.service.inter;

import java.util.List;

import com.page.PageInfo;
import com.vo.Jdgood;
import com.vo.Jdgood2;

public interface Jdgood2Service {
	//���
	public int addJdgood(Jdgood2 jdgood) throws Exception;
	//��ѯһ����Ʒ�����µĶ�����Ʒ��������
	public int getJdgood2SumByJdgood(String jid) throws Exception;
	//��ѯһ����Ʒ�����¶�Ӧ�Ķ�����Ʒ����
	public List<Jdgood2> getAllJdgood2ByJdgood(int jid) throws Exception;
	//��1
	public Jdgood2 getJdgoodById(String jid) throws Exception;

	//������Ʒ�������ֵõ�Jdgood2
	public Jdgood2 getJdgoodByName(String jname) throws Exception;
	//��ѯָ����������ĸ���һ�������jid  
	//�β�: jid ���������jid
	//����ֵ: ����һ�������jid
	public int getParentJdgoodById(int jid) throws Exception;
	
	//��ѯ���ж�����Ʒ����
	public List<Jdgood2> getAllJdgoods() throws Exception;
	//ɾ������������Ʒ����
	public int deleteJdgoodById(String jid) throws Exception;
	//�޸Ķ�����Ʒ����
	public int updateJdgood(Jdgood2 jdgood) throws Exception;
	//���շ�ҳ��ѯ������Ʒ����
	public List<Jdgood2> getPageByQuery(PageInfo pageInfo) throws Exception;
	//��ѯ�ܹ��ж�������¼
	public int getTotalRecordCount() throws Exception;
	//����������ѯ Ȼ���ҳ
	public List<Jdgood2> getPageByQuery(Jdgood2 jdgood,PageInfo pageInfo) throws Exception;
	//����������ѯ �ܹ��ж�������¼
	public int getTotalRecordCount(Jdgood2 jdgood) throws Exception;
	
}
