package com.service.inter;

import java.util.List;

import com.page.OrderCondition;
import com.page.PageInfo;
import com.vo.Jdproduct;

public interface UserProductService {
	// ָ�������µ�������Ʒ������
	public int getTotalRecordSumByCategory(String cid) throws Exception;

	// ����ѯ������ѯ ��ѯ���ܹ��ж�������¼
	public int getTotalRecordSumBySearchCondition(Jdproduct product) throws Exception;
	
	public List<Jdproduct> getPageByQuery(Jdproduct product, PageInfo pageInfo) throws Exception;

	//��ѯָ����Ʒ�����µ�������Ʒ  + ���� Ȼ���ҳ
	public List<Jdproduct> getAllByPageByCategory(PageInfo pageInfo,String cid,OrderCondition orderConditionObj) throws Exception;
	
	// ��1
	public Jdproduct getProductById(String pid) throws Exception;
}
