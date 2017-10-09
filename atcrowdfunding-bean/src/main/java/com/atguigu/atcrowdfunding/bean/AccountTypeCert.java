package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;
/**
 * 表t_account_type_cert 账户资质关系表
 * @Author Alison
 * @Date 2017年7月7日 下午7:51:21   
 * @Version V1.0
 */
public class AccountTypeCert implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String accttype;//账户类型
	private Integer certid;//资质主键
	
	public AccountTypeCert() {
		super();
	}
	
	public AccountTypeCert(Integer id, String accttype, Integer certid) {
		super();
		this.id = id;
		this.accttype = accttype;
		this.certid = certid;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccttype() {
		return accttype;
	}
	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
	public Integer getCertid() {
		return certid;
	}
	public void setCertid(Integer certid) {
		this.certid = certid;
	}
	@Override
	public String toString() {
		return "AccountTypeCert [id=" + id + ", accttype=" + accttype
				+ ", certid=" + certid + "]";
	}
	
	
}
