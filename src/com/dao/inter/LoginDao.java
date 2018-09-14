package com.dao.inter;

import java.util.List;

import com.vo.Login;

public interface LoginDao {

	//1.���
	public int addLogin(Login login) throws Exception;
	//2.ɾ��
	public int deleteLogin(Login login)throws Exception;
	//3.�޸�
	public int updateLogin(Login login);
	//4.��1
	public Login getLoginById(int id)throws Exception;
	//5.��SQL����
	public List<Login> getPageByQuery(String sql) throws Exception;
	public Login userActive(String code);
}
