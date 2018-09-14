package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.page.PageInfo;


import com.service.impl.Jdgood2ServiceImpl;
import com.service.impl.JdgoodServiceImpl;
import com.service.impl.JdproductServiceImpl;


import com.service.inter.Jdgood2Service;
import com.service.inter.JdgoodService;
import com.service.inter.JdproductService;

import com.util.ProductDictionary;

import com.vo.Jdgood;
import com.vo.Jdgood2;
import com.vo.Jdproduct;





public class JdproductServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// �ַ�����
		String action = request.getParameter("action");

		if ("getAllJdgoodForAddProduct".equals(action)) {
			this.getAllJdgoodForAddProduct(request, response);
		}else if("getJdgood2ByJdgoodForAddProduct".equals(action)){
			this.getJdgood2ByJdgoodForAddProduct(request,response);
		}else if("add".equals(action)){
			this.add(request,response);
		}else if ("getAllByPage".equals(action)) {
			this.getAllByPage(request, response);
		}else if ("delete".equals(action)) {
			this.delete(request, response);
		}else if ("getPageByQuery".equals(action)) {
			this.getPageByQuery(request, response);
		}else if ("getOneForUpdate".equals(action)) {
			this.getOneForUpdate(request, response);
		}else if ("update".equals(action)) {
			this.update(request, response);
		}else if ("productUp".equals(action)) {
			this.productUp(request, response);
		}else if ("productDown".equals(action)) {
			this.productDown(request, response);
		}else if ("getOneForUpload".equals(action)) {
			this.getOneForUpload(request, response);
		}else if ("uploadImage".equals(action)) {
			this.uploadImage(request, response);
		}
		
		

	}
	public void getAllJdgoodForAddProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		
		JdgoodService service = new JdgoodServiceImpl();
		
		try {
			List<Jdgood> list = service.getAllJdgoods();
			
			request.setAttribute("list", list);
			
			target = "/WEB-INF/jsp/admin/product/addJdproduct.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯһ����Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	public void getJdgood2ByJdgoodForAddProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//���÷�������Ӧ����������  Ϊapplication/json 
		response.setContentType("application/json");
		String target = "";
		
		//1.�������
		String category1Id = request.getParameter("category1Id");
		
		//2.����ҵ���߼�
		
		Jdgood2Service service = new Jdgood2ServiceImpl();
		
		//3.���
		PrintWriter out = response.getWriter();
		
		try {
			List<Jdgood2> list = service.getAllJdgood2ByJdgood(Integer.parseInt(category1Id));
			
			Gson gson = new Gson();
			
			String jdgood2ListJson = gson.toJson(list);
			
			out.println(jdgood2ListJson);
			
			out.flush();
			
			out.close();
		
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯ������Ʒ����ʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
			//3.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);
		}
		
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		//һ.�������
		String pname = request.getParameter("pname");
		String price = request.getParameter("price");
		String productSum = request.getParameter("productSum");
		String dianpuName = request.getParameter("dianpuName");
		String pdesc = request.getParameter("pdesc");
		String cid = request.getParameter("cid");
		
		Jdproduct product = new Jdproduct();
		product.setPname(pname);
		product.setPrice(Double.parseDouble(price));
		product.setProductSum(Integer.parseInt(productSum));
		product.setDianpuName(dianpuName);
		product.setPdesc(pdesc);
		product.setCid(Integer.parseInt(cid));
		//��.����ҵ���߼�
		
		JdproductService service = new JdproductServiceImpl();
		try {
			service.addProduct(product);
			request.setAttribute("msg", "�����Ʒ�ɹ�");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		//��.ת����ͼ
		target = "/WEB-INF/msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(target);
		
		rd.forward(request, response);
	}
	public void getAllByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String target = request.getParameter("target");
		
		// һ.�������
		String requestPage = request.getParameter("requestPage");
		System.out.println(requestPage);
		// ��.����ҵ���߼�
		PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));

		// ��ѯ�ܹ��ж�������¼
		JdproductServiceImpl service1 = new JdproductServiceImpl();

		int totalRecordSum;
		try {
			totalRecordSum = service1.getTotalRecordSum();
			
			pageInfo.setTotalRecordCount(totalRecordSum);

			JdproductServiceImpl service2 = new JdproductServiceImpl();

			List<Jdproduct> list = service2.getAllByPage(pageInfo);
			target = "/WEB-INF/jsp/admin/product/" + target + ".jsp";
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		
		// ��.ת����ͼ
	
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// һ.�������
		String pid = request.getParameter("pid");
		System.out.println("********look here**********");
		// ��.����ҵ���߼�
		System.out.println(pid);
		JdproductServiceImpl service = new JdproductServiceImpl();

		try {
			service.deleteProductById(pid);
			request.setAttribute("msg", "ɾ���ɹ���");
			this.getPageByQuery(request, response);	
		} catch (Exception e) {
			request.setAttribute("msg", "ɾ��ʧ�ܣ�");
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
			request.getRequestDispatcher(target).forward(request, response);
		}

		
		

	}
	
	public void getPageByQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String target = request.getParameter("target");
		// һ.�������
		String requestPage = request.getParameter("requestPage");

		System.out.println("requestPage=" + requestPage);
		if (requestPage == null) {
			requestPage = "1";
		}

		try {
			String searchCondition = request.getParameter("searchCondition");
			System.out.println("searchCondition=" + searchCondition);
			// ��.����ҵ���߼�
			PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage));

			//����ѯ������ѯ �����ѯ����ܹ��ж�������¼
			JdproductServiceImpl service1 = new JdproductServiceImpl();

			Jdproduct product = new Jdproduct();
			
			//����ǰ���Ʒ�������Ʋ�ѯ ��Ҫ�Ѵ���������Ʒ��������ת����cid
			Jdgood2Service service = new Jdgood2ServiceImpl();
			
			Jdgood2 category = service.getJdgoodByName(searchCondition);
			if (searchCondition != null && !searchCondition.trim().equals("")) {
				product.setPname(searchCondition);
				
				//��������¼�ҳ�������  �Ͱ� ���¼�״̬��ѯ   �����ǰ��������Ʋ�ѯ
				//����ҳ��(�����ϴ�ͼƬ�͹�����Ʒ���������ǰ� �������Ʋ�ѯ��)
				if("productMainUpDown".equals(target)){
					System.out.println("************look here***************");
					System.out.println(searchCondition);
					Integer onsale = ProductDictionary.onsaleStrToInt(searchCondition);
					product.setOnsale(onsale);
				}else{
					product.setDianpuName(searchCondition);
				}
				
				if(category!=null){
					product.setCid(category.getJ2id());
				}
				
			}

			int totalRecordSum = service1.getTotalRecordSumBySearchCondition(product);
			pageInfo.setTotalRecordCount(totalRecordSum);

			JdproductServiceImpl service2 = new JdproductServiceImpl();
			List<Jdproduct> list = service2.getPageByQuery(product, pageInfo);

			// ��.ת����ͼ
			target = "/WEB-INF/jsp/admin/product/" + target + ".jsp";
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("searchCondition", searchCondition);
		}catch (Exception e) {
			
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void getOneForUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		
		// һ.�������
		String pid = request.getParameter("pid");
		String requestPage = request.getParameter("requestPage");
		String searchCondition = request.getParameter("searchCondition");
		// ��.����ҵ���߼�
		try {
			JdproductServiceImpl service = new JdproductServiceImpl();
			Jdproduct product = service.getProductById(pid);

			int cid = product.getCid();

			Jdgood2Service categoryService2 = new Jdgood2ServiceImpl();

			int parentCid = categoryService2.getParentJdgoodById(cid);

			// ��ѯ�����е�һ����Ʒ����
			JdgoodService categoryService = new JdgoodServiceImpl();

			List<Jdgood> categoryList = categoryService.getAllJdgoods();
			// ��.ת����ͼ
			target = "/WEB-INF/jsp/admin/product/updateProduct.jsp";
			request.setAttribute("product", product);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("parentCid", parentCid);
			request.setAttribute("target", request.getParameter("target"));
			request.setAttribute("requestPage", requestPage);
			request.setAttribute("searchCondition", searchCondition);
		} catch (Exception e) {
			
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}

		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = "";
		// һ.�������(��������)
		String pid = request.getParameter("pid");
		String pname = request.getParameter("pname");
		String price = request.getParameter("price");
		String productSum = request.getParameter("productSum");
		String dianpuName = request.getParameter("dianpuName");
		String pdesc = request.getParameter("pdesc");
		String cid = request.getParameter("cid");

		// ��.����ҵ���߼�
		Jdproduct product = new Jdproduct();
		product.setPid(Integer.parseInt(pid));
		product.setPname(pname);
		product.setPrice(Double.parseDouble(price));
		product.setProductSum(Integer.parseInt(productSum));
		product.setDianpuName(dianpuName);
		product.setPdesc(pdesc);
		product.setCid(Integer.parseInt(cid));

		JdproductService service = new JdproductServiceImpl();
		try {
			service.updateProduct(product);
			request.setAttribute("msg", "�޸ĳɹ���");
			// ��.ת����ͼ
			this.getPageByQuery(request, response);
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public void productUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// һ.�������
		String pid = request.getParameter("pid");

		// ��.����ҵ���߼�
		JdproductServiceImpl service = new JdproductServiceImpl();

		try {
			service.productUp(Integer.parseInt(pid));
			request.setAttribute("msg", "���Ϊ" + pid +"����Ʒ�ϼܳɹ�!");

			this.getPageByQuery(request, response);
		} catch (Exception e) {
			target = "/WEN-INF/msg.jsp";
			e.printStackTrace();
			request.getRequestDispatcher(target).forward(request, response);
		}

		

	}
	
	public void productDown(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		// һ.�������
		String pid = request.getParameter("pid");

		// ��.����ҵ���߼�
		JdproductServiceImpl service = new JdproductServiceImpl();

		try {
			service.productDown(Integer.parseInt(pid));
			request.setAttribute("msg", "���Ϊ" + pid +"����Ʒ�¼ܳɹ�!");

			this.getPageByQuery(request, response);
		} catch (Exception e) {
			target = "/WEN-INF/msg.jsp";
			e.printStackTrace();
			request.getRequestDispatcher(target).forward(request, response);
		}

		

	}
	
	public void getOneForUpload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// һ.�������
		String pid = request.getParameter("pid");
		String requestPage = request.getParameter("requestPage");
		String searchCondition = request.getParameter("searchCondition");
		// ��.����ҵ���߼�
		try {
			JdproductServiceImpl service = new JdproductServiceImpl();
			Jdproduct product = service.getProductById(pid);

			// ��.ת����ͼ
			target = "/WEB-INF/jsp/admin/product/uploadProductImage.jsp";
			request.setAttribute("product", product);
			request.setAttribute("target", request.getParameter("target"));
			request.setAttribute("requestPage", requestPage);
			request.setAttribute("searchCondition", searchCondition);
		} catch (Exception e) {
			
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", "��ѯ��һ��Ʒ��Ϣʧ��");
			e.printStackTrace();
		}

		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void uploadImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String target = "/WEB-INF/msg.jsp";

		// һ.�������

		// ��.����ҵ���߼�
		ServletConfig servletConfig = this.getServletConfig();
		String productListUploadPath = servletConfig.getInitParameter("productImageUploadPath");
		
		if(productListUploadPath==null||productListUploadPath.trim().equals("")){
			request.setAttribute("msg", "ϵͳδָ���ϴ�·��");
			
		}else{
			JdproductServiceImpl service = new JdproductServiceImpl();
			try {
				service.upload(request, productListUploadPath);
				
				request.setAttribute("msg", "�ϴ�ͼƬ�ɹ�!");
			} catch (Exception e) {
				
				request.setAttribute("msg", e.getMessage());
				e.printStackTrace();
			}
		}

		

		// ��.ת����ͼ
		
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
