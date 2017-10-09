package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.dao.RoleDao;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.utils.Page;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Page<Role> queryPage(Map<String, Object> paramMap) {
		Page<Role> page = new Page<Role>((Integer) paramMap.get("pageno"),
				(Integer) paramMap.get("pagesize"));
		paramMap.put("startIndex", page.getStartIndex());
		List<Role> roleList = roleDao.queryPage(paramMap);
		page.setData(roleList);
		int totalsize = roleDao.queryCont(paramMap);
		page.setTotalsize(totalsize);
		return page;
	}

	// 根据id删除角色的业务实现
	@Override
	public int deleteRole(Integer id) {
		return roleDao.delete(id);
	}

	// 根据id找到要修改角色的业务实现
	@Override
	public Role getRoleById(Integer id) {
		return roleDao.getRoleById(id);
	}

	// 修改角色名称的业务
	@Override
	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	// 添加角色名称的业务实现
	@Override
	public int addRole(Role role) {
		return roleDao.addRole(role);
	}

	// 批量删除的业务实现
	@Override
	public int deleteBatchRole(Integer[] id) {
		int totalCount = roleDao.deleteBatchRole(id);
		if (totalCount != id.length) {
			throw new RuntimeException("批量删除失败!");
		}
		return totalCount;
	}

	@Override
	public List<Role> queryAllRole() {
		return roleDao.queryAllRole();
	}

	/**
	 * 通过ID获取到权限的信息
	 * 
	 * @param roleid 权限ID
	 * @return
	 */
	@Override
	public Role getRole(Integer roleid) {
		return roleDao.getRole(roleid);
	}

	/**
	 * 分配权限对应的许可
	 * 
	 * @param ids 许可集合
	 * @param roleId 权限
	 * @return
	 */
	@Override
	public int saveRolePermissionRelationship(List<Integer> ids, Integer roleId) {
		//为了避免有相同的许可被多次分配同一个权限上, 在分配许可时先要将之前的许可进行删除, 然后在插入
		roleDao.deleteRolePermissionRelationship(roleId);
		//返回插入成功的条数
		int insertCount = 0;
		//如果ids为空, 说明在该权限上没有任何的许可(将许可全部取消)
		if(ids.size()!=0){
			insertCount = roleDao.saveRolePermissionRelationship(ids, roleId);
		}
		return insertCount;
	}

}
