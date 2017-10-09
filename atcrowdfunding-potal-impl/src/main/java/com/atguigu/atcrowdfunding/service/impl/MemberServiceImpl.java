package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.MemberCert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.dao.MemberDao;
import com.atguigu.atcrowdfunding.service.MemberService;

/**
 * 会员业务接口
 *
 * @Author SUNBO
 * @Date 2017年7月8日 下午8:01:45
 * @Version V1.0
 */
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao managerDao;
	
	@Autowired
	private MemberDao memberDao;

	/**
	 * 会员登录接口
	 * 
	 * @param paramMap
	 *            登录系统需要的参数
	 * @return 获取一个manager的对象(有该对象则登录成功, 否则失败)
	 */
	@Override
	public Member queryMemberForLogin(Map<String, Object> paramMap) {
		return managerDao.queryMemberForLogin(paramMap);
	}

	/**
	 * 会员注册入口
	 * 
	 * @param paramMap 注册系统需要的参数信息
	 * @return 0: 未注册成功    1: 注册成功
	 */
	@Override
	public Integer addMemberForRegister(Map<String, Object> paramMap) {
		return managerDao.addMemberForRegister(paramMap);
	}

	/**
	 * 申请认证(更新账户类型)
	 * 
	 * @param member
	 * @return
	 */
	@Override
	public int updateAcctType(Member member) {
		return memberDao.updateAcctType(member);
	}
	
	/**
	 * 更新会员基本信息
	 * 
	 * @param loginMember
	 * @return
	 */
	@Override
	public int updateBasicInfo(Member loginMember) {
		return memberDao.updateBasicInfo(loginMember);
	}

	/**
	 * 保存会员的资质信息
	 *
	 * @param certimgs
     */
	@Override
	public void saveMemberCertInfo(List<MemberCert> certimgs) {
		for (MemberCert memberCert : certimgs) {
			memberDao.saveMemberCertInfo(memberCert);
		}
	}

    /**
     * 更新数据库会员表中的邮箱地址
     *
     * @param member the member
     */
    @Override
	public void updateMemberEmailByMember(Member member) {
		memberDao.updateMemberEmailByMember(member);
	}

	/**
	 * 更新会员的审核状态
	 *
	 * @param member
     */
    @Override
    public void updateAuthstatus(Member member) {
        memberDao.updateAuthstatus(member);
    }

	/**
	 * 获取会员的信息列表
	 *
	 * @param id
	 * @return
     */
	@Override
	public Member queryMemberByMemberId(Integer id) {
		return memberDao.queryMemberByMemberId(id);
	}
}
