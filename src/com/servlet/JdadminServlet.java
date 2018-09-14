package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.impl.JdgoodServiceImpl;
import com.service.impl.JdadminServiceImpl;
import com.service.inter.JdgoodService;
import com.vo.Jdgood;
import com.vo.Jdproduct;
import com.vo.Jdadmin;

public class JdadminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		if("login".equals(action)){
			this.login(request,response);
		}else if("logout".equals(action)){
			this.logout(request,response);
		}
		
		
		
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String target = "";
		//һ.�������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//��.����ҵ���߼� 
		try {
			JdadminServiceImpl service = new JdadminServiceImpl();
			Jdadmin user = service.login(username, password);
			
			if(user!=null){//�����û����������ҵ��˸��û�
				
				Integer isActive = user.getIswork();
				if(isActive==1){//��¼�ɹ� 
				
					HttpSession session = request.getSession(true);
					session.setAttribute("admin", user);//��user����浽session�� �Ժ�ÿ��ҳ���ж�����ȡ����ʹ��
					//������̨����ҳ jdHoutai.jsp
					//��ѯ��Ʒ���� ������ʾ��ҳ�Ĳ˵�
					JdgoodService categoryService = new JdgoodServiceImpl();
					List<Jdgood> list = categoryService.getAllJdgoods();
					request.setAttribute("list", list);
					target = "/jsp/admin/jdHoutai.jsp";
				}else{
					//��¼ʧ�� ���ص�¼ҳ�� ��ʾ "�û���δ����,�뼤����ٳ��Ե�¼"
					request.setAttribute("msg", "�û���δ����,�뼤����ٳ��Ե�¼");
					target = "/jsp/user/CyAdmin.jsp";
				}
			}else{
				//��¼ʧ�� ���ص�¼ҳ�� ��ʾ "�û������������"
				request.setAttribute("msg", "�û������������,����������");
				target = "/jsp/user/CyAdmin.jsp";
			}
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg","��¼ʧ�� ��ص���¼ҳ����µ�¼");
			e.printStackTrace();
		}
		
		//��.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//һ.�������
		//��.����ҵ���߼�
		//�˳���¼ ע��session����
		HttpSession session = request.getSession(true);
		session.invalidate();
		//��.ת����ͼ
		target = "/jsp/user/CyAdmin.jsp";
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
