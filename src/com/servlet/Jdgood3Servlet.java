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

public class Jdgood3Servlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// �ַ�����
		String action = request.getParameter("action");

		if ("getAllJdgoodForAddJdgood3".equals(action)) {
			this.getAllJdgoodForAddJdgood3(request, response);
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
	
	public void getAllJdgoodForAddJdgood3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//1.�������
		//3.����ҵ���߼�
		Jdgood2Service service = new Jdgood2ServiceImpl();
		
		try {
			List<Jdgood2> list = service.getAllJdgoods();
			
			request.setAttribute("list", list);
			
			target = "/WEB-INF/jsp/admin/jdgood3/addJdgood3.jsp";
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
		String jname = request.getParameter("j3name");
		String fid	= request.getParameter("fid");
		
		//��.����ҵ���߼�
		Jdgood3 jdgood = new Jdgood3();
		jdgood.setJ3name(jname);
		jdgood.setFid(Integer.parseInt(fid));
		
		Jdgood3Service service = new Jdgood3ServiceImpl();
		try {
			service.addJdgood(jdgood);
			request.setAttribute("msg", "���������Ʒ����ɹ�");
		} catch (Exception e) {
			request.setAttribute("msg", "���������Ʒ����ʧ��");
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
		// 3.����ҵ���߼�
		Jdgood3Service service = new Jdgood3ServiceImpl();

		try {
			List<Jdgood3> list = service.getAllJdgoods();

			request.setAttribute("list", list);

			target = "/WEB-INF/jsp/admin/jdgood3/Jdgood3Main.jsp";

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
		// 1.�������
		String jid = request.getParameter("j3id");

		// 3.����ҵ���߼�
		Jdgood3Service service = new Jdgood3ServiceImpl();
		try {
			service.deleteJdgoodById(jid);
			request.setAttribute("msg", "ɾ��������Ʒ����ɹ�");

			this.getAllByPage(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "ɾ��������Ʒ����ʧ��");
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
			// 3.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);

		}


	}

	public void getOneForUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// 1.�������
		String jid = request.getParameter("j3id");

		// 2.����ҵ���߼�
		Jdgood3Service service = new Jdgood3ServiceImpl();
		Jdgood2Service service2 = new Jdgood2ServiceImpl();
		try {
			Jdgood3 jdgood = service.getJdgoodById(jid);
			
			request.setAttribute("jdgood3", jdgood);
			List<Jdgood2> list = service2.getAllJdgoods();
			
			request.setAttribute("list", list);
			target = "/WEB-INF/jsp/admin/jdgood3/updateJdgood3.jsp";
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
		String jid = request.getParameter("j3id");
		String jname = request.getParameter("j3name");
		String fid = request.getParameter("fid");  
		// ��.����ҵ���߼�
		Jdgood3 jdgood = new Jdgood3();
		jdgood.setJ3id(Integer.parseInt(jid));
		jdgood.setJ3name(jname);
		jdgood.setFid(Integer.parseInt(fid));
		// 3.����ҵ���߼�
		Jdgood3Service service = new Jdgood3ServiceImpl();
		try {
			service.updateJdgood(jdgood);
			request.setAttribute("msg", "�޸�������Ʒ����ɹ�!");
			this.getAllByPage(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "�޸�������Ʒ����ʧ��!");
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
		// 3.����ҵ���߼�

		
		try {
			Jdgood3ServiceImpl service = new Jdgood3ServiceImpl();
			
			int totalRecordSum = service.getTotalRecordCount();
			
			Jdgood3ServiceImpl service3 = new Jdgood3ServiceImpl();

			int intRequestPage = Integer.parseInt(requestPage);
			PageInfo pageInfo = new PageInfo(intRequestPage);
			
			pageInfo.setTotalRecordCount(totalRecordSum);
			
			List<Jdgood3> list = service3.getPageByQuery(pageInfo);

			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/jdgood3/Jdgood3Main.jsp";

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
		
		Jdgood3 jdgood = new Jdgood3();
		
		if(searchCondition!=null&&!searchCondition.trim().equals("")){
			jdgood.setJ3name(searchCondition);
			
		}
		
		try {
			
			
			//����������ѯһ����������¼
			Jdgood3Service service = new Jdgood3ServiceImpl();
			
			int totalRecordCount = service.getTotalRecordCount(jdgood);
			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));
			pageInfo.setTotalRecordCount(totalRecordCount);
			//3.����ҵ���߼�
			Jdgood3Service service3 = new Jdgood3ServiceImpl();

			List<Jdgood3> list = service3.getPageByQuery(jdgood, pageInfo);
			request.setAttribute("list", list);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("pageInfo", pageInfo);
			target = "/WEB-INF/jsp/admin/jdgood3/Jdgood3Main.jsp";
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
		//3.����ҵ���߼�
		Jdgood3Service service = new Jdgood3ServiceImpl();
		
		try {
			List<Jdgood3> list = service.getAllJdgoods();
			
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
