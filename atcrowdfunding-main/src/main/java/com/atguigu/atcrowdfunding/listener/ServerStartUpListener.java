package com.atguigu.atcrowdfunding.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.utils.Const;

/**
 * 在服务器启动过程中将request.getContextPath()路径存放到application域中.
 * @author SUNBO
 * 
 */
public class ServerStartUpListener implements ServletContextListener {

	@Autowired
	private PermissionService permissionService ;
	
	@Override
	public void contextDestroyed( ServletContextEvent servletContextEvent ) {
		//将request.getContextPath()路径从application域删除
	}

	@Override
	public void contextInitialized( ServletContextEvent servletContextEvent ) {
		//第一:
		//将request.getContextPath()路径存放到application域中.
		ServletContext application = servletContextEvent.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		
		//第二:
		//获取系统的所有许可uris,存放到application域中
		
		//获取ioc容器
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application);
		
		//获取permissionService
		PermissionService permissionService = ioc.getBean(PermissionService.class);
		
		//获取系统的所有许可uris,存放到application域中
		Set<String> alluris = new HashSet<String>();
		List<Permission> queryAllPermission = permissionService.queryAllPermission();
		for (Permission permission : queryAllPermission) {
			alluris.add("/"+permission.getUrl());
		}
		application.setAttribute(Const.SYSTEM_ALL_PERMISSION_URL, alluris);  
		
		//第三:
		//application域可以存放广告信息
		
		//第四:
		//application域存放项目分类信息
		
		//第五:
		//application域存放数据字典
		
		//....
	}

}
