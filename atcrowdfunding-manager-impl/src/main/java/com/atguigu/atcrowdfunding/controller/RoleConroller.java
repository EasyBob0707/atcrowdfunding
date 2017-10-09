package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Const;
import com.atguigu.atcrowdfunding.utils.Datas;
import com.atguigu.atcrowdfunding.utils.Page;
import com.atguigu.atcrowdfunding.utils.StringUtil;

/**
 * 
 * @Author MAJUN
 * @Date 2017年7月10日 下午8:45:47
 * @Version V1.0
 */
@Controller
@RequestMapping("/role")
public class RoleConroller {

	@Autowired
	private RoleService roleService;

	// 到角色维护页面
	@RequestMapping(value = "/toRole", method = RequestMethod.GET)
	public String toRole() {
		return "role/index";
	}

	// 带查询条件,异步分页查询
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "2") Integer pagesize,
			String queryText, Map<String, Object> map) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);

			if (StringUtil.isNotEmpty(queryText) && queryText.contains("%")) {
				queryText = queryText.replaceAll("%", "\\\\%"); // "\\%" -> "\%"
																// ->
																// concat('%','\%','%')
																// =>'%\%%'
			}
			paramMap.put("queryText", queryText);
			Page<Role> page = roleService.queryPage(paramMap);

			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("异步加载数据失败!");
			result.setSuccess(false);
		}

		return result;

	}

	// 批量删除
	@ResponseBody
	@RequestMapping(value = "/doDeleteBatch", method = RequestMethod.POST)
	public Object doDeleteBatch(Integer[] id) {
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.deleteBatchRole(id);
			result.setSuccess(count == id.length);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;

	}

	// 根据id删除角色
	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.deleteRole(id);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	// 跳转到修改edit页面
	@RequestMapping("/edit")
	public String edit(Integer id, Map<String, Role> map) {
		Role role = roleService.getRoleById(id);
		map.put("role", role);
		return "role/edit";
	}

	// 修改角色名称操作
	@ResponseBody
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public Object doUpdate(Role role) {
		AjaxResult result = new AjaxResult();
		System.out.println(role.getName() + role.getId());
		try {
			int count = roleService.updateRole(role);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	// 跳转到添加界面
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "role/add";
	}

	// 添加角色名称的操作
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Role role) {
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.addRole(role);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 跳转到为权限分配许可的页面
	 * 
	 * @param roleid
	 *            权限ID
	 * @param map
	 * @return
	 */
	@RequestMapping("/assign")
	public String assign(Integer roleId, Map<String, Role> map) {
		Role role = roleService.getRole(roleId);
		map.put(Const.ROLE, role);
		return "role/assign";
	}

	/**
	 * 给权限分配许可
	 * 
	 * @param datas
	 *            许可集合
	 * @param roleid
	 *            权限ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAssignRolePermission")
	public Object doAssignRolePermission(Datas datas, Integer roleId) {
		AjaxResult result = new AjaxResult();
		try {

			int count = roleService.saveRolePermissionRelationship(
					datas.getIds(), roleId);
			if (count == datas.getIds().size()) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

}
