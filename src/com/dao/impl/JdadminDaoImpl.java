package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.JdadminDao;
import com.util.ConnOracle;
import com.vo.Jdadmin;

public class JdadminDaoImpl implements JdadminDao {

	
	private Connection conn;
	
	public JdadminDaoImpl() {
		
	}


	@Override
	public int addUser(Jdadmin user) throws Exception {
		conn = ConnOracle.getConnection();
		int count = 0;
		String sql = "insert into jdadmin(userid,username,passwords,iswork) values(seq_jdadmin.nextval,?,?,1)";
		PreparedStatement pstmt = null;
		// ��.����ͨ��
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPasswords());

			// ��.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ִ��dml �� ddl���� ������Ӱ�������
			if (count >= 1) {
				System.out.println("��ӹ���Ա�ɹ�!");
			} else {
				System.out.println("û������κι���Ա!");
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("����û�ʧ��");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);

		}

		return count;
	}

	@Override
	public int deleteUser(Jdadmin user) throws Exception{
		conn = ConnOracle.getConnection();
		int count = 0;
		String sql = "delete from jdadmin where userid=?";
		PreparedStatement pstmt = null;
		// ��.����ͨ��
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUserid());

			// ��.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ִ��dml �� ddl���� ������Ӱ�������
			if (count >= 1) {
				System.out.println("ɾ������Ա�ɹ�!");
			} else {
				System.out.println("û��ɾ���κι���Ա!");
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("ɾ���û�ʧ��");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);

		}

		return count;
	}

	// ��.�޸�
	public int updateUser(Jdadmin user) throws Exception{
		conn = ConnOracle.getConnection();
		int count;
		String sql = "update jdadmin set username=?,passwords=? where userid=?";
		PreparedStatement pstmt = null;
		// ��.����ͨ��
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPasswords());
			pstmt.setInt(3, user.getUserid());

			// ��.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ִ��dml �� ddl���� ������Ӱ�������
			if (count >= 1) {
				System.out.println("�޸Ĺ���Ա�ɹ�!");
			} else {
				System.out.println("û���޸��κι���Ա�û�!");
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("�޸��û�ʧ��");
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	// ��.��1

	public Jdadmin getUserById(Integer userid) throws Exception{
		conn = ConnOracle.getConnection();
		Jdadmin user = new Jdadmin();

		String sql = "select * from jdadmin where userid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ��.����ͨ��
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);

			// ��.ִ�в����ؽ����
			// ResultSet ����� ��װ�� ���ݿ��ѯ�Ľ����
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPasswords(rs.getString(3));
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("��ѯ�����û�ʧ��");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);

		}

		return user;
	}

	// ��.��SQL����
	public List<Jdadmin> getPageByQuery(String sql) throws Exception{
		conn = ConnOracle.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		List<Jdadmin> list = new ArrayList<Jdadmin>();

		Jdadmin user = null;

		// ��.����ͨ��
		try {
			stmt = conn.createStatement();
			// ��.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				user = new Jdadmin();

				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPasswords(rs.getString("passwords"));
				user.setOthername(rs.getString("othername"));
				user.setAge(rs.getInt("age"));
				user.setSex(rs.getString("sex"));
				user.setUserdesc(rs.getString("userdesc"));
				user.setIswork(rs.getInt("iswork"));
				list.add(user);
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��!");
			e.printStackTrace();
			throw new Exception("��ѯ�û�ʧ��");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}



	public int getTotalRecordSum(String sql) throws Exception{
		conn = ConnOracle.getConnection();
		int totalRecordSum = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ��.����ͨ��
		try {
			pstmt = conn.prepareStatement(sql);
			

			// ��.ִ�в����ؽ����
			// ResultSet ����� ��װ�� ���ݿ��ѯ�Ľ����
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalRecordSum = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
			throw new Exception("��ѯ�û�����ʧ��");
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);

		}

		return totalRecordSum;
	}
	
	
}
