package com.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.atguigu.atcrowdfunding.act.listener.NoListener;
import com.atguigu.atcrowdfunding.act.listener.YesListener;
import com.atguigu.atcrowdfunding.utils.DesUtil;

import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

public class TestEmail {

	@Test
	public void test() throws Exception {
		// 使用JAVA程序发送邮件
		ApplicationContext application = new ClassPathXmlApplicationContext(
				"spring/spring-*.xml");

		// 邮件发送器，由Spring框架提供的
		JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) application
				.getBean("sendMail");

		javaMailSender.setDefaultEncoding("UTF-8");
		MimeMessage mail = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail);
		helper.setSubject("审核用户信息实名制");
		StringBuilder content = new StringBuilder();

		String param = "userid123";
		param = DesUtil.encrypt(param, "abcdefghijklmnopquvwxyz");

		content.append("<a href='http://www.atcrowdfunding.com/test/act.do?p="
				+ param + "'>激活链接</a>");
		helper.setText(content.toString(), true);
		helper.setFrom("admin@atguigu.com");
		helper.setTo("test@atguigu.com");
		javaMailSender.send(mail);
	}

	@Test
	public void test01() {
		
		// 部署流程
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("spring/spring-*.xml");
		// 获取流程引擎
		ProcessEngine processEngine = (ProcessEngine)ctx.getBean("processEngine");
		System.out.println("processEngine="+processEngine);
		
		//存储服务对象,可以进行流程部署等操作
		RepositoryService repositoryService = 
				processEngine.getRepositoryService();
		ProcessDefinitionQuery processDefinitionQuery = 
				repositoryService.createProcessDefinitionQuery();
		
		ProcessDefinition processDefinition = 
				processDefinitionQuery.processDefinitionKey("myProcess")
				.latestVersion().singleResult();
		
		System.out.println(processDefinition.getName());
		System.out.println(processDefinition.getKey());
		System.out.println(processDefinition.getVersion());
		
		//启动流程实例
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("yesListener", new YesListener());
		variables.put("noListener", new NoListener());
		//variables.put("flag", true); //流程实例还没开始,组长审批是通过的还是拒绝的在此时未知的.
		
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(processDefinition.getId(),variables);
		System.out.println("processInstance="+processInstance);
		
		
		TaskService taskService = processEngine.getTaskService();
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		for (Task task : list) {
			
			//审批结果,可能是通过,拒绝?
			taskService.setVariable(task.getId(), "flag", false);
			
			//完成审批任务
			taskService.complete(task.getId());
			
		}
	}


	/**
	 * 使用JDK发送单条短信,智能匹配短信模板[为了从网上查询云片网的jar包依赖]
	 *
	 * @param apikey
	 *            成功注册后登录云片官网,进入后台可查看
	 * @param text
	 *            需要使用已审核通过的模板或者默认模板
	 * @param mobile
	 *            接收的手机号,仅支持单号码发送
	 */
	@Test
	public void yun_pian_wang(String apikey, String mobile, String text) {
		YunpianRestClient client = new YunpianRestClient(apikey);// 用apikey生成client,可作为全局静态变量
		SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
		ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(mobile, text);// 发送短信,ResultDO<?>.isSuccess()判断是否成功
		System.out.println(result);
	}

}
