package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_project_type 项目分类关系表
 * @Author Alison
 * @Date 2017年7月7日 下午8:45:21   
 * @Version V1.0
 */
public class ProjectType implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer projectid;//项目主键
	private Integer typeid;//项目分类主键
	
	public ProjectType() {
		super();
	}
	public ProjectType(Integer id, Integer projectid, Integer typeid) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.typeid = typeid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectid() {
		return projectid;
	}
	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	@Override
	public String toString() {
		return "ProjectType [id=" + id + ", projectid=" + projectid
				+ ", typeid=" + typeid + "]";
	}
	
	
}
