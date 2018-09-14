package com.service.impl;

import java.util.List;

import com.dao.impl.LoginDaoImpl;

import com.dao.inter.LoginDao;
import com.service.inter.LoginService;

import com.vo.Login;


public class LoginServiceImpl implements LoginService{

	private LoginDao dao;
	
	public LoginServiceImpl(){
		dao = new LoginDaoImpl();
	}
	
	@Override
	public int addLogin(Login login) throws Exception{
		
		int count = dao.addLogin(login);
		return count;
	}

	@Override
	public List<Login> getAllLogin() throws Exception {
		List<Login> list = null;

		String sql = "select * from Login";
		list = dao.getPageByQuery(sql);
		return list;
	}

	@Override
	public Login login(String username, String password) throws Exception {
		System.out.println("service�е�login����");
		Login user = null;
		
		String sql = "select * from login where username='" + username+ "' and password='" + password + "'";
		List<Login> list = dao.getPageByQuery(sql);
		if(list.size()>0){
			//�û�����
			user = list.get(0);			
		}		
		return user;
	}

	@Override
	public boolean checkUsername(String username) throws Exception {
		System.out.println("checkUsername �� service");
		boolean isExist=false;
		String sql = "select * from login where username='" + username+ "'";
		List<Login> list = dao.getPageByQuery(sql);
		if(list.size()>0){
			//�û�����
			isExist=true;
		}
		System.out.println(isExist);
		return isExist;
	}

	@Override
	public boolean checkEmail(String email) throws Exception {
		System.out.println("checkEmail �� service");
		boolean isExist=false;
		String sql = "select * from login where mail='" + email+ "'";
		List<Login> list = dao.getPageByQuery(sql);
		if(list.size()>0){
			//�û�����
			isExist=true;
		}
		System.out.println(isExist);
		return isExist;
	}

	@Override
	public boolean uerActive(String code) {
			
		 Login user = dao.userActive(code);
		if(user!=null){
			//���Բ鵽�����룬�޸��û�״̬�����������
			user.setIsActive(1);
			user.setCode(null);
			//�������ݿ�			
			dao.updateLogin(user);			
			return true;
		}else{
			return false;
		}
		
	}

	public boolean checkPhone(String phoneNumber)throws Exception {
		System.out.println("checkPhone �� service");
		boolean isExist=false;
		String sql = "select * from login where phoneNumber='" + phoneNumber+ "'";
		List<Login> list = dao.getPageByQuery(sql);
		if(list.size()>0){
			//�û�����
			isExist=true;
		}
		System.out.println(isExist);
		return isExist;
	}



}
