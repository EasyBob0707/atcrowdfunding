package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 用户角色
 * 
 * @Author: SUNBO
 * @Date: 2017年7月5日 下午9:49:38
 * @Version: V1.0
 * 
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 角色 id
	 */
	private Integer id;
	/**
	 * 角色名称
	 */
	private String name;

	private Role() {
		super();
	}

	private Role(Integer id, String name) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
