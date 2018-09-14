package com.servlet;


import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.service.impl.LoginServiceImpl;
import com.service.inter.LoginService;
import com.util.MailUtils;
import com.util.Random;
import com.vo.Jdproduct;
import com.vo.Login;


public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// �ַ�����
		String action = request.getParameter("action");

		if("add".equals(action)){	
			this.add(request,response);
		}else if("getAllLogins".equals(action)){	
			this.getAllLogins(request,response);
		}else if("login".equals(action)){	
			this.login(request,response);
		}else if("checkUsername".equals(action)){	
			this.checkUsername(request,response);
		}else if("checkEmail".equals(action)){	
			this.checkEmail(request,response);
		}else if("checkPhone".equals(action)){	
			this.checkPhone(request,response);
		}else if("active".equals(action)){
			this.active(request,response);			
		}else if("pictureCheck".equals(action)){
			this.pictureCheck(request,response);			
		}else if("logout".equals(action)){
			this.logout(request,response);			
		}			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}	

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "";
		//һ.�������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String phoneNumber = request.getParameter("phoneNumber");
		String code=Random.getCode();
		//�����ʼ�
		try {
			MailUtils.sendMail(mail, code);
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Login login = new Login();								
		login.setUsername(username);
		login.setPassword(password);
		login.setMail(mail);
		login.setCode(code);
		login.setPhoneNumber(phoneNumber);
		System.out.println("");
		Login user;	
		
		//��.����ҵ���߼�
		LoginService service = new LoginServiceImpl();
			try {
				service.addLogin(login);
				request.setAttribute("msg", username);
				target = "/WEB-INF/jsp/JD/registeSuccess.jsp";
			} catch (Exception e) {
				request.setAttribute("msg", "ע��ʧ��");
				e.printStackTrace();
			}
			
		//��.ת����ͼ
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		
		rd.forward(request, response);
	}
	
	public void getAllLogins(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.�������
		//2.����ҵ���߼�
		LoginService service = new LoginServiceImpl();
		String target = request.getParameter("target");
		try {
			List<Login> list = service.getAllLogin();			
			request.setAttribute("list", list);
			target = "/WEB-INF/jsp/JD/"+ target;
			System.out.println(target);
		} catch (Exception e) {
			request.setAttribute("msg", "��ѯ�û���Ϣʧ��!");
			e.printStackTrace();
			target = "/WEB-INF/msg.jsp";
		}
		//3.ת����ͼ
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("loginSerlet�е�login����");
		String target = "";
		//һ.�������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		//��.����ҵ���߼� 
		try {
			LoginServiceImpl service = new LoginServiceImpl();
			Login user = service.login(username, password);
			
			if(user!=null){//�����û����������ҵ��˸��û�
				
				Integer isActive = user.getIsActive();
				System.out.println("isActive="+isActive);
				if(isActive==1){//��¼�ɹ� 
				
					HttpSession session = request.getSession(true);
					session.setAttribute("user", user);//��user����浽session�� �Ժ�ÿ��ҳ���ж�����ȡ����ʹ��
					
					String toWhere = (String)session.getAttribute("toWhere");
					System.out.println("toWhere="+toWhere);
					if(toWhere==null||toWhere.trim().equals("")){
						//����������ҳ firstPage
						//��ѯ��Ʒ���� ������ʾ��ҳ�Ĳ˵�
						//CategoryService categoryService = new CategoryServiceImpl();
						//List<Category> list = categoryService.getAllCategorys();
						//request.setAttribute("list", list);
						target = "/jsp/user/index.jsp";
					}else if("jiesuan".equals(toWhere)){
						//���� ����ҳ
						//��ѯ���ﳵ��Ķ��� �ڽ���ҳ  ����ʾһ��  Ϊ��ȷ��
						List<Jdproduct> list = (List<Jdproduct>)session.getAttribute("shoppingCart");
						//��.ת����ͼ
						request.setAttribute("list", list);
						target="WEB-INF/jsp/JD/jiesuan.jsp";
					}
					
					
										
				}else{
					//��¼ʧ�� ���ص�¼ҳ�� ��ʾ "�û���δ����,�뼤����ٳ��Ե�¼"
					
					request.setAttribute("msg", "�û���δ����,�뼤����ٳ��Ե�¼");
					target = "/jsp/user/login.jsp";
				}
			}else{
				//��¼ʧ�� ���ص�¼ҳ�� ��ʾ "�û������������"	
				System.out.println("�û�������");
				request.setAttribute("msg", "�û������������");
				target = "/jsp/user/login.jsp";
			}
		} catch (Exception e) {
			target = "/WEB-INF/msg.jsp";
			request.setAttribute("msg","��¼ʧ�� ��ص���¼ҳ����µ�¼");
			e.printStackTrace();
		}	
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("checkUsername ��servlet");
		//һ.�������
		String username = request.getParameter("username");		
		System.out.println(username);
		//��.����ҵ���߼�
		LoginServiceImpl service = new LoginServiceImpl();
		try {
			boolean isExist = service.checkUsername(username);
			System.out.println("servlet�е�isExist="+isExist);
			response.getWriter().write("{\"isExist\":"+isExist+"}");
		} catch (Exception e1) {			
			System.out.print("У���û���ʧ��");
			e1.printStackTrace();
		}
		
	}
	
	private void checkEmail(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("checkEmail ��servlet");
		//һ.�������
		String email = request.getParameter("email");		
		System.out.println(email);
		//��.����ҵ���߼�
		LoginServiceImpl service = new LoginServiceImpl();
		try {
			boolean isExist = service.checkEmail(email);
			System.out.println("servlet�е�isExist="+isExist);
			response.getWriter().write("{\"isExist\":"+isExist+"}");
		} catch (Exception e1) {			
			System.out.print("У�������Ƿ����ʧ��");
			e1.printStackTrace();
		}
		
	}
	
	private void checkPhone(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("checkPhone ��servlet");
		//һ.�������
		String phoneNumber = request.getParameter("phoneNumber");		
		System.out.println(phoneNumber);
		//��.����ҵ���߼�
		LoginServiceImpl service = new LoginServiceImpl();
		try {
			boolean isExist = service.checkPhone(phoneNumber);
			System.out.println("servlet�е�isExist="+isExist);
			response.getWriter().write("{\"isExist\":"+isExist+"}");
		} catch (Exception e1) {			
			System.out.print("У���ֻ����Ƿ����ʧ��");
			e1.printStackTrace();
		}		
	}
	
	//������֤
	public void active(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("servlet�е�active");
		//1.��ȡ������
		String target ="";
		String code=request.getParameter("code");
		//2.����ҵ��㹦��
		LoginService service=new LoginServiceImpl();
		boolean flag=service.uerActive(code);
		//3.���м�����Ϣ��ʾ
		if(flag==true){
			//�û�����ɹ�����request������ʾ��Ϣ��ת������¼����
			request.setAttribute("msg","�û�����ɹ������¼��");
			target= "/jsp/user/login.jsp";			
		}else{
			//�û�����ʧ�ܣ���request������ʾ��Ϣ��ת������ʾ����
			request.setAttribute("msg","�û�����ʧ�ܣ������¼��");
			target= "/WEB-INF/msg.jsp";			
		}
		request.getRequestDispatcher(target).forward(request, response);
	}
	
	public void pictureCheck(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�ͻ����������֤��
		String checkCodeClient=request.getParameter("checkcode");
		System.out.println("checkCodeClient:"+checkCodeClient);
		int resultTip=0;
		//��ȡ�����session�е���֤��
		String checkcodeServer=(String)(request.getSession().getAttribute("CHECKCODE"));
		System.out.println("checkcodeServer:"+checkcodeServer);
		//�ȽϿͻ����������е���֤��
		if(checkCodeClient.equalsIgnoreCase(checkcodeServer)){
			resultTip=1;
			System.out.println("�ɹ�");
		}else{			
			resultTip=0;
			System.out.println("ʧ��");
		}
		//��IO���ķ�ʽ������resultTipֵ����ʾͼƬ·��		
			PrintWriter pw;		
			try {
				pw = response.getWriter();
				pw.print(resultTip);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				System.out.println("����ʧ��");
				e.printStackTrace();
			}					
	}
	//�û��˳�
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String target="";
			HttpSession session = request.getSession(true);
			session.invalidate();
			target="/jsp/user/login.jsp";
			//3.ת����ͼ
			request.getRequestDispatcher(target).forward(request, response);
	}
	
}
