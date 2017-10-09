package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_message 消息表
 * @Author Alison
 * @Date 2017年7月7日 下午8:21:38   
 * @Version V1.0
 */
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer memberid;//接收消息会员ID
	private String content;//内容
	private String senddate;//时间
	
	public Message() {
		super();
	}
	public Message(Integer id, Integer memberid, String content, String senddate) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.content = content;
		this.senddate = senddate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSenddate() {
		return senddate;
	}
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", memberid=" + memberid + ", content="
				+ content + ", senddate=" + senddate + "]";
	}
	
	
}
