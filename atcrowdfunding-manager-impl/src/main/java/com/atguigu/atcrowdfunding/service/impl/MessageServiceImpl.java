package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Message;
import com.atguigu.atcrowdfunding.dao.MessageDao;
import com.atguigu.atcrowdfunding.service.MessageService;
import com.atguigu.atcrowdfunding.utils.Page;


@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageDao messageDao;
	
	//查询所有的消息
	@Override
	public Page<Message> queryMessage() {
		Page<Message> page = new Page<Message>();
		List<Message> data = messageDao.queryMessage();
		page.setData(data);
		return page;
	}

	//根据id查询一条message
	@Override
	public Message queryMessageById(Integer id) {
		return messageDao.queryMessageById(id);
	}

	//修改message
	@Override
	public int updateMessage(Message message) {
		return messageDao.updateMessage(message);
	}

}
