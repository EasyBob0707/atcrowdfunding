package com.atguigu.atcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.bean.Manager;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.utils.Const;

/**
 * 访问者可以访问公共的页面(如:登录页面/注册页面等) 该项目只是通过粗粒度(指通过模块整体做权限控制) 而细粒度的对模块中的功能进行细化控制(如:
 * 增删改查)做详细的控制 适配器设计模式的优点: 这里继承HandlerInterceptorAdapter只需要实现用到的方法
 * 而不必去理会用不到的方法(同样不用去实现)
 * 
 * @Author SUNBO
 * @Date 2017年7月18日 下午3:18:44
 * @Version V1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter { // implements
																	// HandlerInterceptor
																	// {

	
	// 该方法是在目标方法调用之前进行调用
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 声明一个白名单(白名单中存放所有无权限可以访问的请求)
		// 白名单中的路径可以允许所有的用户进行访问(一般登录之前允许访问的页面放到白名单当中, 登录之后的页面不允许放在白名单当中)
		Set<String> allowable = new HashSet<String>();
		// 跳转到默认主页面
		allowable.add("/toDefault.htm");
		// 用户登录成功页面
		allowable.add("/doLogin.do");
		// 用户登录页面
		allowable.add("/toLogin.htm");
		// 用户注册页面
		allowable.add("/toReg.htm");
		// 注册管理员/会员
		allowable.add("/doReg.do");
		// 会员登录
		allowable.add("/member.htm");
		// 检查是否有相同的注册账号存在
		allowable.add("/existLoginacct.do");

		/*
		 * StringBuffer requestURL = request.getRequestURL();
		 * 
		 * String requestURI = request.getRequestURI();
		 */

		String contextPath = request.getContextPath();

		String servletPath = request.getServletPath();

		// System.out.println("requestURI="+requestURI); //
		// /atcrowdfunding-main/jquery/jquery-2.1.1.min.js
		System.out.println("servletPath=" + servletPath);// /jquery/jquery-2.1.1.min.js
		// System.out.println("requestURL="+requestURL); //
		// http://localhost/atcrowdfunding-main/jquery/jquery-2.1.1.min.js
		// System.out.println("contextPath="+contextPath); //
		// /atcrowdfunding-main

		// 判端请求路径是否存在于白名单当中(若存在则放行)
		if (allowable.contains(servletPath)) {
			return true;
		} else {
			// 如果不存在于白名单当中, 判断该用户是否登录过该系统
			// true : 表示一定获取session对象.
			// 如果之前服务器给客户端分配过session,则获取这个session,否则,创建一个新的.
			// false : 表示获取之前分配过的session
			// 如果之前服务器给客户端分配过session,则获取这个session,否则,返回null.
			HttpSession session = request.getSession(true);
			// 判断该管理员是否登录过, 从session域中查看是否有该管理员存在即可
			Manager manager = (Manager) session.getAttribute(Const.LOGIN_MANAGER);
			// 判断该会员是否登录过, 从session域中查看是否有该会员存在即可(只要有会员登录就放行)
			Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			//问题: 登录了不一定所有的模块都可以进行访问(递交给访问控制拦截器)
			if (manager == null && member == null) {
				response.sendRedirect(contextPath + "/toLogin.htm");
				return false;
			}
			return true;
		}
	}

	// 该方法是在目标方法之后, 视图渲染之前进行调用
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);

	}

	// 该方法是在视图渲染之后, 视图响应之前进行调用
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);

	}

}
