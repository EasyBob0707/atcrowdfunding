package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_dictionary 数据字典
 * @Author Alison
 * @Date 2017年7月7日 下午8:03:27   
 * @Version V1.0
 */
public class Dictionary implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//名称
	private String code;//代码
	private String subcode;//分类代码
	private String val;//值
	
	public Dictionary() {
		super();
	}
	public Dictionary(Integer id, String name, String code, String subcode,
			String val) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.subcode = subcode;
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
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", name=" + name + ", code=" + code
				+ ", subcode=" + subcode + ", val=" + val + "]";
	}
	
	
}
