package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_role_permission 角色许可表
 * @Author Alison
 * @Date 2017年7月7日 下午9:06:36   
 * @Version V1.0
 */
public class RolePermission implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer roleid;//角色主键
	private Integer permissionid;//许可主键
	public RolePermission() {
		super();
	}
	public RolePermission(Integer id, Integer roleid, Integer permissionid) {
		super();
		this.id = id;
		this.roleid = roleid;
		this.permissionid = permissionid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}
	@Override
	public String toString() {
		return "RolePermission [id=" + id + ", roleid=" + roleid
				+ ", permissionid=" + permissionid + "]";
	}
	
	
}
