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
import com.service.impl.Jdgood3ServiceImpl;
import com.service.impl.JdgoodServiceImpl;
import com.service.inter.Jdgood2Service;
import com.service.inter.Jdgood3Service;
import com.service.inter.JdgoodService;
import com.vo.*;

public class Jdgood2Servlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// �ַ�����
		String action = request.getParameter("action");

		if ("getAllJdgoodForAddJdgood2".equals(action)) {
			this.getAllJdgoodForAddJdgood2(request, response);
		}else if("add".equals(action)){	
			this.add(request,response);
		}else if("getMenuForFirstPage".equals(action)){	
			this.getMenuForFirstPage(request,response);
		}else if ("getAll".equals(action)) {
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
		}
		

	}
	
	public void getAllJdgoodForAddJdgood2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//1.�������
		//2.����ҵ���߼�
		JdgoodService service = new JdgoodServiceImpl();
		
		try {
			List<Jdgood> list = service.getAllJdgoods();
			
			request.setAttribute("list", list);
			
			target = "/WEB-INF/jsp/admin/jdgood2/addJdgood2.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯ������Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	
	
	
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//һ.�������
		String jname = request.getParameter("j2name");
		String fid	= request.getParameter("fid");
		
		//��.����ҵ���߼�
		Jdgood2 jdgood = new Jdgood2();
		jdgood.setJ2name(jname);
		jdgood.setFid(Integer.parseInt(fid));
		
		Jdgood2Service service = new Jdgood2ServiceImpl();
		try {
			service.addJdgood(jdgood);
			request.setAttribute("msg", "��Ӷ�����Ʒ����ɹ�");
		} catch (Exception e) {
			request.setAttribute("msg", "��Ӷ�����Ʒ����ʧ��");
			e.printStackTrace();
		}
		//��.ת����ͼ
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);
		
		rd.forward(request, response);
	}
	
	public void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// 1.�������
		// 2.����ҵ���߼�
		Jdgood2Service service = new Jdgood2ServiceImpl();

		try {
			List<Jdgood2> list = service.getAllJdgoods();

			request.setAttribute("list", list);

			target = "/WEB-INF/jsp/admin/jdgood2/Jdgood2Main.jsp";

		} catch (Exception e) {

			request.setAttribute("msg", "��ѯ������Ʒ����ʧ��");
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
		String cid = request.getParameter("j2id");
		
		//2.����ҵ���߼�
		try {
			//ɾ��ǰ Ӧ���Ȳ�ѯ��û�ж�Ӧ�Ķ�����Ʒ����
			Jdgood3Service service = new Jdgood3ServiceImpl();
			
			int count = service.getJdgood3SumByJdgood(cid);
			if(count>0){//�ж�Ӧ�Ķ�����Ʒ����		
				request.setAttribute("msg", "ɾ��ʧ��");
				request.setAttribute("msgDetail", "���ҵ������� ����ɾ�������� �ټ���ɾ��");
			}else{
				Jdgood2Service service2 = new Jdgood2ServiceImpl();
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
		String jid = request.getParameter("j2id");

		// 2.����ҵ���߼�
		Jdgood2Service service = new Jdgood2ServiceImpl();
		JdgoodService service2 = new JdgoodServiceImpl();
		try {
			Jdgood2 jdgood = service.getJdgoodById(jid);
			
			request.setAttribute("jdgood2", jdgood);
			List<Jdgood> list = service2.getAllJdgoods();
			
			request.setAttribute("list", list);
			target = "/WEB-INF/jsp/admin/jdgood2/updateJdgood2.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��ѯ����������Ʒ����ʧ��!");
			target = "/WEB-INF/msg.jsp";
		}
		// 3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// 1.�������
		String jid = request.getParameter("j2id");
		String jname = request.getParameter("j2name");
		String fid = request.getParameter("fid");  
		// ��.����ҵ���߼�
		Jdgood2 jdgood = new Jdgood2();
		jdgood.setJ2id(Integer.parseInt(jid));
		jdgood.setJ2name(jname);
		jdgood.setFid(Integer.parseInt(fid));
		// 2.����ҵ���߼�
		Jdgood2Service service = new Jdgood2ServiceImpl();
		try {
			service.updateJdgood(jdgood);
			request.setAttribute("msg", "�޸Ķ�����Ʒ����ɹ�!");
			this.getAllByPage(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "�޸Ķ�����Ʒ����ʧ��!");
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
			Jdgood2ServiceImpl service = new Jdgood2ServiceImpl();
			
			int totalRecordSum = service.getTotalRecordCount();
			
			Jdgood2ServiceImpl service2 = new Jdgood2ServiceImpl();

			int intRequestPage = Integer.parseInt(requestPage);
			PageInfo pageInfo = new PageInfo(intRequestPage);
			
			pageInfo.setTotalRecordCount(totalRecordSum);
			
			List<Jdgood2> list = service2.getPageByQuery(pageInfo);

			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/jdgood2/Jdgood2Main.jsp";

		} catch (Exception e) {

			request.setAttribute("msg", "��ѯ������Ʒ����ʧ��");
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
		
		Jdgood2 jdgood = new Jdgood2();
		
		if(searchCondition!=null&&!searchCondition.trim().equals("")){
			jdgood.setJ2name(searchCondition);
			
		}
		
		try {
			
			
			//����������ѯһ����������¼
			Jdgood2Service service = new Jdgood2ServiceImpl();
			
			int totalRecordCount = service.getTotalRecordCount(jdgood);
			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));
			pageInfo.setTotalRecordCount(totalRecordCount);
			//2.����ҵ���߼�
			Jdgood2Service service2 = new Jdgood2ServiceImpl();

			List<Jdgood2> list = service2.getPageByQuery(jdgood, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/jdgood2/Jdgood2Main.jsp";
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
		Jdgood2Service service = new Jdgood2ServiceImpl();
		
		try {
			List<Jdgood2> list = service.getAllJdgoods();
			
			request.setAttribute("list", list);
			
			target = "/jdchenying/base.jsp";
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
