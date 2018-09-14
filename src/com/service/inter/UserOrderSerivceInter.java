package com.service.inter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.vo.CategoryOrder;



public interface UserOrderSerivceInter {
		//���ɣ���ӣ�����
		public int addOrder(CategoryOrder categoryOrder) throws Exception;
		//ɾ������
		public int deleteOrderById(String cid) throws Exception;
		//��ѯ����
		public CategoryOrder getOrderById(String cid) throws Exception;
		//��ѯ���ж���
		public List<CategoryOrder> getAllOrder() throws Exception;
		//��ö�������
		public int getAllOrderSum() throws Exception;
		//�޸Ķ���
		public int updateCategory(CategoryOrder categoryOrder) throws Exception;
		// �ϴ�ͼƬ
		public void upload(HttpServletRequest request, String productListUploadPath) throws Exception;
		//����ͼƬ��·������Ʒ��
		public void saveImagePathInTable(String pid, List<String> fileNameList) throws Exception;
		//�����������в�ѯ
		public List<CategoryOrder> getAllByQuery(CategoryOrder order) throws Exception;
		//��ѯ����״̬   0��ʾδ���� 1 ��ʾ�Ѹ���
		public List<CategoryOrder> OrdequeryOrderStater(int cstate)throws Exception;
		
}
