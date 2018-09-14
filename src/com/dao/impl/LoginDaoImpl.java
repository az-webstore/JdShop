package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.LoginDao;
import com.util.ConnOracle;
import com.vo.Login;

public class LoginDaoImpl implements LoginDao {
	/*�ܹ��з���
	 * int addLogin(Login login)����û���Ϣ�� DML������Ӱ���������DCL��DDL��� ����ֵ��0��
	 * int deleteLogin(Login login)ɾ���û���Ϣ
	 * int updateLogin(Login login)�޸��û���Ϣ
	 * Login getLoginById(int id)����ID��ѯ�û���Ϣ�����ŵ�vo��
	 * List<Login> getPageByQuery(String sql)���ݴ�������sql����ѯ
	 * */
	// 1.���Connection
	private Connection conn;

	public LoginDaoImpl() {
	
	}

	@Override
	public int addLogin(Login login) throws Exception{
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "insert into login values(seq_login.nextval,?,?,0,?,?,?)";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;

		// Ĭ�������ǹرյ� ����addCategory����һ����¼��ʱ�� ���Զ��ύ
		// ��������

		try {

			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, login.getPassword());
			pstmt.setString(3, login.getMail());
			pstmt.setString(4, login.getCode());
			pstmt.setString(5, login.getPhoneNumber());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

		
			if (count >= 1) {
				System.out.println("����û��ɹ�!");
			} else {
				System.out.println("û������κ��û�!");
			}
		} catch (SQLException e) {
			
			System.out.println("����ͨ��������û�ʧ��");
			e.printStackTrace();
			
			throw new Exception("�û����ʧ��");//�쳣ת��
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int deleteLogin(Login login) throws Exception{
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "delete from login where lid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, login.getLid());

			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("ɾ���û��ɹ�!");
			} else {
				System.out.println("û��ɾ���κ��û�!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ����ɾ���û�ʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int updateLogin(Login login) {
		conn = ConnOracle.getConnection();
		int count = 0;
		// 3.����ͨ��
		String sql = "update login set username=?,password=?,mail=? ,isActive=?, code=?, phoneNumber=? where lid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, login.getPassword());
			pstmt.setString(3, login.getMail());
			pstmt.setInt(4, login.getIsActive());
			pstmt.setString(5, login.getCode());
			pstmt.setString(6, login.getPhoneNumber());
			pstmt.setInt(7, login.getLid());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("�޸��û���Ϣ�ɹ�!");
			} else {
				System.out.println("û���޸��κ��û���Ϣ!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����޸��û���Ϣʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public Login getLoginById(int id)throws Exception {
		conn = ConnOracle.getConnection();
		Login login = new Login();

		// 3.����ͨ��
		String sql = "select * from login where lid=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			// 4.ִ�в����ؽ����
			rs = pstmt.executeQuery();

			while (rs.next()) {
				login.setLid(rs.getInt("lid"));
				login.setUsername(rs.getString("username"));
				login.setPassword(rs.getString("password"));
				login.setMail(rs.getString("mail"));
				login.setIsActive(rs.getInt("isActive"));
				login.setIsActive(rs.getInt("code"));
				login.setPhoneNumber(rs.getString("phoneNumber"));
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ�û���Ϣʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);
		}

		return login;
	}

	@Override
	public List<Login> getPageByQuery(String sql) throws Exception {
		conn = ConnOracle.getConnection();
		List<Login> list = new ArrayList<Login>();
		Login login = null;
		ResultSet rs = null;
		Statement stmt = null;
		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				login = new Login();

				login.setLid(rs.getInt("lid"));
				login.setUsername(rs.getString("username"));
				login.setPassword(rs.getString("password"));
				login.setMail(rs.getString("mail"));
				login.setIsActive(rs.getInt("isActive"));
				login.setCode(rs.getString("code"));
				login.setPhoneNumber(rs.getString("phoneNumber"));
				System.out.println("��ѯ�е�����login��Ϣ��"+login);
				list.add(login);

			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ�����ʧ��");
			e.printStackTrace();
			
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}

	@Override
	public Login userActive(String code){
		conn = ConnOracle.getConnection();
		Login login = new Login();

		// 3.����ͨ��
		String sql = "select * from login where code=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, code);
			// 4.ִ�в����ؽ����
			rs = pstmt.executeQuery();

			while (rs.next()) {
				login.setLid(rs.getInt("lid"));
				login.setUsername(rs.getString("username"));
				login.setPassword(rs.getString("password"));
				login.setMail(rs.getString("mail"));
				login.setIsActive(rs.getInt("isActive"));
				login.setCode(rs.getString("code"));
				login.setPhoneNumber(rs.getString("phoneNumber"));
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ�û���Ϣʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);
		}
		System.out.println("dao�е�userActive��û���޸ļ�������isActiveʱ��"+login);
		return login;
	}

	
}
