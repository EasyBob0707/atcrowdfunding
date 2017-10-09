package com.atguigu.atcrowdfunding.dao;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Permission;

/**
 * 权限维护
 * 
 * @Author SUNBO
 * @Date 2017年7月13日 下午1:00:00
 * @Version V1.0
 */
public interface PermissionDao {

	/**
	 * 查询全部的节点信息
	 * 
	 * @return 返回所有的节点信息
	 */
	List<Permission> queryAllPermission();

	/**
	 * 业务需求只需在输的分支上添加叶子节点信息
	 * 
	 * @param permission
	 *            添加的叶子节点信息
	 * @return 返回JSON格式的数据
	 */
	int savePermission(Permission permission);

	/**
	 * 业务需求只能删除叶子节点(分支和父节点不能删除)
	 * 
	 * @param permission
	 *            删除叶子节点
	 * @return 返回JSON格式的数据
	 */
	int deletePermission(Permission permission);

	/**
	 * 修改叶子节点的信息
	 * 
	 * @param permission
	 *            传递要修改的叶子节点信息
	 * @return
	 */
	int updatePermission(Permission permission);

	/**
	 * 根据叶子节点的ID对叶子节点进行修改
	 * 
	 * @param id
	 *            叶子节点的ID
	 * @return
	 */
	Permission queryPermissionById(Integer id);

	/**
	 * 通过权限ID查询该权限所对应的许可
	 * 
	 * @param roleid 权限ID
	 * @return
	 */
	List<Integer> queryRolePermissionByRoleid(Integer roleId);

}
