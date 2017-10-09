package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 流程单(记忆会员申请认证时, 认证的节点)
 * 
 * @Author SUNBO
 * @Date 2017年7月24日 下午7:23:25
 * @Version V1.0
 */
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer memberid;
	private String piid;
	private String status;
	private String authcode;
	private String pstep;// 认证节点

	public Ticket() {
		super();
	}

	public Ticket(Integer id, Integer memberid, String piid, String status,
			String authcode, String pstep) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.piid = piid;
		this.status = status;
		this.authcode = authcode;
		this.pstep = pstep;
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

	public String getPiid() {
		return piid;
	}

	public void setPiid(String piid) {
		this.piid = piid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getPstep() {
		return pstep;
	}

	public void setPstep(String pstep) {
		this.pstep = pstep;
	}

}
