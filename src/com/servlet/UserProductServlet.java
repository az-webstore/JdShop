package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.page.OrderCondition;
import com.page.PageInfo;
import com.service.impl.Jdgood2ServiceImpl;
import com.service.impl.Jdgood3ServiceImpl;
import com.service.impl.JdgoodServiceImpl;
import com.service.impl.JdproductServiceImpl;
import com.service.impl.ShoppingCartServiceImpl;
import com.service.impl.UserProductServiceImpl;
import com.service.inter.Jdgood2Service;
import com.service.inter.JdgoodService;
import com.service.inter.JdproductService;
import com.service.inter.UserProductService;
import com.vo.Jdgood;
import com.vo.Jdgood2;
import com.vo.Jdgood3;
import com.vo.Jdproduct;
import com.vo.Login;

public class UserProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("getProductListByCategory".equals(action)) {
			this.getProductListByCategory(request, response);
		}else if ("getPageByQuery".equals(action)) {
			this.getPageByQuery(request, response);
		}else if ("getOneProduct".equals(action)) {
			this.getOneProduct(request, response);
		}else if ("getOneProductUrl".equals(action)) {
			this.getOneProductUrl(request, response);
		}else if("addCar".equals(action)){	
			this.addCar(request,response);
		}else if("JieSuan".equals(action)){	
			this.JieSuan(request,response);
		}else if("getAll".equals(action)){
			this.getAll(request, response);
		}else if("deleteCategory".equals(action)){
			this.deleteCategory(request,response);	
		}
		
	}

	public void getProductListByCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in UsreProductServlet getProductListByCategory!!!");
		String target = "";
		// һ.�������
		
		String cid = request.getParameter("cid");// 62 ���ֻ�
		System.out.println(cid);
		
		
		String requestPage = request.getParameter("requestPage");
		System.out.println(requestPage);
		//��ȡһ����Ʒ����
		JdgoodServiceImpl service2 = new JdgoodServiceImpl();
		//��ȡ������Ʒ����
		Jdgood2ServiceImpl service3 = new Jdgood2ServiceImpl();
		//��ȡ������Ʒ����
		Jdgood3ServiceImpl service4 = new Jdgood3ServiceImpl();
		String orderCondition = request.getParameter("orderCondition");
		String ascOrDesc = request.getParameter("ascOrDesc");
		
		OrderCondition orderConditionObj = new OrderCondition();
		
		if(orderCondition!=null&&!orderCondition.trim().equals("")){
			orderConditionObj.setOrderCondition(orderCondition);
			orderConditionObj.setAscOrDesc(ascOrDesc);
		}
		
		
		// ��.����ҵ���߼�
		PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage), 8);// ÿҳ��ʾ8����¼

		List<Jdproduct> list;
		try {
			
			
			//��ѯ����������Ʒ
			Jdgood2 jdgood2 = service3.getJdgoodById(cid);
			
			System.out.println("jdgood2="+jdgood2);
			//��ȡ����fid
			int fid = jdgood2.getFid();
			System.out.println("fid="+fid);
			//��ѯһ����Ʒ�����µĶ�����Ʒ����
			List<Jdgood3> jdgood3s = service4.getAllJdgood3ByJdgood(Integer.parseInt(cid));
			//System.out.println("jdgood2s="+jdgood2s);
			//��ѯ����һ����Ʒ
			Jdgood jdgood = service2.getJdgoodById(Integer.toString(fid));
			// ��ѯָ����Ʒ������ �ܹ��ж�������Ʒ��¼
			UserProductServiceImpl service1 = new UserProductServiceImpl();
			int totalRecordSum = service1.getTotalRecordSumByCategory(cid);
			pageInfo.setTotalRecordCount(totalRecordSum);
			// ������Ʒ���� ��ѯ ��Ʒ
			UserProductServiceImpl service = new UserProductServiceImpl();
			
			
			list = service.getAllByPageByCategory(pageInfo, cid,orderConditionObj);
			// ��.ת����ͼ
			request.setAttribute("jdgood", jdgood);
			request.setAttribute("jdgood2", jdgood2);
			request.setAttribute("jdgood3s", jdgood3s);
			request.setAttribute("totalRecordSum", totalRecordSum);
			request.setAttribute("list", list);
			request.setAttribute("cid", cid);
			request.setAttribute("pageInfo", pageInfo);
			
			request.setAttribute("orderConditionObj", orderConditionObj);
			target = "/WEB-INF/jsp/user/jdlist.jsp";
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
		}

		request.getRequestDispatcher(target).forward(request, response);
	}

	public void getPageByQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in getPageByQuery");
		String cid = request.getParameter("cid");
		
		
		System.out.println("cid=" + cid);
		if (cid != null && !cid.trim().equals("")) {
			// ��˵�����Ʒ�����ʱ�� ʹ�����
			System.out.println("��˵�������");
			this.getProductListByCategory(request, response);
		} else {
			// ������ʹ��
			System.out.println("������ʹ��");
			this.getPageByQuery2(request, response);
		}
	}

	// ������ʹ�����
	public void getPageByQuery2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in UserProductServlet--->getPageByQuery1234");
		String target = "";

		// һ.�������

		String requestPage = request.getParameter("requestPage");

		System.out.println("requestPage=" + requestPage);
		if (requestPage == null) {
			requestPage = "1";
		}

		String searchCondition = request.getParameter("searchCondition");
		System.out.println("searchCondition=" + searchCondition);
		
		String orderCondition = request.getParameter("orderCondition");
		System.out.println("orderCondition=" + orderCondition);
		
		String ascOrDesc = request.getParameter("ascOrDesc");
		System.out.println("ascOrDesc=" + ascOrDesc);
		// ��.����ҵ���߼�
		PageInfo pageInfo = new PageInfo(Integer.parseInt(requestPage), 8);

		// ����ѯ������ѯ �����ѯ����ܹ��ж�������¼
		UserProductServiceImpl service1 = new UserProductServiceImpl();

		Jdproduct product = new Jdproduct();

		// ����ǰ���Ʒ�������Ʋ�ѯ ��Ҫ�Ѵ���������Ʒ��������ת����cid
		Jdgood2Service service = new Jdgood2ServiceImpl();

		
		try {
			Jdgood2 category  = service.getJdgoodByName(searchCondition);
			if (searchCondition != null && !searchCondition.trim().equals("")) {
				product.setPname(searchCondition);

				product.setDianpuName(searchCondition);

				if (category != null) {
					product.setCid(category.getJ2id());
				}

			}

			OrderCondition orderConditionObj = new OrderCondition();
			//������������
			if(orderCondition!=null&&!orderCondition.trim().equals("")){
				orderConditionObj.setOrderCondition(orderCondition);
				orderConditionObj.setAscOrDesc(ascOrDesc);
				product.setOrderConditionObj(orderConditionObj);
			}
			
			
			
			int totalRecordSum = service1
					.getTotalRecordSumBySearchCondition(product);
			pageInfo.setTotalRecordCount(totalRecordSum);

			UserProductServiceImpl service2 = new UserProductServiceImpl();
			List<Jdproduct> list = service2.getPageByQuery(product, pageInfo);

			// ��.ת����ͼ
			target = "/WEB-INF/jsp/user/jdlist.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("orderConditionObj", orderConditionObj);
		
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}

	
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	//��ѯ��һ��Ʒ
	public void getOneProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		//1.�������
		String pid = request.getParameter("pid");
		
		//2.����ҵ���߼�
		UserProductService service = new UserProductServiceImpl();
		try {
			Jdproduct product = service.getProductById(pid);
			request.setAttribute("product", product);
			target = "/WEB-INF/jsp/user/MyJsp.jsp";  
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);;
	}
	
	public void getOneProductUrl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("ʹ��URL��д��ʽʵ�ֻỰ����");
		String target = "";
		//һ.�������
		String pid = request.getParameter("pid");
		
		//��.����ҵ���߼�
		
		try {
			UserProductServiceImpl service = new UserProductServiceImpl();
			Jdproduct product = service.getProductById(pid);
			
			
			String url = "ShoppingCartServlet?action=addToCart&pid=" + pid;
			
			// ʹ��URL��д���� ʵ�� session  ������ cookie �����õ����
			
			HttpSession session = request.getSession(true);
			
			//���cookie ��������  ������дurl
			//���û�н���cookie ԭ���������url
			String afterURL = response.encodeURL(url);
			System.out.println(afterURL);
			
			//��.ת����ͼ
			request.setAttribute("product", product);
			request.setAttribute("afterURL", afterURL);
			target = "/WEB-INF/jsp/user/productURL.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			target = "/WEB-INF/msg.jsp";
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	//���빺�ﳵ
	public void addCar(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

		String target = "";
		// һ.�������
		
		String csum = request.getParameter("ShuLiang");
		String pid = request.getParameter("pid");
		try {
			JdproductServiceImpl categoryservlet = new JdproductServiceImpl();
			Jdproduct category2 = categoryservlet.getProductById(pid);
			category2.setProductSum(Integer.parseInt(csum));
			ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();
			
			// true ����к͵�ǰ�û�(���������)�������session ������� session
			// ���û�� ��Ϊ����û�(���������)����һ����Ӧ��session
			// false ����к͵�ǰ�û�(���������)�������session ������� session
			// û���������session ����null
			HttpSession session = request.getSession(true);// ����Ҫʹ��true
			shoppingCartService.addToCart(session, category2, Integer.parseInt(csum));
			// ��.ת����ͼ
			target ="/WEB-INF/jsp/user/JoinSucceed.jsp";
			request.setAttribute("category2", category2);
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		// ��.����ҵ���߼�

		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void JieSuan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ҵ���߼�
		String target = "";
		String toWhere=null;
		String csum = request.getParameter("JieSuanTxt");
		System.out.println("����ҳ���ϻ�ȡ����sum="+csum);
		int csum2=0;
		try {
			HttpSession session = request.getSession(true);
			Login user = (Login)session.getAttribute("user");
//					for(Category2 category:list){
//						csum2 = category.getCsum();
//						int pid2 = category.getPid();
//						System.out.println("���ǹ��ﳵ����ʾ��Sum2="+csum2);
//					}
			if(user!=null){
				List<Jdproduct> list = (List<Jdproduct>) session.getAttribute("shoppingCart");
				request.setAttribute("list", list);
				target="WEB-INF/jsp/user/jiesuan.jsp";
			}else{
				System.out.println("toWhere");
				session.setAttribute("toWhere", "jiesuan");
				target = "/WEB-INF/jsp/JD/login.jsp";
			}
			
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// һ.�������
		// ��.����ҵ���߼�
		
		// ��.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	//��ѯ���ﳵ�����е�����
	public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ҵ���߼�
		String target = "";
		HttpSession session = request.getSession(true);
		List<Jdproduct> list = (List<Jdproduct>) session.getAttribute("shoppingCart");
		if(list==null||list.size()==0){
			target = "WEB-INF/jsp/user/myCartNull.jsp";
		}else{
			request.setAttribute("list", list);
			target="WEB-INF/jsp/product/myCartMain.jsp";
		}
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	//ɾ��
		public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String target = "";
			//1.�������
			String pid = request.getParameter("pid");
			try {
				HttpSession session = request.getSession(true);
				List<Jdproduct> list = (List<Jdproduct>) session.getAttribute("shoppingCart");
				//�Ȳ�ѯ��û�ж�Ӧ�Ķ�����Ʒ���� ����� ����ɾ��
				for(int i=0;i<list.size();i++){
					if(list.get(i).getPid()==Integer.parseInt(pid)){
						list.remove(i);
					}
				}
					//2.����ҵ���߼�
				this.getAll(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", e.getMessage());
				target = "/WEB-INF/msg.jsp";
				e.printStackTrace();
				//3.ת����ͼ
				request.getRequestDispatcher(target).forward(request, response);
			}
			
			

		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
