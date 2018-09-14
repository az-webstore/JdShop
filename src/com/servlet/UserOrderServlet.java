package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.impl.JdproductServiceImpl;
import com.service.impl.JdproductServiceImpl;
import com.service.impl.ShoppingCartServiceImpl;
import com.service.impl.UserOrderSerivceImpl;
import com.vo.Jdproduct;
import com.vo.CategoryOrder;
import com.vo.Jdproduct;
import com.vo.Login;

/**
 * Servlet implementation class UserOrderServlet
 */
public class UserOrderServlet extends HttpServlet {
		String target="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		if("add".equals(action)){
			this.add(request,response);
		}else if("getAll".equals(action)){
			this.getAll(request,response);
		}else if("delete".equals(action)){
			this.delete(request,response);
		}else if("paymentByAll".equals(action)){
			this.paymentByAll(request,response);
		}
	}
	//��ӣ����ɶ�����
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				System.out.println("add");
				//һ.�������
				String cname = request.getParameter("cname");
				String cdesc = request.getParameter("cdesc");
				String cprice = request.getParameter("cprice");
				String dianpuName = request.getParameter("dianpuName");
				
				CategoryOrder cateorder= new CategoryOrder();
//				cateorder.setCprice(Integer.parseInt(cprice));
//				cateorder.setDianpuname(dianpuName);
				UserOrderSerivceImpl service = new UserOrderSerivceImpl();
				try {
					//��.����ҵ���߼�
					service.addOrder(cateorder);
					request.setAttribute("msg", "���һ����Ʒ����ɹ�");
				} catch (Exception e) {
					request.setAttribute("msg", "���һ����Ʒ����ʧ��");
					e.printStackTrace();
				}
				//��.ת����ͼ
				target = "/WEB-INF/msg.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(target);
				rd.forward(request, response);
	}
	
	//��ѯ���ж���
	protected void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("test");
			UserOrderSerivceImpl service =new UserOrderSerivceImpl();
		try {
			List<CategoryOrder> list = service.getAllOrder();
			request.setAttribute("list", list);
			if(list==null||list.size()==0){
				target = "/WEB-INF/msg.jsp";
				request.setAttribute("msg", "��ʱû�ж�������¼����ʾ��֮ǰ�������Ʒ ");
			}else{
				HttpSession session = request.getSession(true);
				request.setAttribute("list", list);
				target="/WEB-INF/jsp/admin/OrderCategory/categoryMain.jsp";
				
			}
			
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	//ɾ��������Ϣ
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		UserOrderSerivceImpl service =new UserOrderSerivceImpl();

		// һ.�������
		try {
			service.deleteOrderById(cid);
			this.getAll(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
			// ��.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);
		}
	
	}
	

	protected void paymentByAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("֧���ɹ�");
//		String cid = request.getParameter("cid");
//		UserOrderSerivceImpl service = new UserOrderSerivceImpl();
//		CategoryOrder cateorder  = new CategoryOrder();
//		try {
//			CategoryOrder categoryOrderpay = service.getOrderById(cid);
//			request.setAttribute("categoryOrderpay",categoryOrderpay);
//		} catch (Exception e) {
//			request.setAttribute("msg", e.getMessage());
//			target = "/WEB-INF/msg.jsp";
//			e.printStackTrace();
//			// ��.ת����ͼ
//			request.getRequestDispatcher(target).forward(request, response);
//		}
	
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
