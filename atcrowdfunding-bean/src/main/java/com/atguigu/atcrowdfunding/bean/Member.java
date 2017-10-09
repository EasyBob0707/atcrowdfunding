package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_member 会员表
 * @Author Alison
 * @Date 2017年7月7日 下午8:06:50   
 * @Version V1.0
 */
public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String loginacct;//登录账号
	private String userpswd;//登录密码
	private String username;//用户名称
	private String email;//电子邮箱
	private String authstatus;//实名认证状态
	private String usertype;//用户类型
	private String realname;//真实名称
	private String cardnum;//身份证号码
	private String accttype;//账户类型
	private String tel;//手机号码
	
	public Member() {
		super();
	}
	public Member(Integer id, String loginacct, String userpswd,
			String username, String email, String authstatus, String usertype,
			String realname, String cardnum, String accttype, String tel) {
		super();
		this.id = id;
		this.loginacct = loginacct;
		this.userpswd = userpswd;
		this.username = username;
		this.email = email;
		this.authstatus = authstatus;
		this.usertype = usertype;
		this.realname = realname;
		this.cardnum = cardnum;
		this.accttype = accttype;
		this.setTel(tel);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginacct() {
		return loginacct;
	}
	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}
	public String getUserpswd() {
		return userpswd;
	}
	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthstatus() {
		return authstatus;
	}
	public void setAuthstatus(String authstatus) {
		this.authstatus = authstatus;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getCardnum() {
		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public String getAccttype() {
		return accttype;
	}
	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", loginacct=" + loginacct + ", userpswd="
				+ userpswd + ", username=" + username + ", email=" + email
				+ ", authstatus=" + authstatus + ", usertype=" + usertype
				+ ", realname=" + realname + ", cardnum=" + cardnum
				+ ", accttype=" + accttype + ", tel=" + tel +"]";
	}
	
}
