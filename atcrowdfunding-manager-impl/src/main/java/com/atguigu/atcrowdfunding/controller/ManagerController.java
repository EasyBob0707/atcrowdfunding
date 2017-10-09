package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Manager;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.service.ManagerService;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Const;
import com.atguigu.atcrowdfunding.utils.Datas;
import com.atguigu.atcrowdfunding.utils.Page;
import com.atguigu.atcrowdfunding.utils.StringUtil;

/**
 * Manager请求处理的Handle接口
 * 
 * @Author SUNBO
 * @Date 2017年7月9日 上午11:16:46
 * @Version V1.0
 */
@RequestMapping("/manager")
@Controller
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping(value = "/unassign", method = RequestMethod.POST)
	public Object unassign(Datas datas, Integer managerid) {
		AjaxResult result = new AjaxResult();

		try {

			int count = managerService.deleteManagerRoleRelationship(managerid,
					datas.getIds());

			result.setSuccess(count == datas.getIds().size());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 分配权限
	 * 
	 * @param datas
	 * @param managerid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public Object assign(Datas datas, Integer managerid) {
		AjaxResult result = new AjaxResult();

		try {

			int count = managerService.saveManagerRoleRelationship(managerid,
					datas.getIds());

			result.setSuccess(count == datas.getIds().size());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 取消权限
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/toAssignRole")
	public String toAssignRole(Integer id, Map<String, Object> map) {
		Manager manager = managerService.getManagerById(id);
		map.put("manager", manager);

		// 获取所有角色对象
		List<Role> allRoleList = roleService.queryAllRole();

		// 未分配角色
		List<Role> unassignList = new ArrayList<Role>();

		// 已分配角色
		List<Role> assignList = new ArrayList<Role>();

		// 查询当前用户已经分配的角色id
		List<Integer> ids = managerService.queryRoleidsByManagerid(id); // 根据用户id查询角色id,到中间表查询

		// 判断角色是否已经被分配过;
		for (Role role : allRoleList) {
			if (ids.contains(role.getId())) {
				assignList.add(role);
			} else {
				unassignList.add(role);
			}
		}

		map.put("assignList", assignList);
		map.put("unassignList", unassignList);

		return "manager/assignRole";
	}

	/**
	 * 用于管理员后台操作(从main--->index(指的是manager的index, 就是原型当中的user))
	 * 
	 * @return 跳转到manger的首页面
	 * @RequestMapping("/index")对于非Restful风格支持两种请求post/get 如果指定了那么就只接受指定的请求方式
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String toManagerIndex() {
		return "manager/index";
	}

	/**
	 * 分页查询数据
	 * 
	 * @param pageno
	 *            页面索引
	 * @param pagesize
	 *            每页显示的记录条数
	 * @param queryText
	 *            管理员信息检索条件信息
	 * @param map
	 *            传递数据
	 * @return 返回JSON数据
	 */
	@ResponseBody
	@RequestMapping(value = "/doIndex", method = RequestMethod.POST)
	public Object doindex(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "2") Integer pagesize,
			String queryText, Map<String, Object> map) {

		AjaxResult result = new AjaxResult();

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>(); // VO,DTO
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);

			// 判断检索的信息条件是否为空(检索条件当中是否存在有%等特殊的符号)
			if (StringUtil.isNotEmpty(queryText) && queryText.contains("%")) {
				// 如果存在特殊的符号, 对特殊的符号进行处理
				// "\\%" -> "\%" -> concat('%','\%','%') =>'%\%%'
				// String中的数据是不可变的, 对其操作之后需要重新接受
				queryText = queryText.replaceAll("%", "\\\\%");
			}
			// 将处理完成的检索条件封装到Map当中
			paramMap.put("queryText", queryText);

			Page<Manager> page = managerService.queryPage(paramMap);

			// 返回分页的信息
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("数据加载数据失败!");
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "manager/add";
	}

	// 保存用户
	@ResponseBody
	@RequestMapping(value = "/doAdd")
	public Object doAdd(Manager manager) {
		AjaxResult result = new AjaxResult();

		try {
			int count = managerService.saveManager(manager);
			// 由于数据保存成功就返回1, 如果count==1则返回true, 页面再次返回是否进行跳转
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("账号已经存在!");
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 修改前要获取管理员信息(这里是用同步的方式进行操作的)
	 * 
	 * @param id
	 *            根据ID查询管理员信息
	 * @param map
	 *            将查到的数据保存到map中
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Map<String, Object> map) {
		Manager manager = managerService.getManagerById(id);
		map.put(Const.MANAGER, manager);
		return "manager/edit";
	}

	/**
	 * 修改对应的管理员信息
	 * 
	 * @param manager
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doEdit", method = RequestMethod.POST)
	public Object doEdit(Manager manager) {
		AjaxResult result = new AjaxResult();

		try {
			int count = managerService.updateManager(manager);

			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("该账号已经存在!");
		}

		return result;
	}

	/**
	 * 删除管理员信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		System.out.println(id);
		try {
			int count = managerService.deleteManager(id);

			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 批量进行删除
	 * 
	 * @param id
	 *            要删除的ID
	 */
	@ResponseBody
	@RequestMapping("/doDeleteBatch")
	public Object doDeleteBatch(Integer[] id) {
		AjaxResult result = new AjaxResult();

		try {
			int count = managerService.deleteBatchManager(id);

			result.setSuccess(count == id.length);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 跳转到项目分类操作页
	 * 
	 * @return
	 */
	@RequestMapping("/projectType")
	public String projectType() {
		return "manager/project_type";
	}

}
