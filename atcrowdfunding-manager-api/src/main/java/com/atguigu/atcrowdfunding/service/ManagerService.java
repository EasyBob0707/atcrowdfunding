package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Manager;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.utils.Page;

/**
 * 用户业务处理
 * 
 * @Author: SUNBO
 * @Date: 2017年7月5日 下午10:05:01
 * @Version: V1.0
 * 
 */
public interface ManagerService {

	/**
	 * 用户登录业务处理
	 * 
	 * @param user
	 * @return 用户登录是否成功 0: 失败 1: 成功
	 */
	public Integer login(Manager user);

	/**
	 * 获管理员权限信息
	 * 
	 * @return
	 */
	public List<Role> getRoles();

	/**
	 * 真正注册的业务处理
	 * 
	 * @param user
	 * @return 1: 表示注册成功 0: 表示注册失败
	 */
	public Integer doReg(Manager user);

	/**
	 * 关联用户与角色
	 * 
	 * @param user
	 *            通过用户user关联角色
	 */
	public void insertUserId(Manager user);

	/**
	 * 管理员登录接口
	 * 
	 * @param paramMap
	 *            登录系统需要的参数
	 * @return 获取一个manager的对象(有该对象则登录成功, 否则失败)
	 */
	public Manager queryManagerForLogin(Map<String, Object> paramMap);

	/**
	 * 管理员注册的接口
	 * 
	 * @param paramMap
	 *            提交注册信息
	 * @return 返回注册的标识信息
	 */
	public Integer addManagerForRegister(Map<String, Object> paramMap);

	/**
	 * 查询分页信息的接口
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 返回分页数据
	 */
	public Page<Manager> queryPage(Map<String, Object> paramMap);

	/**
	 * 新增管理员操作
	 * 
	 * @param manager
	 *            新增管理员的字段信息
	 * @return 1: 操作成功 0: 操作失败
	 */
	public int saveManager(Manager manager);

	/**
	 * 查询管理员信息用于做修改
	 * 
	 * @param id
	 * @return
	 */
	public Manager getManagerById(Integer id);

	/**
	 * 修改管理员的信息
	 * 
	 * @param manager
	 * @return
	 */
	public int updateManager(Manager manager);

	/**
	 * 删除管理员的操作
	 * 
	 * @param id
	 *            根据ID进行删除
	 * @return
	 */
	public int deleteManager(Integer id);

	/**
	 * 批量进行删除
	 * 
	 * @param id
	 *            要删除的id
	 * @return
	 */
	public int deleteBatchManager(Integer[] id);

	/**
	 * 判断注册的账号是否存在
	 * 
	 * @param paramMap
	 * @return
	 */
	public Manager queryLoginacct(Map<String, Object> paramMap);

	/**
	 * 取消权限
	 * 
	 * @param userid
	 *            对应的管理员
	 * @param ids
	 *            所有权限的集合
	 * @return 返回取消权限的标识
	 */
	public int deleteManagerRoleRelationship(Integer userid, List<Integer> ids);

	/**
	 * 分配权限
	 * 
	 * @param userid 对应的管理员
	 * @param ids 所有权限的集合
	 * @return 返回分配权限的标识
	 */
	public int saveManagerRoleRelationship(Integer userid, List<Integer> ids);
	
	/**
	 * 根据用户查询权限
	 * @param id 用户ID
	 * @return 权限列表
	 */
	public List<Integer> queryRoleidsByManagerid(Integer id);
	
	/**
	 * 获取该管理员的对应的权限许可
	 * 
	 * @param managerId 管理员ID 
	 * @return
	 */
	public List<Permission> queryPermissionByUserid(String managerId);

}
