package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表t_permission 许可（权限）表
 * 
 * @Author Alison
 * @Date 2017年7月7日 下午8:32:18
 * @Version V1.0
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;// 父ID
	private String name;// 名称
	private String icon;// 图标
	private String url;// 链接地址
	// 表示子节点
	private List<Permission> children = new ArrayList<Permission>();
	// 菜单栏是否是打开的状态(ztree默认是关闭的状态), 这里默认是展开的状态
	private boolean open = true;
	private boolean checked = false;

	public Permission() {
		super();
	}

	public Permission(String name) {
		super();
		this.name = name;
	}

	public Permission(Integer id, Integer pid, String name, String icon,
			String url) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.icon = icon;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", pid=" + pid + ", name=" + name
				+ ", icon=" + icon + ", url=" + url + ", children=" + children
				+ ", open=" + open + ", checked=" + checked + "]";
	}

}
