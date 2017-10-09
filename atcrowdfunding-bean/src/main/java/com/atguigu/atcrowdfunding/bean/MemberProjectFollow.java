package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_member_project_follow 项目关注表
 * @Author Alison
 * @Date 2017年7月7日 下午8:19:26   
 * @Version V1.0
 */
public class MemberProjectFollow implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer projectid;//项目主键
	private Integer memberid;//会员主键
	
	public MemberProjectFollow() {
		super();
	}
	public MemberProjectFollow(Integer id, Integer projectid, Integer memberid) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.memberid = memberid;
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
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	@Override
	public String toString() {
		return "MemberProjectFollow [id=" + id + ", projectid=" + projectid
				+ ", memberid=" + memberid + "]";
	}
	
	
}
