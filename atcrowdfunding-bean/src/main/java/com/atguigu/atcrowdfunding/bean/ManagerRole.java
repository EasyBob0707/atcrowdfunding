package com.atguigu.atcrowdfunding.bean;
import java.io.Serializable;

/**
 * 对应t_user_role表 用户角色表
 * @Author Alison
 * @Date 2017年7月7日 下午9:17:29   
 * @Version V1.0
 */
public class ManagerRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer managerid;//用户主键
	private Integer roleid;//角色主键
	
	public ManagerRole() {
		super();
	}
	public ManagerRole(Integer managerid, Integer roleid) {
		super();
		this.managerid = managerid;
		this.roleid = roleid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getManagerid() {
		return managerid;
	}
	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	@Override
	public String toString() {
		return "MemberRole [id=" + id + ", managerid=" + managerid + ", roleid="
				+ roleid + "]";
	}
}