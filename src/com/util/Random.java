package com.util;

public class Random {
	public static String getCode(){
		// ��0-9����һ���ַ�����
		StringBuffer array = new StringBuffer();
		for (int i = 0; i <= 9; i++) {
			array.append(i);
		}					
			int length = array.length();

		// �洢������ɵ��ַ���
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i <6; i++) {
			// ������λ�õ��ַ�
			char c = array.charAt((int) (Math.random() * length));
			str.append(c);
		}

		return str.toString();
	}
	public static void main(String[] args) {
		System.out.println(getCode());
	}
			
	
}
