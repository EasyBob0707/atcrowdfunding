package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_cert 资质表
 * @Author Alison
 * @Date 2017年7月7日 下午8:01:35   
 * @Version V1.0
 */
public class Cert implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//资质名称
	
	public Cert() {
		super();
	}
	public Cert(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "Cert [id=" + id + ", name=" + name + "]";
	}
	
}
