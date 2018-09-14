package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.CategoryOrderDao;
import com.util.ConnOracle;
import com.vo.CategoryOrder;

public class CategoryOrderDaoImpl implements CategoryOrderDao{
	private Connection conn;
	
	public CategoryOrderDaoImpl(){
		
	
	
	}
	//���붩��
		@Override
		public int addOrder(CategoryOrder order) throws Exception {
			conn= ConnOracle.getConnection();
			int count=0;
			PreparedStatement pstmt =null;
			// �ող������Ʒ����"δ֧��"״̬ ����cstate ��ֵΪ0 1 ��ʾ��֧�� 0 ��ʾδ֧��
			String sql="insert into categoryorder values(DBMS_RANDOM.value(1,99999999),sysdate,0,null,null,null,0)";
			try {
				pstmt = conn.prepareStatement(sql);
				//pstmt.setInt(1, order.getTotal());
				//pstmt.setInt(2, order.getTelephone());
				//pstmt.setString(3, order.getAddress());
				//pstmt.setString(4, order.getName());
				System.out.println("order����");
				//ִ�в����ؽ����
				 count = pstmt.executeUpdate();
				 if(count>=0){
					 System.out.println("��Ӷ����ɹ�����");
				 }else{
					 System.out.println("û����Ӷ���");
				 }
				
			} catch (SQLException e) {
				System.out.println("����ͨ������Ӷ���ʧ�ܣ�������");
				e.printStackTrace();
				throw new Exception("��Ӷ���ʧ�ܣ���");
			}finally{
				ConnOracle.closeConnection(null, pstmt, conn);
			}
			return count;
		}
		//ɾ��
		@Override
		public int deleOrder(CategoryOrder order) throws Exception {
			conn= ConnOracle.getConnection();
			PreparedStatement pstm=null;
			String sql="delete from categoryorder where oid=?";
			int count =0;
			try {
				pstm  = conn.prepareStatement(sql);
				pstm.setInt(1,order.getOid());
				//ִ�в����ؽ����
				 count = pstm.executeUpdate();
				 if(count>=0){
					 System.out.println("ɾ�������ɹ�����");
				 }else{
					 System.out.println("û��ɾ������");
				 }
			} catch (SQLException e) {
				System.out.println("����ͨ����ɾ������ʧ�ܣ���");
				e.printStackTrace();
				throw new Exception("ɾ������ʧ�ܣ���");
			}finally{
				ConnOracle.closeConnection(null, pstm, conn);
			}
			return count;
		}
		//�޸�
		@Override
		public int updateOrder(CategoryOrder order) throws Exception {
			conn= ConnOracle.getConnection();
			int count=0;
			PreparedStatement pstm =null;
			String sql="update categoryorder set ostate=?,address=? where oid=?";
			try {
				pstm=conn.prepareStatement(sql);
			
				pstm.setInt(1,order.getOstate());
				pstm.setString(2, order.getAddress());
				pstm.setInt(3,order.getOid());
			//ִ�в����ؽ����
				 count = pstm.executeUpdate();
				 if(count>=0){
					 System.out.println("�޸Ķ����ɹ�����");
				 }else{
					 System.out.println("û���޸Ķ���");
				 }
				
			} catch (SQLException e) {
				System.out.println("����ͨ�����޸Ķ���ʧ��");
				e.printStackTrace();
				throw new Exception("�޸Ķ���ʧ�ܣ���");
			}finally{
				ConnOracle.closeConnection(null, pstm, conn);
			}
			return count;
		}
		//��һ
		@Override
		public CategoryOrder getOrderByID(int id) throws Exception {
			conn= ConnOracle.getConnection();
			CategoryOrder order =new CategoryOrder();
			PreparedStatement pstm =null;
			ResultSet rs=null;
			String sql="select *  from categoryorder where oid = ?";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1,id);
				//ִ�в����ؽ����
				rs = pstm.executeQuery();
				while(rs.next()){
					order.setOid(rs.getInt("oid"));
					order.setOstate(rs.getInt("ostate"));
					order.setAddress(rs.getString("address"));
					order.setOtime(rs.getDate("otime"));
					order.setName(rs.getString("name"));
					order.setTelephone(rs.getInt("telephone"));
					order.setTotal(rs.getInt("total"));
				}
				
				
			} catch (SQLException e) {
				System.out.println("����ͨ��ʧ�ܣ���");
				e.printStackTrace();
				throw new Exception("��ѯ��һ����ʧ�ܣ���");
			}finally{
				ConnOracle.closeConnection(rs, pstm, conn);
			}
			return order;
		
		}
		//
		@Override
		public List<CategoryOrder> getAllBySql(String sql) throws Exception {
			conn= ConnOracle.getConnection();
			List<CategoryOrder> list = new ArrayList<CategoryOrder>();
			Statement stm=null;
			CategoryOrder order=null;
			ResultSet rs =null;
			try {
				stm= conn.createStatement();
				rs= stm.executeQuery(sql);
				while(rs.next()){
					order =new CategoryOrder();
					order.setOid(rs.getInt("oid"));
					order.setOtime(rs.getDate("otime"));
					order.setOstate(rs.getInt("ostate"));
					order.setAddress(rs.getString("address"));
					order.setName(rs.getString("name"));
					order.setTelephone(rs.getInt("telephone"));
					order.setTotal(rs.getInt("total"));
					list.add(order);
				}
				
			} catch (SQLException e) {
				System.out.println("��SQL��ѯʧ�ܣ���");
				e.printStackTrace();
				throw new Exception("��ѯ���ж���ʧ�ܣ���");
			}finally{
				ConnOracle.closeConnection(rs, stm, conn);
			}
			return list;
		}

		@Override
		public int getAllOrder(String sql) throws Exception {
			conn= ConnOracle.getConnection();
			int getAllOrder = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					getAllOrder = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("����ͨ��ʧ��");
				e.printStackTrace();
				throw new Exception("��ѯ��������ʧ�ܣ���");
			
			} finally {

				ConnOracle.closeConnection(rs, null, conn);
			}

			return getAllOrder;
		}

		@Override
		public List<CategoryOrder> queryOrderState(int cstate) throws Exception {
			conn= ConnOracle.getConnection();
			CategoryOrder orderState =new CategoryOrder();
			PreparedStatement pstm =null;
			ResultSet rs=null;  
			 List<CategoryOrder> list =new ArrayList<CategoryOrder>();
			String sql="select *  from categoryorder where ostate= ?";//���ǲ��ǲ�״̬
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1,cstate);
				//ִ�в����ؽ����
				rs = pstm.executeQuery();
				while(rs.next()){
					orderState.setOid(rs.getInt("oid"));
					orderState.setOtime(rs.getDate("otime"));
					orderState.setOstate(rs.getInt("ostate"));
					orderState.setAddress(rs.getString("address"));
					list.add(orderState);
				}
				
				
			} catch (SQLException e) {
				System.out.println("����ͨ��ʧ�ܣ���");
				e.printStackTrace();
				throw new Exception("��ѯ����״̬ʧ�ܣ�");
			}finally{
				ConnOracle.closeConnection(rs, pstm, conn);
			}
			return list;
		}
		
		public static void main(String[] args) throws Exception {
			CategoryOrderDaoImpl o = new CategoryOrderDaoImpl();
			CategoryOrder cateorder  = new CategoryOrder();
			o.addOrder(cateorder);
		
		}

}
