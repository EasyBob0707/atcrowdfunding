package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.utils.Page;

public interface RoleService {

	Page<Role> queryPage(Map<String, Object> paramMap);

	int deleteRole(Integer id);

	Role getRoleById(Integer id);

	int updateRole(Role role);

	int addRole(Role role);

	int deleteBatchRole(Integer[] id);

	List<Role> queryAllRole();

	/**
	 * 通过ID获取到权限的信息
	 * 
	 * @param roleid 权限ID
	 * @return
	 */
	Role getRole(Integer roleid);

	/**
	 * 分配权限对应的许可
	 * 
	 * @param ids 许可集合
	 * @param roleId 权限
	 * @return
	 */
	int saveRolePermissionRelationship(List<Integer> ids, Integer roleId);
}
