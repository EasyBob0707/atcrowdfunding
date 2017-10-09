package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_advertisement 广告表
 * 
 * @Author Alison
 * @Date 2017年7月7日 下午7:52:07
 * @Version V1.0
 */
public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;// 名称
	private String iconpath;// 图片
	private String status;// 状态
	private String url;// 链接地址
	private String managerId;// 创建人ID

	public Advertisement() {
		super();
	}

	public Advertisement(String id, String name, String iconpath,
			String status, String url, String managerId) {
		super();
		this.id = id;
		this.name = name;
		this.iconpath = iconpath;
		this.status = status;
		this.url = url;
		this.managerId = managerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconpath() {
		return iconpath;
	}

	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "advertisement [id=" + id + ", name=" + name + ", iconpath="
				+ iconpath + ", status=" + status + ", url=" + url
				+ ", managerId=" + managerId + "]";
	}

}
