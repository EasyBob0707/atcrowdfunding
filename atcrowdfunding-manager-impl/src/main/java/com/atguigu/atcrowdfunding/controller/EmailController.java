package com.atguigu.atcrowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.utils.DesUtil;

@RequestMapping("email")
@Controller
public class EmailController {
	
	@ResponseBody
	@RequestMapping("/act")
	public Object getVerificationCode(String p) throws Exception {
		//获取邮件当中的验证码, 并将其解码
		String val = DesUtil.decrypt(p, "abcdefghijklmnopquvwxyz");
		System.out.println(val);
		return val;
	}
}
