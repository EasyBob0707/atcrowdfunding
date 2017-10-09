package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.dao.PermissionDao;
import com.atguigu.atcrowdfunding.service.PermissionService;

/**
 * 权限维护
 * 
 * @Author SUNBO
 * @Date 2017年7月13日 下午12:56:11
 * @Version V1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;
	
	/**
	 * 查询全部的节点信息
	 * 
	 * @return 返回所有的节点信息
	 */
	@Override
	public List<Permission> queryAllPermission() {
		return permissionDao.queryAllPermission();
	}

	/**
	 * 业务需求只需在输的分支上添加叶子节点信息
	 * 
	 * @param permission
	 *            添加的叶子节点信息
	 * @return 返回JSON格式的数据
	 */
	@Override
	public int savePermission(Permission permission) {
		return permissionDao.savePermission(permission);
	}

	/**
	 * 业务需求只能删除叶子节点(分支和父节点不能删除)
	 * 
	 * @param permission 删除叶子节点
	 * @return 返回JSON格式的数据
	 */
	@Override
	public int deletePermission(Permission permission) {
		return permissionDao.deletePermission(permission);
	}

	/**
	 * 修改叶子节点的信息
	 * 
	 * @param permission 传递要修改的叶子节点信息
	 * @return
	 */
	@Override
	public int updatePermission(Permission permission) {
		return permissionDao.updatePermission(permission);
	}

	/**
	 * 根据叶子节点的ID对叶子节点进行修改
	 * 
	 * @param id 叶子节点的ID
	 * @return
	 */
	@Override
	public Permission queryPermissionById(Integer id) {
		return permissionDao.queryPermissionById(id);
	}

	/**
	 * 通过权限ID查询该权限所对应的许可
	 * 
	 * @param roleid 权限ID
	 * @return
	 */
	@Override
	public List<Integer> queryRolePermissionByRoleid(Integer roleId) {
		return permissionDao.queryRolePermissionByRoleid(roleId);
	}
}
