package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.atcrowdfunding.bean.Role;

public interface RoleDao {

	/**
	 * 分页查询
	 */
	List<Role> queryPage(Map<String, Object> paramMap);

	/**
	 * 分页查询，计算记录数
	 */
	int queryCont(Map<String, Object> paramMap);

	// 根据id删除角色
	int delete(Integer id);

	// 根据id找到要修改的角色
	Role getRoleById(Integer id);

	// 修改角色名称的方法
	int updateRole(Role role);

	// 添加角色名称的方法
	int addRole(Role role);

	// 批量删除
	int deleteBatchRole(Integer[] id);

	// 获取全部的权限
	List<Role> queryAllRole();

	/**
	 * 通过ID获取到权限的信息
	 * 
	 * @param roleid
	 *            权限ID
	 * @return
	 */
	Role getRole(Integer roleid);

	/**
	 * 分配权限对应的许可
	 * 
	 * @param ids
	 *            许可集合
	 * @param roleId
	 *            权限
	 * @return
	 */
	int saveRolePermissionRelationship(@Param("ids") List<Integer> ids,
			@Param("roleId") Integer roleId);

	/**
	 * 删除权限对应的许可
	 * 
	 * @param roleId 权限ID
	 */
	int deleteRolePermissionRelationship(Integer roleId);
}
