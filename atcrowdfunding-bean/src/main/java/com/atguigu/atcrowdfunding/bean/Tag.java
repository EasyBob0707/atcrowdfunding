package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_tag 项目标签
 * @Author Alison
 * @Date 2017年7月7日 下午9:10:35   
 * @Version V1.0
 */
public class Tag implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;//父ID
	private String name;//标签名称
	public Tag() {
		super();
	}
	public Tag(Integer id, Integer pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
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
	@Override
	public String toString() {
		return "Tag [id=" + id + ", pid=" + pid + ", name=" + name + "]";
	}
	
	
}
