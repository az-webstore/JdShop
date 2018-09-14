package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.page.PageInfo;

import com.service.impl.Jdgood2ServiceImpl;
import com.service.impl.JdgoodServiceImpl;

import com.service.inter.Jdgood2Service;
import com.service.inter.JdgoodService;

import com.vo.Jdgood;
import com.vo.Jdgood2;

public class JdgoodServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			this.add(request, response);
		} else if ("getAll".equals(action)) {
			this.getAll(request, response);
		} else if ("delete".equals(action)) {
			this.delete(request, response);
		} else if ("getOneForUpdate".equals(action)) {
			this.getOneForUpdate(request, response);
		} else if ("update".equals(action)) {
			this.update(request, response);
		} else if ("getAllByPage".equals(action)) {
			this.getAllByPage(request, response);
		}else if("getPageByQuery".equals(action)){	
			this.getPageByQuery(request,response);
		}else if("getMenuForFirstPage".equals(action)){	
			this.getMenuForFirstPage(request,response);
		}else if("testjd".equals(action)){	
			this.testjd(request,response);
		}

	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		// һ.�������
		String jname = request.getParameter("jname");


		// ��.����ҵ���߼�
		Jdgood jdgood = new Jdgood();
		jdgood.setJname(jname);
		

		JdgoodService service = new JdgoodServiceImpl();
		try {
			service.addJdgood(jdgood);
			request.setAttribute("msg", "���һ����Ʒ����ɹ�");
		} catch (Exception e) {
			request.setAttribute("msg", "���һ����Ʒ����ʧ��");
			e.printStackTrace();
		}
		// ��.ת����ͼ
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);

		rd.forward(request, response);
	}

	public void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// 1.�������
		// 2.����ҵ���߼�
		JdgoodService service = new JdgoodServiceImpl();

		try {
			List<Jdgood> list = service.getAllJdgoods();

			request.setAttribute("list", list);

			target = "/WEB-INF/jsp/admin/jdgood/jdgoodMain.jsp";

		} catch (Exception e) {

			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��");
			target = "/WEB-INF/msg.jsp";

			e.printStackTrace();
		}
		// 3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		//1.�������
		String cid = request.getParameter("jid");
		
		//2.����ҵ���߼�
		try {
			//ɾ��ǰ Ӧ���Ȳ�ѯ��û�ж�Ӧ�Ķ�����Ʒ����
			Jdgood2Service service = new Jdgood2ServiceImpl();
			
			int count = service.getJdgood2SumByJdgood(cid);
			if(count>0){//�ж�Ӧ�Ķ�����Ʒ����		
				request.setAttribute("msg", "ɾ��ʧ��");
				request.setAttribute("msgDetail", "���ҵ������� ����ɾ�������� �ټ���ɾ��");
			}else{
				JdgoodService service2 = new JdgoodServiceImpl();
				service2.deleteJdgoodById(cid);
				request.setAttribute("msg", "ɾ���ɹ�!");
				
			}
			
			this.getPageByQuery(request, response);
			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
			//3.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);
		}


	}

	public void getOneForUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// 1.�������
		String jid = request.getParameter("jid");

		// 2.����ҵ���߼�
		JdgoodService service = new JdgoodServiceImpl();

		try {
			Jdgood jdgood = service.getJdgoodById(jid);
			request.setAttribute("jdgood", jdgood);
			target = "/WEB-INF/jsp/admin/jdgood/updateJdgood.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��ѯ����һ����Ʒ����ʧ��!");
			target = "/WEB-INF/msg.jsp";
		}
		// 3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// 1.�������
		String jid = request.getParameter("jid");
		String jname = request.getParameter("jname");
		
		// ��.����ҵ���߼�
		Jdgood jdgood = new Jdgood();
		jdgood.setJid(Integer.parseInt(jid));
		jdgood.setJname(jname);
		
		// 2.����ҵ���߼�
		JdgoodService service = new JdgoodServiceImpl();
		try {
			service.updateJdgood(jdgood);
			request.setAttribute("msg", "�޸�һ����Ʒ����ɹ�!");
			this.getAllByPage(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "�޸�һ����Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
			// 3.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);
		}

	}

	public void getAllByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// 1.�������
		String requestPage = request.getParameter("requestPage");
		// 2.����ҵ���߼�

		
		try {
			JdgoodServiceImpl service = new JdgoodServiceImpl();
			
			int totalRecordSum = service.getTotalRecordCount();
			
			JdgoodServiceImpl service2 = new JdgoodServiceImpl();

			int intRequestPage = Integer.parseInt(requestPage);
			PageInfo pageInfo = new PageInfo(intRequestPage);
			
			pageInfo.setTotalRecordCount(totalRecordSum);
			
			List<Jdgood> list = service2.getPageByQuery(pageInfo);

			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/jdgood/JdgoodMain.jsp";

		} catch (Exception e) {

			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��");
			target = "/WEB-INF/msg.jsp";

			e.printStackTrace();
		}
		// 3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	public void getPageByQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String target = "";
		//1.�������
		String searchCondition = request.getParameter("searchCondition");
		
		String requestPage = request.getParameter("requestPage");
		if(requestPage==null){
			
			requestPage = "1";
		}
		
		Jdgood jdgood = new Jdgood();
		
		if(searchCondition!=null&&!searchCondition.trim().equals("")){
			jdgood.setJname(searchCondition);
			
		}
		
		try {
			
			
			//����������ѯһ����������¼
			JdgoodService service = new JdgoodServiceImpl();
			
			int totalRecordCount = service.getTotalRecordCount(jdgood);
			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));
			pageInfo.setTotalRecordCount(totalRecordCount);
			//2.����ҵ���߼�
			JdgoodService service2 = new JdgoodServiceImpl();

			List<Jdgood> list = service2.getPageByQuery(jdgood, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/jdgood/JdgoodMain.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
		
	}
	public void getMenuForFirstPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String target = "";
		//1.�������
		//2.����ҵ���߼�
		JdgoodService service = new JdgoodServiceImpl();
		
		try {
			List<Jdgood> list = service.getAllJdgoods();
			
			request.setAttribute("list", list);
			
			target = "/jdchenying/base.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	//���ǲ���
	public void testjd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String target = "";
	
		//1.�������
		//2.����ҵ���߼�
		JdgoodService service = new JdgoodServiceImpl();
		
		
		
		try {
			List<Jdgood> list = service.getAllJdgoods();
			
			request.setAttribute("list", list);
			
			target = "/WEB-INF/jsp/user/fs.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯ������Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	

}
