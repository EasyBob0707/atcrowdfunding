package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.bean.Ticket;

public interface TicketDao {

	/**
	 * 根据会员的id查询流程单(查看用户认证流程执行到哪步操作)
	 * 
	 * @param id
	 * @return
	 */
	Ticket queryTicketByMemberId(Integer id);

	/**
	 * 更新流程单中的认证流程
	 * 
	 * @param ticket
	 * @return
	 */
	int updateTicketProcessStep(Ticket ticket);
	
	/**
	 * 更新会员流程单
	 * 
	 * @param ticket
	 */
	void saveTicket(Ticket ticket);

	/**
	 * 邮件发送完成之后, 更新流程单中的相关信息
	 *
	 * @param ticket
	 * @return
     */
	int updateTicketInfo(Ticket ticket);

	/**
	 * 通过流程定义ID获取到会员的相关信息(t_ticket 流程单)
	 *
	 * @param processInstanceId
	 * @return
	 */
	Ticket queryTicketByPiid(String processInstanceId);

	/**
	 * 更新流程单中的认证流程
	 *
	 * @param memberid
     */
	void updateTicketStatus(Integer memberid);
}
