package com.service.inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.page.PageInfo;
import com.vo.Jdproduct;

public interface JdproductService {

	// �����Ʒ
	public int addProduct(Jdproduct product) throws Exception;

	// ɾ����Ʒ
	public int deleteProductById(String pid) throws Exception;

	// �޸�
	public int updateProduct(Jdproduct product) throws Exception;

	public int getTotalRecordSum() throws Exception;

	// �����а���ҳ
	public List<Jdproduct> getAllByPage(PageInfo pageInfo) throws Exception;

	// ����ѯ������ѯ ��ѯ���ܹ��ж�������¼
	public int getTotalRecordSumBySearchCondition(Jdproduct product)
			throws Exception;

	// ����ѯ������ѯ�� ��ҳ
	public List<Jdproduct> getPageByQuery(Jdproduct product, PageInfo pageInfo)
			throws Exception;

	// ��1
	public Jdproduct getProductById(String pid) throws Exception;

	// ��Ʒ�ϼ�
	public void productUp(Integer pid) throws Exception;

	// ��Ʒ�¼�
	public void productDown(Integer pid) throws Exception;

	// �ϴ�ͼƬ
	public void upload(HttpServletRequest request, String productListUploadPath) throws Exception;

	//����ͼƬ��·������Ʒ��
	public void saveImagePathInTable(String pid, List<String> fileNameList) throws Exception;
}
