package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;

/**
 * 表t_return 回报表
 * @Author Alison
 * @Date 2017年7月7日 下午8:58:13   
 * @Version V1.0
 */
public class Return implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer projectid;//项目ID
	private String type;//回报类型
	private Integer supportmoney;//支持金额
	private String content;//回报内容
	private Integer count;//回报数量
	private Integer signalpurchase;//单笔限购
	private Integer purchase;//限购数量
	private Integer freight;//运费
	private String invoice;//发票
	private Integer rtndate;//回报时间
	
	public Return() {
		super();
	}
	public Return(Integer id, Integer projectid, String type,
			Integer supportmoney, String content, Integer count,
			Integer signalpurchase, Integer purchase, Integer freight,
			String invoice, Integer rtndate) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.type = type;
		this.supportmoney = supportmoney;
		this.content = content;
		this.count = count;
		this.signalpurchase = signalpurchase;
		this.purchase = purchase;
		this.freight = freight;
		this.invoice = invoice;
		this.rtndate = rtndate;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSupportmoney() {
		return supportmoney;
	}
	public void setSupportmoney(Integer supportmoney) {
		this.supportmoney = supportmoney;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getSignalpurchase() {
		return signalpurchase;
	}
	public void setSignalpurchase(Integer signalpurchase) {
		this.signalpurchase = signalpurchase;
	}
	public Integer getPurchase() {
		return purchase;
	}
	public void setPurchase(Integer purchase) {
		this.purchase = purchase;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Integer getRtndate() {
		return rtndate;
	}
	public void setRtndate(Integer rtndate) {
		this.rtndate = rtndate;
	}
	@Override
	public String toString() {
		return "Return [id=" + id + ", projectid=" + projectid + ", type="
				+ type + ", supportmoney=" + supportmoney + ", content="
				+ content + ", count=" + count + ", signalpurchase="
				+ signalpurchase + ", purchase=" + purchase + ", freight="
				+ freight + ", invoice=" + invoice + ", rtndate=" + rtndate
				+ "]";
	}
	
	
}
