package com.atguigu.atcrowdfunding.dao;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Message;

public interface MessageDao {
	
	int updateMessage(Message message);

	List<Message> queryMessage();

	Message queryMessageById(Integer id);

}
