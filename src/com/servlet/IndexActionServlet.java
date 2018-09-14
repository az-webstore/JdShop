package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.impl.UserOrderSerivceImpl;
import com.vo.Jdproduct;
import com.vo.CategoryOrder;

public class IndexActionServlet extends HttpServlet {
	String target = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		if ("deleteMyOrder".equals(action)) {
			this.deleteMyOrder(request, response);
		} else if ("getMenuForFirst".equals(action)) {
			this.getMenuForFirst(request, response);
		} else if ("getAllOrder".equals(action)) {
			this.getAllOrder(request, response);
		} else if ("getAllByQuery".equals(action)) {
			this.getAllByQuery(request, response);
		} else if ("queryOrderState".equals(action)) {
			this.queryOrderState(request, response);
		}else if ("addOrder".equals(action)) {
			this.addOrder(request, response);
		}else if("payment".equals(action)){
			this.payment(request,response);
		}
	}

	// ��ѯ���ж���
	protected void getAllOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserOrderSerivceImpl service =new UserOrderSerivceImpl();
		try {
			List<CategoryOrder> list = service.getAllOrder();
			request.setAttribute("list", list);
			if(list==null||list.size()==0){
				target = "WEB-INF/jsp/user/indexOrderNull.jsp";
			}else{
				request.setAttribute("list", list);
				target = "WEB-INF/jsp/user/indexOrder.jsp";
				
			}
			
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯ���ж���ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		request.getRequestDispatcher(target).forward(request, response);
	}

	// ת����ҳ��
	protected void queryOrderState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ostate = request.getParameter("ostate");
		int parseInt = Integer.parseInt(ostate);
		UserOrderSerivceImpl service = new UserOrderSerivceImpl();
		try {
			List<CategoryOrder> list = service.OrdequeryOrderStater(parseInt);
			if (parseInt == 1) {
				request.setAttribute("list", list);
				target = "WEB-INF/jsp/user/indexOrder.jsp";
			} else if (parseInt == 0) {
				request.setAttribute("list", list);
				target = "WEB-INF/jsp/user/indexOrder.jsp";
			} else {
				target = "WEB-INF/jsp/user/indexOrderNull.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��!");
			target = "/WEB-INF/msg.jsp";
		}

		request.getRequestDispatcher(target).forward(request, response);
	}

	public void getMenuForFirst(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserOrderSerivceImpl service = new UserOrderSerivceImpl();
		System.out.println("test");
		// 1.�������
		// 2.����ҵ���߼�
		try {
			List<CategoryOrder> list = service.getAllOrder();
			request.setAttribute("list", list);
			target = "/WEB-INF/jsp/user/indexOrder.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		// 3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void deleteMyOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String cid = request.getParameter("cid");
		UserOrderSerivceImpl service = new UserOrderSerivceImpl();
		// һ.�������
		try {
			int deleteOrderById = service.deleteOrderById(cid);
			System.out.println(deleteOrderById);
			this.getAllOrder(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
			// ��.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);
		}

	}
	// ��������ѯ����

	// ��������ѯ����
	public void getAllByQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String target = "";
		// 1.�������
		String serarchOrder = request.getParameter("search_inp");
		CategoryOrder order = new CategoryOrder();
		if (serarchOrder != null && !serarchOrder.equals("")) {
			order.setOid(Integer.parseInt(serarchOrder));

		}

		try {
			// ����������ѯһ����������¼
			UserOrderSerivceImpl service = new UserOrderSerivceImpl();
			// 2.����ҵ���߼�
			List<CategoryOrder> list = service.getAllByQuery(order);
			request.setAttribute("serarchOrder", serarchOrder);
			if (list == null || list.size() == 0) {
				request.setAttribute("msg", "��ѯ����������Ҫ�Ķ���");
				target = "/WEB-INF/msg.jsp";
			} else {
				request.setAttribute("list", list);
				target = "WEB-INF/jsp/user/indexOrder.jsp";
			}

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		// 3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);

	}
			//��ӣ����ɶ�����
		protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					System.out.println("add");
					//һ.�������				
					CategoryOrder cateorder  = new CategoryOrder();
					HttpSession session = request.getSession(true);
					UserOrderSerivceImpl service = new UserOrderSerivceImpl();
					try {
						//��.����ҵ���߼�
						List<Jdproduct> list = (List<Jdproduct>) session.getAttribute("shoppingCart");
						request.setAttribute("list", list);
						
						 service.addOrder(cateorder);
						 
					
//						for(CategoryOrder li:list){
//							int oid = li.getOid();
//							System.out.println("������="+oid);
//						}
						target = "/WEB-INF/jsp/user/UserJieSuan.jsp";
					} catch (Exception e) {
						request.setAttribute("msg", "��Ӷ���ʧ��");
						e.printStackTrace();
						target = "/WEB-INF/msg.jsp";
					}
					request.getRequestDispatcher(target).forward(request, response);
					//��.ת����ͼ
				
		}
	
		//֧��
		protected void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("ȥ����ҳ�渶��");
			String cid = request.getParameter("cid");
			UserOrderSerivceImpl service = new UserOrderSerivceImpl();
			CategoryOrder cateorder  = new CategoryOrder();
			try {
				CategoryOrder orderById = service.getOrderById(cid);
				System.out.println("���ǵ������ʱ��="+orderById);
				request.setAttribute("orderById",orderById);
				target = "/WEB-INF/jsp/user/UserJieSuan.jsp";
				
			} catch (Exception e) {
				
			}
			// һ.�������
		
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		
		
		
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
