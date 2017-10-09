package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_param 参数表
 * @Author Alison
 * @Date 2017年7月7日 下午8:29:40   
 * @Version V1.0
 */
public class Param implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//名称
	private String code;//代码
	private String val;//值
	
	public Param() {
		super();
	}
	public Param(Integer id, String name, String code, String val) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.val = val;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "Param [id=" + id + ", name=" + name + ", code=" + code
				+ ", val=" + val + "]";
	}
	
	
	
}
