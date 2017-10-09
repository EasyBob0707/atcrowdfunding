package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 管理员的实体类
 * 对应t_user表
 * @Author SUNBO
 * @Date 2017年7月7日 下午5:56:32
 * @Version V1.0
 */
public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 登录账户名称
	 */
	private String loginacct;
	/**
	 * 登录密码
	 */
	private String userpswd;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 账号创建时间
	 */
	private String createtime;
	/**
	 * 权限id
	 */
	private Integer roleid;

	public Manager() {
		super();
	}

	public Manager(String id, String loginacct, String userpswd,
			String username, String email, String createtime) {
		super();
		this.id = id;
		this.loginacct = loginacct;
		this.userpswd = userpswd;
		this.username = username;
		this.email = email;
		this.createtime = createtime;
	}

	public Manager(String id, String loginacct, String userpswd,
			String username, String email, String createtime, Integer roleid) {
		super();
		this.id = id;
		this.loginacct = loginacct;
		this.userpswd = userpswd;
		this.username = username;
		this.email = email;
		this.createtime = createtime;
		this.roleid = roleid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginacct=" + loginacct + ", userpswd="
				+ userpswd + ", username=" + username + ", email=" + email
				+ ", createtime=" + createtime + "]";
	}

}
