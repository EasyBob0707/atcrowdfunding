package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_order 订单表
 * @Author Alison
 * @Date 2017年7月7日 下午8:24:04   
 * @Version V1.0
 */
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer memberid;//会员ID
	private Integer projectid;//项目ID
	private Integer returnid;//回报ID
	private String ordernum;//订单编号
	private String createdate;//创建时间
	private Integer money;//支持金额
	private Integer rtncount;//回报数量
	private String status;//交易状态
	private String address;//收货地址
	private String invoice;//发票
	private String invoictitle;//发票抬头
	private String remark;//备注
	
	public Order() {
		super();
	}
	public Order(Integer id, Integer memberid, Integer projectid,
			Integer returnid, String ordernum, String createdate,
			Integer money, Integer rtncount, String status, String address,
			String invoice, String invoictitle, String remark) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.projectid = projectid;
		this.returnid = returnid;
		this.ordernum = ordernum;
		this.createdate = createdate;
		this.money = money;
		this.rtncount = rtncount;
		this.status = status;
		this.address = address;
		this.invoice = invoice;
		this.invoictitle = invoictitle;
		this.remark = remark;
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
	public Integer getProjectid() {
		return projectid;
	}
	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}
	public Integer getReturnid() {
		return returnid;
	}
	public void setReturnid(Integer returnid) {
		this.returnid = returnid;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getRtncount() {
		return rtncount;
	}
	public void setRtncount(Integer rtncount) {
		this.rtncount = rtncount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getInvoictitle() {
		return invoictitle;
	}
	public void setInvoictitle(String invoictitle) {
		this.invoictitle = invoictitle;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", memberid=" + memberid + ", projectid="
				+ projectid + ", returnid=" + returnid + ", ordernum="
				+ ordernum + ", createdate=" + createdate + ", money=" + money
				+ ", rtncount=" + rtncount + ", status=" + status
				+ ", address=" + address + ", invoice=" + invoice
				+ ", invoictitle=" + invoictitle + ", remark=" + remark + "]";
	}
	
	
}
