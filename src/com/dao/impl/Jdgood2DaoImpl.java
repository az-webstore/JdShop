package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.Jdgood2Dao;
import com.util.ConnOracle;
import com.vo.Jdgood2;

public class Jdgood2DaoImpl implements Jdgood2Dao {
	private Connection conn;

	public Jdgood2DaoImpl() {
		
	}

	@Override
	public int addJdgood(Jdgood2 jdgood) throws Exception {
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "insert into jdgood2 values(seq_jdgood2.nextval,?,?)";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;

		// Ĭ�������ǹرյ� ����addCategory����һ����¼��ʱ�� ���Զ��ύ
		// ��������

		try {

			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, jdgood.getJ2name());
			pstmt.setInt(2, jdgood.getFid());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

		
			if (count >= 1) {
				System.out.println("�����Ʒ����ɹ�!");
			} else {
				System.out.println("û������κ���Ʒ����!");
			}
		} catch (SQLException e) {
			
			System.out.println("����ͨ���������Ʒ����ʧ��");
			e.printStackTrace();
			
			throw new Exception("��Ʒ�������ʧ��");//�쳣ת��
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int deleteJdgood(Jdgood2 jdgood2) throws Exception {
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "delete from jdgood2 where j2id=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, jdgood2.getJ2id());

			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("ɾ����Ʒ����ɹ�!");
			} else {
				System.out.println("û��ɾ���κ���Ʒ����!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ����ɾ����Ʒ����ʧ��");
			e.printStackTrace();
			
			throw new Exception("ɾ����Ʒ����ʧ��");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int updateJdgood(Jdgood2 jdgood2) throws Exception {
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "update jdgood2 set j2name=?,fid=? where j2id=? ";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, jdgood2.getJ2name());
			pstmt.setInt(2, jdgood2.getFid());
			pstmt.setInt(3, jdgood2.getJ2id());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("�޸���Ʒ����ɹ�!");
			} else {
				System.out.println("û���޸��κ���Ʒ����!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����޸���Ʒ����ʧ��");
			e.printStackTrace();
			throw new Exception("�޸Ķ�������Ʒ����ʧ��!");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public Jdgood2 getJdgoodById(int id) throws Exception {
		conn = ConnOracle.getConnection();
		Jdgood2 jdgood2 = new Jdgood2();

		// 3.����ͨ��
		String sql = "select * from jdgood2 where j2id=? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			// 4.ִ�в����ؽ����
			rs = pstmt.executeQuery();

			while (rs.next()) {
				jdgood2.setJ2id(rs.getInt("j2id"));
				jdgood2.setJ2name(rs.getString("j2name"));
				jdgood2.setFid(rs.getInt("fid"));
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ������Ʒ����ʧ��");
			e.printStackTrace();
			throw new Exception("��ѯ����������Ʒ����ʧ��!");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);
		}

		return jdgood2;
	}

	@Override
	public List<Jdgood2> getPageByQuery(String sql) throws Exception {
		conn = ConnOracle.getConnection();
		List<Jdgood2> list = new ArrayList<Jdgood2>();
		Jdgood2 jdgood2 = null;
		ResultSet rs = null;
		Statement stmt = null;
		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				jdgood2 = new Jdgood2();

				jdgood2.setJ2id(rs.getInt("j2id"));
				jdgood2.setJ2name(rs.getString("j2name"));
				jdgood2.setFid(rs.getInt("fid"));

				list.add(jdgood2);

			}
		} catch (SQLException e) {
			System.out.println("����getPageByQueryͨ�����ѯ�����ʧ��");
			e.printStackTrace();
			
			throw new Exception("��ѯ������Ʒ����ʧ��");//�쳣ת��
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	
	}

	@Override
	public int getTotalRecordCount(String sql) throws Exception {
		conn = ConnOracle.getConnection();
		int totalRecordSum = -1;
		
		ResultSet rs = null;
		Statement stmt = null;
		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				totalRecordSum = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("����getTotalRecordCountͨ�����ѯ�����ʧ��");
			e.printStackTrace();
			
			throw new Exception("��ѯ���������ܼ�¼��ʧ��");//�쳣ת��
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return totalRecordSum;
	}

}
