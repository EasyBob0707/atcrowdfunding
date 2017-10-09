package com.atguigu.atcrowdfunding.utils;

/**
 * 管理员异常类
 * 
 * @Author SUNBO
 * @Date 2017年7月10日 上午8:41:23
 * @Version V1.0
 */
public class ManagerException extends RuntimeException {

	public static final long serialVersionUID = 1L;

	public ManagerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManagerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ManagerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ManagerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ManagerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
