package com.service.inter;

import java.util.List;

import com.vo.Login;


public interface LoginService {

	//���
	public int addLogin(Login login) throws Exception;
	//������
	public List<Login> getAllLogin() throws Exception;
	public Login login(String username,String password) throws Exception;
	public boolean checkUsername(String username) throws Exception;
	public boolean checkEmail(String email) throws Exception;
	public boolean uerActive(String code);
	public boolean checkPhone(String phoneNumber)throws Exception;
}
