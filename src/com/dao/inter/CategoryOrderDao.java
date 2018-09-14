package com.dao.inter;

import java.util.List;

import com.vo.CategoryOrder;

public interface CategoryOrderDao {
	//����
	public int addOrder(CategoryOrder  order) throws Exception;
	//ɾ��
	public int deleOrder(CategoryOrder  order) throws Exception;
	//�޸�
	public int updateOrder(CategoryOrder  order) throws Exception;
	//��һ
	public CategoryOrder getOrderByID(int id) throws Exception;
	//��SQL����
	public List<CategoryOrder> getAllBySql(String sql) throws Exception;
	//��ѯ���е�����
	public int getAllOrder(String sql) throws Exception;
	//��ѯ���еĶ���״̬
	public List<CategoryOrder> queryOrderState(int cstate) throws Exception;
}
