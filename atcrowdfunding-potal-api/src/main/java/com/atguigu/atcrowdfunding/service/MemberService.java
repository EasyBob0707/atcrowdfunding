package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;

/**
 * 会员登录接口
 *
 * @Author SUNBO
 * @Date 2017年7月8日 下午7:40:16
 * @Version V1.0
 */
public interface MemberService {

    /**
     * 会员登录入口.
     *
     * @param paramMap 登录系统需要的参数.
     * @return 获取一个manager的对象(有该对象则登录成功, 否则失败). member
     */
    public Member queryMemberForLogin(Map<String, Object> paramMap);

    /**
     * 会员注册入口
     *
     * @param paramMap 注册系统需要的一些参数
     * @return 0 : 未注册成功    1: 注册成功
     */
    public Integer addMemberForRegister(Map<String, Object> paramMap);

    /**
     * 申请认证(更新账户类型)
     *
     * @param member the member
     * @return int int
     */
    public int updateAcctType(Member member);

    /**
     * 更新会员基本信息
     *
     * @param loginMember the login member
     * @return int int
     */
    public int updateBasicInfo(Member loginMember);

    /**
     * 保存会员上传的资质信息
     *
     * @param certimgs the certimgs
     */
    void saveMemberCertInfo(List<MemberCert> certimgs);

    /**
     * Update member email.
     *
     * @param member the member
     */
    void updateMemberEmailByMember(Member member);

    /**
     * 更新会员的状态
     *
     * @param member
     */
    void updateAuthstatus(Member member);

    /**
     * 通过从流程单中获取memberId, 从而获取member
     *
     * @param id
     * @return
     */
    Member queryMemberByMemberId(Integer id);
}
