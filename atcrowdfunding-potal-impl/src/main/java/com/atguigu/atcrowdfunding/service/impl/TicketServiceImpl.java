package com.atguigu.atcrowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Ticket;
import com.atguigu.atcrowdfunding.dao.TicketDao;
import com.atguigu.atcrowdfunding.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao ;
	
	/**
	 * 根据会员的id查询流程单(查看用户认证流程执行到哪步操作)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Ticket queryTicketByMemberId(Integer id) {
		return ticketDao.queryTicketByMemberId(id);
	}

	/**
	 * 更新流程单中的认证流程
	 * 
	 * @param ticket
	 * @return
	 */
	@Override
	public int updateTicketProcessStep(Ticket ticket) {
		return ticketDao.updateTicketProcessStep(ticket);
	}

	/**
	 * 更新用户的流程单
	 * 
	 * @param ticket
	 */
	@Override
	public void saveTicket(Ticket ticket) {
		ticketDao.saveTicket(ticket);
	}

	/**
	 * 发送邮件完成之后, 更新流程单中的相关信息
	 *
	 * @param ticket
	 * @return
     */
	@Override
	public int updateTicketInfo(Ticket ticket) {
		return ticketDao.updateTicketInfo(ticket);
	}

    /**
     * 通过流程定义ID获取到会员的相关信息(t_ticket 流程单)
     *
     * @param processInstanceId
     * @return
     */
	@Override
	public Ticket queryTicketByPiid(String processInstanceId) {
		return ticketDao.queryTicketByPiid(processInstanceId);
	}

	/**
	 * 更新流程单中的认证流程
	 *
	 * @param memberid
     */
	@Override
	public void updateTicketStatus(Integer memberid) {
		ticketDao.updateTicketStatus(memberid);
	}
}
