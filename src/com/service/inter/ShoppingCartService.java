package com.service.inter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vo.Jdproduct;

public interface ShoppingCartService {

	//����Ʒ���빺�ﳵ
	public void addToCart(HttpSession session,Jdproduct category2,int ProductSum);
	//����Ʒɾ�����ﳵ
}
