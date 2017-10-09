package com.atguigu.atcrowdfunding.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.utils.Const;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	// 该监听器已经在配置文件中配置了, PermissionService就可以进行自动注入了
	// 类比于Handler中加入@Controller就相当于中配置文件中声明了bean(只是两种不同的开发方式: 注解/配置文件)
	@Autowired
	private PermissionService permissionService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 首先获取请求的路径
		String servletPath = request.getServletPath();

		// 首先判断用户请求的路径是否在数据库中所有的所有请求路径中
		// 判断用户的请求路径是否存在于数据库的路径, 需要从数据库中读取所有的路径信息
		// 需要调用PermissionService业务层获取数据库中的路径信息
		// 获取数据库中允许的请求
		/*
		 * List<Permission> queryAllPermission = permissionService
		 * .queryAllPermission(); // 使用Set的目的就是避免重复的数据 Set<String> allUris = new
		 * HashSet<String>();
		 * 
		 * for (Permission permission : queryAllPermission) { //
		 * 由于从数据库中查询出来路径是没有"/", 在比对的时候有"/" allUris.add("/" +
		 * permission.getUrl()); }
		 */

		// 为了降低频繁访问数据库, 在服务器启动时就将系统中的访问路径全部存到Application域当中
		// 每次需要的时候从application域中取
		Set<String> allUris = (Set<String>) request.getSession()
				.getServletContext()
				.getAttribute(Const.SYSTEM_ALL_PERMISSION_URL);

		// 判断当前用户请求的路径是否存在数据库中所有请求路径之中
		if (allUris.contains(servletPath)) {
			// 存在于数据库所有的请求路径中, 判断是否存在于当前用户的权限中的路径
			// 这里就用户登录时, 将用户的权限显得路径存到session域当中
			HttpSession session = request.getSession();

			Set<String> pathPermission = (Set<String>) session
					.getAttribute(Const.LOGIN_MANAGER_PERMISSION_URL);

			if (pathPermission.contains(servletPath)) {
				return true;
			} else {
				response.sendRedirect(request.getContextPath() + "/error.htm");
				return false;
			}
		} else {
			// 其他路径不进行拦截(放行)
			return true;
		}
	}
}
