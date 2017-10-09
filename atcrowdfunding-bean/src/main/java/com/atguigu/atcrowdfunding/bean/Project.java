package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_project 项目表
 * @Author Alison
 * @Date 2017年7月7日 下午8:34:54   
 * @Version V1.0
 */
public class Project implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//项目名称
	private String remark;//项目简介
	private Integer money;//筹资金额
	private Integer day;//筹资天数
	private String status;//状态
	private String deploydate;//发布日期
	private Integer supportmoney;//支持金额
	private Integer supporter;//支持者数量
	private Integer completion;//完成度
	private Integer memberid;//发起人ID
	private String createdate;//创建日期
	private Integer follower;//关注者数量
	
	public Project() {
		super();
	}
	public Project(Integer id, String name, String remark, Integer money,
			Integer day, String status, String deploydate, Integer supportmoney,
			Integer supporter, Integer completion, Integer memberid,
			String createdate, Integer follower) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.money = money;
		this.day = day;
		this.status = status;
		this.deploydate = deploydate;
		this.supportmoney = supportmoney;
		this.supporter = supporter;
		this.completion = completion;
		this.memberid = memberid;
		this.createdate = createdate;
		this.follower = follower;
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
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeploydate() {
		return deploydate;
	}
	public void setDeploydate(String deploydate) {
		this.deploydate = deploydate;
	}
	public Integer getSupportmoney() {
		return supportmoney;
	}
	public void setSupportmoney(Integer supportmoney) {
		this.supportmoney = supportmoney;
	}
	public Integer getSupporter() {
		return supporter;
	}
	public void setSupporter(Integer supporter) {
		this.supporter = supporter;
	}
	public Integer getCompletion() {
		return completion;
	}
	public void setCompletion(Integer completion) {
		this.completion = completion;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public Integer getFollower() {
		return follower;
	}
	public void setFollower(Integer follower) {
		this.follower = follower;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", remark=" + remark
				+ ", money=" + money + ", day=" + day + ", status=" + status
				+ ", deploydate=" + deploydate + ", supportmoney="
				+ supportmoney + ", supporter=" + supporter + ", completion="
				+ completion + ", memberid=" + memberid + ", createdate="
				+ createdate + ", follower=" + follower + "]";
	}
	
}
