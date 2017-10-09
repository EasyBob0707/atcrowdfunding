package com.atguigu.atcrowdfunding.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Manager;
import com.atguigu.atcrowdfunding.bean.ManagerRole;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.dao.ManagerDao;
import com.atguigu.atcrowdfunding.service.ManagerService;
import com.atguigu.atcrowdfunding.utils.Const;
import com.atguigu.atcrowdfunding.utils.DateStringConvert;
import com.atguigu.atcrowdfunding.utils.MD5Util;
import com.atguigu.atcrowdfunding.utils.ManagerException;
import com.atguigu.atcrowdfunding.utils.Page;

/**
 * 用户业务处理
 * 
 * @Author: SUNBO
 * @Date: 2017年7月5日 下午10:05:01
 * @Version: V1.0
 * 
 */
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	/**
	 * 用户登录业务处理
	 * 
	 * @param user
	 * @return 用户登录是否成功 0: 失败 1: 成功
	 */
	@Override
	public Integer login(Manager user) {
		if (user != null) {
			return managerDao.getLogin(user);
		}
		throw new ManagerException("用户登录失败!");
	}

	@Override
	public List<Role> getRoles() {
		return managerDao.getRoleInfo();
	}

	/**
	 * 真正注册的业务处理
	 * 
	 * @param manager
	 * @return 1: 表示注册成功 0: 表示注册失败
	 */
	@Override
	public Integer doReg(Manager manager) {
		if (manager != null) {
			return managerDao.realReg(manager);
		}
		throw new ManagerException("注册失败!");
	}

	/**
	 * 关联用户与角色
	 * 
	 * @param manager
	 *            通过用户user关联角色
	 */
	@Override
	public void insertUserId(Manager manager) {
		managerDao.insertUserId(manager);
	}

	/**
	 * 管理员登录接口
	 * 
	 * @param paramMap
	 *            登录系统需要的参数
	 * @return 获取一个manager的对象(有该对象则登录成功, 否则失败)
	 */
	@Override
	public Manager queryManagerForLogin(Map<String, Object> paramMap) {
		return managerDao.queryManagerForLogin(paramMap);
	}

	/**
	 * 管理员注册的接口
	 * 
	 * @param paramMap
	 *            提交注册信息
	 * @return 返回注册的标识信息
	 */
	@Override
	public Integer addManagerForRegister(Map<String, Object> paramMap) {
		return managerDao.addManagerForRegister(paramMap);
	}

	/**
	 * 查询分页信息的接口
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 返回分页数据
	 */
	@Override
	public Page<Manager> queryPage(Map<String, Object> paramMap) {
		// 将数据封装到page中进行返回
		Page<Manager> page = new Page<Manager>(
				(Integer) paramMap.get("pageno"),
				(Integer) paramMap.get("pagesize"));

		paramMap.put("startIndex", page.getStartIndex());

		// 默认管理员不被查询出来
		paramMap.put("rootManager", Const.ROOT_MANAGER);

		List<Manager> managerList = managerDao.queryPage(paramMap);

		page.setData(managerList);

		// 查询分页总记录数
		Integer totalsize = managerDao.queryCount(paramMap);

		page.setTotalsize(totalsize);

		return page;
	}

	/**
	 * 新增管理员操作
	 * 
	 * @param manager
	 *            新增管理员的字段信息
	 * @return 1: 操作成功 0: 操作失败
	 */
	@Override
	public int saveManager(Manager manager) {
		// 由于新增管理员时页面中不提供管理员的密码和创建时间(需要在此处对数据进行初始化)
		manager.setUserpswd(MD5Util.digest(Const.DEFAULT_PASSWORD));
		manager.setCreatetime(DateStringConvert.dateToString(new Date()));
		return managerDao.saveManager(manager);
	}

	/**
	 * 查询管理员信息用于做修改
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Manager getManagerById(Integer id) {
		return managerDao.getManagerById(id);
	}

	/**
	 * 修改管理员的信息
	 * 
	 * @param manager
	 * @return
	 */
	@Override
	public int updateManager(Manager manager) {
		return managerDao.updateManager(manager);
	}

	/**
	 * 删除管理员的操作
	 * 
	 * @param id
	 *            根据ID进行删除
	 * @return
	 */
	@Override
	public int deleteManager(Integer id) {
		return managerDao.deleteManager(id);
	}

	/**
	 * 批量进行删除
	 * 
	 * @param id
	 *            要删除的id
	 * @return
	 */
	@Override
	public int deleteBatchManager(Integer[] id) {
		// 使用动态SQL进行批量删除
		int deleteNum = managerDao.deleteBatchManager(id);
		if (deleteNum != id.length) {
			throw new ManagerException("批量删除失败!");
		}
		return deleteNum;
	}

	/**
	 * 判断注册的账号是否存在
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Manager queryLoginacct(Map<String, Object> paramMap) {
		return managerDao.getLoginacct(paramMap);
	}

	/**
	 * 取消权限
	 * 
	 * @param userid
	 *            对应的管理员
	 * @param ids
	 *            所有权限的集合
	 * @return 返回取消权限的标识
	 */
	@Override
	public int deleteManagerRoleRelationship(Integer managerid, List<Integer> ids) {
		return managerDao.deleteManagerRoleRelationship(managerid, ids);
	}

	/**
	 * 分配权限
	 * 
	 * @param userid
	 *            对应的管理员
	 * @param ids
	 *            所有权限的集合
	 * @return 返回分配权限的标识
	 */
	@Override
	public int saveManagerRoleRelationship(Integer managerid, List<Integer> ids) {
		int count = 0;
		for (Integer roleid : ids) {
			ManagerRole ur = new ManagerRole(managerid, roleid);
			count += managerDao.insertManagerRole(ur);
		}

		return count;
	}

	/**
	 * 根据用户查询权限
	 * 
	 * @param id
	 *            用户ID
	 * @return 权限列表
	 */
	@Override
	public List<Integer> queryRoleidsByManagerid(Integer id) {
		return managerDao.queryRoleidsByManagerid(id);
	}

	/**
	 * 获取该管理员的对应的权限许可
	 * 
	 * @param id 管理员ID
	 * @return
	 */
	@Override
	public List<Permission> queryPermissionByUserid(String managerId) {
		return managerDao.queryPermissionByUserid(managerId);
	}

}
