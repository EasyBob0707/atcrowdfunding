package com.atguigu.atcrowdfunding.utils;

import java.util.List;

/**
 * JSON数据返回
 * 
 * @Author SUNBO
 * @Date 2017年7月10日 上午8:45:34
 * @Version V1.0
 */
public class AjaxResult {
	
	/**
	 * 返回AJAX的标识   true/false
	 */
	private boolean success;
	/**
	 * 如果返回数据出现错误时, 将携带错误信息返回
	 */
	private String errorMessage;
	/**
	 * 返回分页数据
	 */
	private Page page;
	/**
	 * 将数据封装到集合当中进行返回(lby添加)
	 */
	private List list;
	/**
	 * 主要用于返回节点信息
	 */
	private Object data;
	

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
