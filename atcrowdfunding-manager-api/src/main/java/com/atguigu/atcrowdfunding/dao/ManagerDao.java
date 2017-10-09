package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.atcrowdfunding.bean.ManagerRole;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.Manager;

/**
 * 用户持久化处理
 * 
 * @author SUNBO
 * @version 1.0
 */
public interface ManagerDao {

	/**
	 * 用户登录处理
	 * 
	 * @param user
	 * @return
	 */
	Integer getLogin(Manager user);

	/**
	 * 回显用户角色信息
	 * 
	 * @return 返回角色信息
	 */
	List<Role> getRoleInfo();

	/**
	 * 真正注册的业务处理
	 * 
	 * @param user
	 * @return 1: 表示注册成功 0: 表示注册失败
	 */
	Integer realReg(Manager user);

	/**
	 * 关联用户与角色
	 * 
	 * @param user
	 */
	void insertUserId(Manager user);

	/**
	 * 管理员登录接口
	 * 
	 * @param paramMap
	 *            传递数据
	 * @return 返回一个manager的对象
	 */
	Manager queryManagerForLogin(Map<String, Object> paramMap);

	/**
	 * 用户注册接口
	 * 
	 * @param paramMap
	 *            注册传递的
	 * @return 注册标识(0: 失败 1:成功)
	 */
	Integer addManagerForRegister(Map<String, Object> paramMap);

	/**
	 * 查询分页信息的接口
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 返回分页数据
	 */
	List<Manager> queryPage(Map<String, Object> paramMap);

	/**
	 * 查询分页记录数的条数
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 记录条数
	 */
	Integer queryCount(Map<String, Object> paramMap);

	/**
	 * 新增管理员操作
	 * 
	 * @param manager 新增管理员的字段信息
	 * @return 1: 操作成功  0: 操作失败
	 */
	int saveManager(Manager manager);
	
	/**
	 * 查询管理员信息用于做修改
	 * 
	 * @param id 
	 * @return
	 */
	Manager getManagerById(Integer id);

	/**
	 * 修改管理员的信息
	 * 
	 * @param manager
	 * @return
	 */
	int updateManager(Manager manager);

	/**
	 * 删除管理员的操作
	 * 
	 * @param id 根据ID进行删除
	 * @return
	 */
	int deleteManager(Integer id);
	
	/**
	 * 批量进行删除
	 * 
	 * @param id 要删除的id
	 * @return
	 */
	int deleteBatchManager(Integer[] id);
	
	/**
	 * 判断注册的账号是否存在
	 * 
	 * @param paramMap
	 * @return
	 */
	Manager getLoginacct(Map<String, Object> paramMap);

	/**
	 * 取消权限
	 * 
	 * @param userid
	 *            对应的管理员
	 * @param ids
	 *            所有权限的集合
	 * @return 返回取消权限的标识
	 */
	int deleteManagerRoleRelationship(@Param("managerid")Integer managerid, @Param("ids")List<Integer> ids);

	/**
	 * 分配权限
	 * 
	 * @param userid 对应的管理员
	 * @param ids 所有权限的集合
	 * @return 返回分配权限的标识
	 */
	int insertManagerRole(ManagerRole ur);

	/**
	 * 根据用户查询权限
	 * @param id 用户ID
	 * @return 权限列表
	 */
	List<Integer> queryRoleidsByManagerid(Integer id);

	/**
	 * 获取该管理员的对应的权限许可
	 * 
	 * @param id 管理员ID
	 * @return
	 */
	List<Permission> queryPermissionByUserid(String managerId);
}
