package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Const;

/**
 * 权限维护
 * 
 * @Author SUNBO
 * @Date 2017年7月13日 下午12:40:11
 * @Version V1.0
 */
@RequestMapping("/permission")
@Controller
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	/**
	 * 跳转到权限维护
	 * 
	 * @return 跳转到页面
	 */
	@RequestMapping("/index")
	public String toPermission() {
		return "permission/index";
	}

	/**
	 * 加载权限数据(只加载一次, 手动进行拼装数据)
	 * 
	 * @return 返回JSON数据
	 */
	@ResponseBody
	@RequestMapping("/toLoadingData")
	public Object toLoadingData() {
		// 返回数据
		AjaxResult result = new AjaxResult();
		try {
			// 从数据库中查询出所有的节点
			List<Permission> allList = permissionService.queryAllPermission();
			// 然后将查询出来的数据存放到MAP中
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();

			for (Permission permission : allList) {
				permissionMap.put(permission.getId(), permission);
			}

			Permission root = null;
			// 再次遍历allList
			for (Permission permission : allList) {
				// 寻找根节点(pid为空的就是根节点)
				if (permission.getPid() == null) {
					root = permission;
				} else {
					// 不是父节点就是一个子节点
					Permission child = permission;
					// 通过子节点去寻找父节点
					Permission parent = permissionMap.get(child.getPid());
					// 然后就子节点放到父节点下
					parent.getChildren().add(child);
				}
			}
			result.setData(root);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("加载数据失败!");
			result.setSuccess(false);
		}
		return result;

	}

	/**
	 * 异步加载许可树,分配许可,回显许可(分配许可)
	 * 
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/asyncLoadDataAssign")
	public Object asyncLoadDataAssign(Integer roleId){
		
		//查询之前角色所分配的许可id
		List<Integer> permissionIdList =
				permissionService.queryRolePermissionByRoleid(roleId);
		
		//获取所有的权限节点
		List<Permission> allList = permissionService.queryAllPermission();
		
		//将所有Permission存放到Map中,有利于Permission查找
		Map<Integer, Permission> map = new HashMap<Integer,Permission>();
		
		for (Permission permission : allList) {
			
			//判断是否回显(复选框是否被勾选)
			if(permissionIdList.contains(permission.getId())){
				permission.setChecked(true);
			}
			
			map.put(permission.getId(), permission);
		}

		
		Permission root = null ;		
		for (Permission permission : allList) { 
			if(permission.getPid() == null){ //查找根节点
				root = permission ;
			}else{
				Permission child = permission ;
				Permission parent = map.get(child.getPid()); //查询子节点父节点.
				parent.getChildren().add(child); //组合父子节点关系.
			}
		}			

		List<Permission> rootPermission = new ArrayList<Permission>();
		rootPermission.add(root);

		return rootPermission ;
	}

	/**
	 * 加载权限数据(只加载一次, 手动进行拼装数据) 换为异步加载ztree
	 * 
	 * @return 返回JSON数据
	 */
	@ResponseBody
	@RequestMapping("/asyncLoadData")
	public Object asyncLoadData() {

		List<Permission> allList = permissionService.queryAllPermission();

		// 将所有Permission存放到Map中,有利于Permission查找
		Map<Integer, Permission> map = new HashMap<Integer, Permission>();

		for (Permission permission : allList) {
			map.put(permission.getId(), permission);
		}

		Permission root = null;
		for (Permission permission : allList) {
			if (permission.getPid() == null) { // 查找根节点
				root = permission;
			} else {
				Permission child = permission;
				Permission parent = map.get(child.getPid()); // 查询子节点父节点.
				parent.getChildren().add(child); // 组合父子节点关系.
			}
		}

		List<Permission> rootPermission = new ArrayList<Permission>();
		rootPermission.add(root);

		return rootPermission;
	}

	/**
	 * 跳转到节点添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "permission/add";
	}

	/**
	 * 业务需求只需在输的分支上添加叶子节点信息
	 * 
	 * @param permission
	 *            添加的叶子节点信息
	 * @return 返回JSON格式的数据
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission) {
		AjaxResult result = new AjaxResult();

		try {
			int count = permissionService.savePermission(permission);

			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 业务需求只能删除叶子节点(分支和父节点不能删除)
	 * 
	 * @param permission
	 *            删除叶子节点
	 * @return 返回JSON格式的数据
	 */
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Permission permission) {
		AjaxResult result = new AjaxResult();

		try {
			int count = permissionService.deletePermission(permission);

			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 修改叶子节点的信息
	 * 
	 * @param permission
	 *            修改叶子节点的信息
	 * @param paramMap
	 *            存放到REQUEST域中将数据进行回显
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(Permission permission, Map<String, Permission> paramMap) {
		paramMap.put(Const.TREE_PERMISSION,
				permissionService.queryPermissionById(permission.getId()));
		return "permission/edit";
	}

	/**
	 * 修改叶子节点的信息
	 * 
	 * @param permission
	 *            传递要修改的叶子节点信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doEdit")
	public Object doEdit(Permission permission) {
		AjaxResult result = new AjaxResult();
		try {
			int count = permissionService.updatePermission(permission);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
