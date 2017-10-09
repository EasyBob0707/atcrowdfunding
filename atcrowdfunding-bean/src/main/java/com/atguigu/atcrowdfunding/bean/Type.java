package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_type 项目分类
 * @Author Alison
 * @Date 2017年7月7日 下午9:14:09   
 * @Version V1.0
 */
public class Type implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//分类名称
	private String remark;//描述
	
	public Type() {
		super();
	}
	public Type(Integer id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
	
	
}
