package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dao.impl.CategoryOrderDaoImpl;
import com.service.inter.UserOrderSerivceInter;
import com.vo.CategoryOrder;

public class UserOrderSerivceImpl implements UserOrderSerivceInter{
	CategoryOrderDaoImpl dao;
	public UserOrderSerivceImpl(){
		dao = new CategoryOrderDaoImpl();
	}
	@Override
	//���ɶ���
	public int addOrder(CategoryOrder categoryOrder)throws Exception {
		int count = dao.addOrder(categoryOrder);
		return count;
	}
	//ɾ������
	@Override
	public int deleteOrderById(String cid) throws Exception{
		CategoryOrder categoryOrder = new CategoryOrder();
		categoryOrder.setOid(Integer.parseInt(cid));
		int count = dao.deleOrder(categoryOrder);
		return count;
	}
	//��ѯ��һ����
	@Override
	public CategoryOrder getOrderById(String cid) throws Exception {
		CategoryOrder categoryOrder = null;
		categoryOrder = dao.getOrderByID(new Integer(cid));
		return categoryOrder;

	}
	//��ѯ����δ֧����������
	@Override
	public int getAllOrderSum() throws Exception  {
		String sql = "select count(*)  from categoryorder where ostate=0";
		int getAllOrderSum = 0;
		getAllOrderSum = dao.getAllOrder(sql);
		return getAllOrderSum;
	}
	//��ѯ���ж���
		@Override
		public List<CategoryOrder> getAllOrder() throws Exception {
			List<CategoryOrder> list =null;
			String sql="select * from categoryorder";
			list=dao.getAllBySql(sql);
			return list;
		}
	//����������ѯ
	@Override
	public List<CategoryOrder> getAllByQuery(CategoryOrder order) throws Exception {
		List<CategoryOrder> list =null;
//		String sql="select * from categoryorder where cname like '%123 %'or cdesc like '%312%' or cid like '%914%'";	
		StringBuffer sb =new StringBuffer("select * from categoryorder where 1=1");
		Integer oid = order.getOid();
	
		if(oid!= null){
			sb.append(" and oid =");
			sb.append(oid);
			sb.append("");
		}
		String sql = sb.toString();
		System.out.println(sql);
		list=dao.getAllBySql(sql);
		return list;
	}
	//�ϴ�ͼƬ
	@Override
	public void upload(HttpServletRequest request, String productListUploadPath) {
		// TODO Auto-generated method stub
		
	}
	//����ͼƬ��·������Ʒ��
	@Override
	public void saveImagePathInTable(String pid, List<String> fileNameList) {
		// TODO Auto-generated method stub
		
	}
	
	public List<CategoryOrder> OrdequeryOrderStater(int ostate) throws Exception {
		String sql="select *  from categoryorder where ostate="+ ostate;
		List<CategoryOrder> list = dao.getAllBySql(sql);
		return list;
	}
	public static void main(String[] args) throws Exception {
		UserOrderSerivceImpl order =new UserOrderSerivceImpl();
		CategoryOrder o =new CategoryOrder();
		List<CategoryOrder> list = order.OrdequeryOrderStater(1);
		for(Object li: list){
			System.out.println(li);
		}
		int size = list.size();
		System.out.println(size);
	}
	
	//�޸Ķ��� 
	@Override
	public int updateCategory(CategoryOrder categoryOrder) throws Exception {
		int count = dao.updateOrder(categoryOrder);
		return count;
		
	}
}








