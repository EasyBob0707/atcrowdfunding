package com.atguigu.atcrowdfunding.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Message;
import com.atguigu.atcrowdfunding.service.MessageService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Page;
/**
 * 
 * @Author Alison
 * @Date 2017年7月12日 下午4:53:19   
 * @Version V1.0
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	
	//真正的修改
	@ResponseBody
	@RequestMapping(value="/doEdit")
	public Object doEdit(Message message){
		AjaxResult result = new AjaxResult();
		try {
			int count = messageService.updateMessage(message);
			if(count==1){
				result.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("修改失败失败！！");
		}
		return result;
	}
	
	//去到修改页面
	@RequestMapping(value="/edit")
	public String edit(Integer id,Map<String,Object> map){
		
		Message message = messageService.queryMessageById(id);
		map.put("message", message);
		
		return "message/message_edit";
	}
	
	
	//ajax加载data数据
	@ResponseBody
	@RequestMapping(value="/doMessage")
	public Object doMessage(){
		AjaxResult result = new AjaxResult();
		
		try {
			Page<Message> page = messageService.queryMessage();
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("查询消息失败");
		}
		
		return result;
	}
	
	//去到消息模板页面
	@RequestMapping("/index")
	public String Index(){
		return "message/message_index";
	}
}
