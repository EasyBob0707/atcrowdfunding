package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_project_tag 项目标签关系表
 * @Author Alison
 * @Date 2017年7月7日 下午8:52:12   
 * @Version V1.0
 */
public class ProjectTag implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer projectid;//项目主键
	private Integer tagid;//标签主键
	
	public ProjectTag() {
		super();
	}
	public ProjectTag(Integer id, Integer projectid, Integer tagid) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.tagid = tagid;
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
	public Integer getTagid() {
		return tagid;
	}
	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}
	@Override
	public String toString() {
		return "ProjectTag [id=" + id + ", projectid=" + projectid + ", tagid="
				+ tagid + "]";
	}
	
	
	
	
}
