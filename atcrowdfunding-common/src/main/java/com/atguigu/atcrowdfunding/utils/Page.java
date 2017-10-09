package com.atguigu.atcrowdfunding.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

/**
 * 分页类
 * 
 * @Author SUNBO
 * @Date 2017年7月10日 上午8:43:49
 * @Version V1.0
 */
public class Page<T> {
	
	private List<T> data = new ArrayList<T>(); // 分页数据

	private int totalno; // 总页码
	private int pageno; // 当前页码
	private int totalsize; // 总记录数
	private int pagesize; // 当前页码数据条数
	
	public Page() {
		super();
	}

	public Page(int pageno, int pagesize) {
		if (pageno <= 0) {
			this.pageno = 1;
		} else {
			this.pageno = pageno;
		}
		if (pagesize <= 0) {
			this.pagesize = 10;
		} else {
			this.pagesize = pagesize;
		}
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalno() {
		return totalno;
	}

	/*
	 * private void setTotalno(int totalno) { this.totalno = totalno; }
	 */
	
	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
		this.totalno = ((totalsize % pagesize) > 0) ? ((totalsize / pagesize) + 1)
				: (totalsize / pagesize);
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	// 计算开始索引
	public int getStartIndex() {
		return (pageno - 1) * pagesize; // 0 10 20
	}

}
