package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.service.inter.ShoppingCartService;
import com.vo.Jdproduct;

public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Override
	public void addToCart(HttpSession session,Jdproduct category2,int ProductSum) {
		List<Jdproduct> list = (List<Jdproduct>)session.getAttribute("shoppingCart");
		if(list==null){
			//��һ������
			list = new ArrayList<Jdproduct>();
			list.add(category2);
			
			//���빺�ﳵ
		}else{
			//�ڶ�������
			int pid = category2.getPid();
			
			boolean ifBeforeBuy = false;
			
			//�鿴һ�� ��ǰ �Ƿ� ���
			for(Jdproduct oldProduct:list){
				
				if(oldProduct.getPid()==pid){//�����ǰ����������
					//����+1
					
					int shoppingCarSum = oldProduct.getProductSum();
					oldProduct.setProductSum(shoppingCarSum+ProductSum);
					ifBeforeBuy = true;
					break;
				}
			}
			
			if(!ifBeforeBuy){//֮ǰ���û���
				list.add(category2);//���빺�ﳵ
			}
			
		}
		session.setAttribute("shoppingCart", list);
	}
	
	
}
