package com.vo;

import java.sql.Date;

public class CategoryOrder {
	private Integer oid; //�������
	private Date otime; //�µ�ʱ��
	private Integer total; //�ܼ�
	private Integer ostate; //״̬
	private String address; //�ջ��˵�ַ
	private String name; //�ջ�������
	private Integer telephone; //�ջ��˵绰
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Date getOtime() {
		return otime;
	}
	public void setOtime(Date otime) {
		this.otime = otime;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getOstate() {
		return ostate;
	}
	public void setOstate(Integer ostate) {
		this.ostate = ostate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "CategoryOrder [oid=" + oid + ", otime=" + otime + ", total=" + total + ", ostate=" + ostate
				+ ", address=" + address + ", name=" + name + ", telephone=" + telephone + "]";
	}
	
}
