package com.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.����һ���������ʼ��������Ự���� Session
		Properties props = new Properties();
		//���÷��͵�Э��
		props.setProperty("mail.transport.protocol", "SMTP");
		
		//���÷����ʼ��ķ�����
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");// ָ����֤Ϊtrue

		// ������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//���÷����˵��ʺź�����
				return new PasswordAuthentication("2493707545@qq.com", "iuklcjabipjnebag");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.����һ��Message�����൱�����ʼ�����
		Message message = new MimeMessage(session);

		//���÷�����
		message.setFrom(new InternetAddress("2493707545@qq.com"));

		//���÷��ͷ�ʽ�������
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//�����ʼ�����
		message.setSubject("�û�����");
		// message.setText("����һ�⼤���ʼ�����<a href='#'>���</a>");

		String url="http://localhost:8080/JDCY/LoginServlet?action=active&code="+emailMsg;
		String content="<h1>�����ҵľ��������ʼ�!����������������!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		//String content=emailMsg;
		//�����ʼ�����
		message.setContent(content, "text/html;charset=utf-8");

		// 3.���� Transport���ڽ��ʼ�����
		Transport.send(message);
	}
	
	public static void main(String[] args) throws AddressException, MessagingException {
		MailUtils.sendMail("915628590@qq.com", "abcdefg");
		
	}
}
