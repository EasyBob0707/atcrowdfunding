package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_member_address 收货地址表
 * @Author Alison
 * @Date 2017年7月7日 下午8:13:56   
 * @Version V1.0
 */
public class MemberAddress implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer memberid;//会员Id
	private String address;//地址
	
	public MemberAddress() {
		super();
	}
	public MemberAddress(Integer memberid, String address) {
		super();
		this.memberid = memberid;
		this.address = address;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "MemberAddress [memberid=" + memberid + ", address=" + address
				+ "]";
	}
	
	
}
