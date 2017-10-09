package com.atguigu.atcrowdfunding.utils;

/**
 * 常量类
 * 
 * @Author SUNBO
 * @Date 2017年7月10日 上午8:46:07
 * @Version V1.0
 */
public class Const {

	//管理员登录(用于Session中的key)
	public final static String LOGIN_MANAGER = "loginManager";
	//会员登录(用于Session中的key)
	public final static String LOGIN_MEMBER = "loginMember";
	
	public final static String MANAGER = "manager";
	
	public final static String MEMBER = "member";
	//初始密码
	public final static String DEFAULT_PASSWORD = "123456";
	//超级管理员
	public static final String ROOT_MANAGER = "admin";
	//存放树数据的回显(修改叶子节点使用)
	public static final String TREE_PERMISSION = "permission";
	//存放权限许可
	public static final String ROLE = "role";
	//登录后获取菜单展示
	public static final String ROOT_PERMISSION = "rootPermission";
	//存放登录用户的路径
	public static final String LOGIN_MANAGER_PERMISSION_URL = "pathPermission";
	//存放系统中可访问的所有路径
	public static final String SYSTEM_ALL_PERMISSION_URL = "alluris";

}
