package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.JdgoodDao;
import com.util.ConnOracle;

import com.vo.Jdgood;

public class JdgoodDaoImpl implements JdgoodDao {
	// 1.���Connection
	private Connection conn;

	public JdgoodDaoImpl() {
		
	}

	@Override
	public int addJdgood(Jdgood jdgood) throws Exception {
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "insert into jdgood values(seq_jdgood.nextval,?)";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;

		// Ĭ�������ǹرյ� ����addCategory����һ����¼��ʱ�� ���Զ��ύ
		// ��������

		try {

			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, jdgood.getJname());
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
	public int deleteJdgood(Jdgood jdgood) throws Exception {
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "delete from jdgood where jid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, jdgood.getJid());

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
	public int updateJdgood(Jdgood jdgood) throws Exception {
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "update jdgood set jname=? where jid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, jdgood.getJname());
			pstmt.setInt(2, jdgood.getJid());
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
			throw new Exception("�޸�һ����Ʒ����ʧ��!");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public Jdgood getJdgoodById(int id) throws Exception {
		conn = ConnOracle.getConnection();
		Jdgood jdgood = new Jdgood();

		// 3.����ͨ��
		String sql = "select * from jdgood where jid=? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			// 4.ִ�в����ؽ����
			rs = pstmt.executeQuery();

			while (rs.next()) {
				jdgood.setJid(rs.getInt("jid"));
				jdgood.setJname(rs.getString("jname"));
				
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ������Ʒ����ʧ��");
			e.printStackTrace();
			throw new Exception("��ѯ����һ����Ʒ����ʧ��!");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);
		}

		return jdgood;
	}

	@Override
	public List<Jdgood> getPageByQuery(String sql) throws Exception {
		conn = ConnOracle.getConnection();
		List<Jdgood> list = new ArrayList<Jdgood>();
		Jdgood jdgood = null;
		ResultSet rs = null;
		Statement stmt = null;
		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				jdgood = new Jdgood();

				jdgood.setJid(rs.getInt("jid"));
				jdgood.setJname(rs.getString("jname"));
				

				list.add(jdgood);

			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ�����ʧ��");
			e.printStackTrace();
			
			throw new Exception("��ѯһ����Ʒ����ʧ��");//�쳣ת��
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
			System.out.println("����ͨ�����ѯ�����ʧ��");
			e.printStackTrace();
			
			throw new Exception("��ѯһ�������ܼ�¼��ʧ��");//�쳣ת��
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return totalRecordSum;
	}

}
