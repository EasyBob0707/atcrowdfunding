package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.Message;
import com.atguigu.atcrowdfunding.utils.Page;


public interface MessageService {

	Page<Message> queryMessage();

	Message queryMessageById(Integer id);
	
	int updateMessage(Message message);

}
