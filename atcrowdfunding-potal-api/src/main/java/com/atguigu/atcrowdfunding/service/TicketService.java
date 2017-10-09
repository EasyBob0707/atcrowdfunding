package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.Ticket;

public interface TicketService {
	
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
	 * 保存用户认证的流程单
	 * 
	 * @param ticket
	 */
	void saveTicket(Ticket ticket);

	/**
	 * 发送邮件完成之后, 更新流程单中的相关信息
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
	 * 设置流程单的状态
	 *
	 * @param memberid
     */
	void updateTicketStatus(Integer memberid);
}
