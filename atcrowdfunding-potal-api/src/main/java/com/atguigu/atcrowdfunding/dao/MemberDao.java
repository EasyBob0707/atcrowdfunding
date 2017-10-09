package com.atguigu.atcrowdfunding.dao;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;

/**
 * 会员持久化接口
 * 
 * @Author SUNBO
 * @Date 2017年7月8日 下午7:43:35
 * @Version V1.0
 */
public interface MemberDao {

	/**
	 * 会员登录接口
	 * 
	 * @param paramMap
	 *            传递数据
	 * @return 返回一个manager的对象
	 */
	Member queryMemberForLogin(Map<String, Object> paramMap);

	/**
	 * 会员注册入口
	 * 
	 * @param paramMap
	 *            注册系统需要的参数信息
	 * @return 0: 未注册成功 1: 注册成功
	 */
	Integer addMemberForRegister(Map<String, Object> paramMap);

	/**
	 * 申请认证(更新账户类型)
	 * 
	 * @param member
	 * @return
	 */
	int updateAcctType(Member member);

	/**
	 * 更新会员基本信息
	 * 
	 * @param loginMember
	 * @return
	 */
	int updateBasicInfo(Member loginMember);

	/**
	 * 保存会员的资质信息
	 *
	 * @param memberCert
     */
	void saveMemberCertInfo(MemberCert memberCert);

	/**
	 * 更新数据库会员表中邮箱地址
	 *
	 * @param member
     */
	void updateMemberEmailByMember(Member member);

    /**
     * 更新数据库会员表中的邮箱地址
     *
     * @param member
     */
    void updateAuthstatus(Member member);

	/**
	 * 获取会员的信息
	 *
	 * @param id
	 * @return
     */
	Member queryMemberByMemberId(Integer id);
}
