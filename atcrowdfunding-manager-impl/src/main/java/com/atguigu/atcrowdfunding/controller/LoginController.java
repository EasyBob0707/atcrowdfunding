package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Manager;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.ManagerService;
import com.atguigu.atcrowdfunding.service.MemberService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Const;
import com.atguigu.atcrowdfunding.utils.MD5Util;

/**
 * 用户请求处理
 * 
 * @Author SUNBO
 * @Date 2017年7月5日 下午11:59:53
 * @Version V1.0
 */
@Controller
public class LoginController {

	/**
	 * 这里是交给代理对象进行注入的(只能写接口不能写实现类, 写实现类是注入不进去的)
	 */
	@Autowired
	private ManagerService managerService;

	@Autowired
	private MemberService memberService;

	/**
	 * 验证已经进入
	 */
	public LoginController() {
		super();
		System.out.println("==========LoginController==========");
	}

	/**
	 * 首页信息展示页面(以后扩展)
	 * 
	 * @return 跳转到首页
	 */
	@RequestMapping("/toDefault")
	public String toDefault() {
		/* 需要从数据库查询出首页要显示的相关信息 */
		return "index";
	}

	/**
	 * 管理员/会员注册 member:会员 manager:管理
	 * 
	 * @param user
	 *            注册提交的用户信息
	 * @return 服务器转发的页面
	 */
	@RequestMapping("/toReg")
	public String register(Map<String, Object> role) {
		/* 从数据库中查询角色信息进行角色回显(入参传入map相当于将回显数据存放到request域中) */
		// role.put("roles", managerService.getRoles());
		/* 跳转到注册页面 */
		return "reg";
	}

	/**
	 * 管理员登陆成功后跳转到后台页面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public String main() {
		return "manager/main";
	}

	/**
	 * 会员登陆成功后跳转到后台页面
	 * 
	 * @return
	 */
	@RequestMapping("/member")
	public String member() {
		return "member/member";
	}

	/**
	 * 真正的进行注册用户操作
	 * 
	 * @param username
	 *            用户名称
	 * @param loginacct
	 *            用户账号
	 * @param userpswd
	 *            用户密码
	 * @param email
	 *            用户邮箱
	 * @param usertype
	 *            用户类型
	 * @param map
	 *            默认将用户信息存放到request域中 用于转发操作
	 * @param session
	 *            将用户信息存放到session域中 用于重定向操作
	 * @return 返回Json数据(封装到AjaxResult中)
	 */
	@ResponseBody
	@RequestMapping("/doReg")
	public Object doReg(String username, String loginacct, String userpswd,
			String email, String usertype, Map<String, Object> map,
			HttpSession session) {

		// 存放返回的JSON数据
		AjaxResult result = new AjaxResult();

		// 将前台的数据封装到Map集合当中
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("username", username);
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", MD5Util.digest(userpswd));
		paramMap.put("email", email);

		// 获取是否有相同的账号进行注册
		Manager queryLoginacct = managerService.queryLoginacct(paramMap);
		if (queryLoginacct == null) {

			// 暂时注册信息不存放到session域中, 只返回成功与失败的标识
			if (Const.MANAGER.equals(usertype)) {

				int flag = managerService.addManagerForRegister(paramMap);

				if (flag == 0) {
					result.setErrorMessage("注册失败!");
					result.setSuccess(false);
				}

				if (flag == 1) {
					// 将账号和密码封装到manager对象当中
					Manager manager = new Manager();
					manager.setLoginacct(loginacct);
					manager.setUserpswd(userpswd);
					manager.setUsername(username);
					// 将注册的管理员对象存放到session域中
					session.setAttribute(Const.LOGIN_MANAGER, manager);

					// Manager attribute = (Manager)
					// session.getAttribute(Const.LOGIN_MANAGER);
					// System.out.println(attribute.getUsername());
					// session.setAttribute(Const.Register_MANAGER,
					// registeruser);
					result.setSuccess(true);
				}

			}

			if (Const.MEMBER.equals(usertype)) {

				int flag = memberService.addMemberForRegister(paramMap);

				if (flag == 0) {
					result.setErrorMessage("注册失败!");
					result.setSuccess(false);
				}

				if (flag == 1) {
					// 将账号和密码封装到member对象当中
					Member member = new Member();
					member.setUsername(username);
					// 将注册的会员对象存放到session域中
					session.setAttribute(Const.LOGIN_MEMBER, member);
					result.setSuccess(true);
				}
			}

		} else {

			result.setErrorMessage("账号已存在!");
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 管理员/会员登录前要回写显相应的数据(角色暂时写死)
	 * 
	 * @return 跳转到用户注册页面
	 */
	/*@RequestMapping("/toLogin")
	public String toLogin(Map<String, Object> role) {
		// 后台页面暂时是写死的不用进行回显
		// role.put("roles", managerService.getRoles());
		return "login";
	}*/
	
	/**
	 * 系统自动登录
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String login(HttpServletRequest request, HttpSession session) {
		
		//判断是否需要自动登录
		//如果之前登录过，cookie中存放了用户信息，需要获取cookie中的信息，并进行数据库的验证
		boolean needLogin = true;
		String logintype = null ;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){ //如果客户端禁用了Cookie，那么无法获取Cookie信息
			
			for (Cookie cookie : cookies) {
				if("logincode".equals(cookie.getName())){
					String logincode = cookie.getValue();
					//loginacct=admin&userpwd=21232f297a57a5a743894a0e4a801fc3&logintype=member
					System.out.println("获取到Cookie中的键值对"+cookie.getName()+"===== " + logincode);
					String[] split = logincode.split("&");
					if(split.length == 3){
						String loginacct = split[0].split("=")[1];
						String userpswd = split[1].split("=")[1];
						logintype = split[2].split("=")[1];
						
						if("user".equals(logintype)){
							/*User user = new User();
							user.setLoginacct(loginacct);
							user.setUserpwd(userpwd);
							
							User dbLogin = userService.queryUser4Login(user);
							
							if(dbLogin!=null){
								session.setAttribute(Const.LOGIN_USER, dbLogin);
								needLogin = false ;
							}*/
						}else if("member".equals(logintype)){
							Map<String,Object> paramMap = new HashMap<String,Object>();
							paramMap.put("loginacct", loginacct);
							paramMap.put("userpswd", userpswd);
							Member dbLogin = memberService.queryMemberForLogin(paramMap);
							
							if(dbLogin != null){
								session.setAttribute(Const.LOGIN_MEMBER, dbLogin);
								needLogin = false ;
							}
						}
						
					}
				}
			}
		}
		
		if(needLogin){
			return "login";
		}else{
			if("user".equals(logintype)){
				return "redirect:/main.htm";
			}else if("member".equals(logintype)){
				return "redirect:/member.htm";
			}
		}

		return "login";
	}

	/**
	 * 权限不足, 跳转到错误页面
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/error")
	public String toErrorAuth(Map<String, Object> role) {
		return "error/error_auth";
	}

	/**
	 * 处理异步请求登录(管理员与会员共用登录/注册)
	 * 
	 * @ResponseBody 表示采用框架底层的HttpMessageConverter进行内容类型转换
	 *               如果引用了Jackson组件,返回实体对象或集合,框架就会将对象或集合转换为 JSON格式字符串,
	 *               然后以流的形式将字符串响应给客户端.
	 * 
	 * @param loginacct
	 *            登录账号
	 * @param userpswd
	 *            登录密码
	 * @param usertype
	 *            用户角色
	 * @param flag
	 *            记住我(标识)
	 * @param map
	 *            只能将数据存储到request域中
	 * @param response
	 *            获取cookie数据
	 * @param session
	 *            将登录信息存放到session域中
	 * @return 返回AjaxResult封装的json数据
	 */
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object doLogin(String loginacct, String userpswd, String usertype,
			String flag, Map<String, Object> map, HttpServletResponse response,
			HttpSession session) {

		AjaxResult result = new AjaxResult();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", MD5Util.digest(userpswd));

		// 管理员登录入口
		if (Const.MANAGER.equals(usertype)) {
			Manager loginManager = managerService
					.queryManagerForLogin(paramMap);
			if (loginManager == null) {
				result.setErrorMessage("用户名称或密码不正确,登录失败!");
				result.setSuccess(false);
			} else {

				// 根据用户所分配角色,以及角色所分配许可,来查找该用户所拥有的许可权限集合
				List<Permission> permissionList = managerService
						.queryPermissionByUserid(loginManager.getId());

				// 此处将用户登录的路径进行存储
				Set<String> pathPermission = new HashSet<String>();

				Map<Integer, Permission> permissionMap = 
						new HashMap<Integer, Permission>();
				
				for (Permission permission : permissionList) {
					permissionMap.put(permission.getId(), permission);
					// 将权限URL存放到Set集合当中
					pathPermission.add("/" + permission.getUrl());
				}

				Permission root = null;

				for (Permission permission : permissionList) {
					// 子节点
					Permission child = permission; // 假设为子菜单
					if (child.getPid() == null) {
						root = permission;
					} else {
						// 父节点
						Permission parent = permissionMap.get(child.getPid());
						parent.getChildren().add(child);
					}
				}

				session.setAttribute(Const.ROOT_PERMISSION, root);
				session.setAttribute(Const.LOGIN_MANAGER_PERMISSION_URL,
						pathPermission);
				session.setAttribute(Const.LOGIN_MANAGER, loginManager);
				result.setSuccess(true);
			}
		}

		// 会员登录入口
		if (Const.MEMBER.equals(usertype)) {
			Member loginMember = memberService.queryMemberForLogin(paramMap);
			if (loginMember == null) {
				result.setErrorMessage("用户名称或密码不正确,登录失败!");
				result.setSuccess(false);
			} else {

				if ("1".equals(flag)) { // 记住我
					String logincode = "\"loginacct="
							+ loginMember.getLoginacct() + "&userpwd="
							+ loginMember.getUserpswd() + "&logintype=member\"";
					// loginacct=admin&userpwd=21232f297a57a5a743894a0e4a801fc3&logintype=member
					System.out
							.println("用户-存放到Cookie中的键值对：logincode::::::::::::"
									+ logincode);
					Cookie c = new Cookie("logincode", logincode);

					c.setMaxAge(60 * 60 * 12); // 12小时Cookie过期 单位秒
					c.setPath("/"); // 表示任何请求路径都可以访问Cookie

					response.addCookie(c);
				}

				session.setAttribute(Const.LOGIN_MEMBER, loginMember);
				result.setSuccess(true);
			}
		}

		// 返回Json数据
		return result;
	}

	/**
	 * 管理员注销的操作
	 * 
	 * @param session
	 *            从session域中删除管理员对象的信息
	 * @return 注销之后跳转到首页
	 */
	@RequestMapping("/managerLogout")
	public String managerLogout(HttpSession session) {
		// 从session中删除管理员的用户信息
		session.removeAttribute(Const.LOGIN_MANAGER);
		// 跳转到根目录下之后默认访问index.jsp, 由于index.jsp进行转发到真正的index.jsp
		return "redirect:/";
	}

	/**
	 * 会员注销的操作
	 * 
	 * @param session
	 *            从session域中删除管理员对象的信息
	 * @return 注销之后跳转到首页
	 */
	@RequestMapping("/memberLogout")
	public String memberLogout(HttpSession session) {
		// 从session中删除管理员的用户信息
		session.removeAttribute(Const.LOGIN_MEMBER);
		// 跳转到根目录下之后默认访问index.jsp, 由于index.jsp进行转发到真正的index.jsp
		return "redirect:/";
	}

	/**
	 * 判断注册的账户是否存在
	 * 
	 * @param loginacct
	 *            提供的账户名称
	 * @return 返回数据
	 */
	@ResponseBody
	@RequestMapping("/existLoginacct")
	public Object existLoginAcct(String loginacct) {
		// 返回查询数据的标志值
		AjaxResult result = new AjaxResult();
		// 用map向业务层传递数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		// 调用业务层接口
		Manager manager = managerService.queryLoginacct(paramMap);
		// 判断是否存在
		if (manager != null) {
			// 查出的数据不为空(在数据库中存在该数据)
			result.setSuccess(false);
			result.setErrorMessage("该账号已存在!");
			return result;
		}
		// 如果返回的数据为空(在数据库中不存在该数据), 可以添加
		result.setSuccess(true);
		return result;
	}
}
