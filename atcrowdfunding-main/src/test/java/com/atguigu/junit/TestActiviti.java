package com.atguigu.junit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestActiviti {

	ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"spring/spring-*.xml");

	// 分组
	@Test
	public void testStart04() {
		// 获取ACTIVITI的引擎
		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
		.latestVersion().singleResult();
		
		//启动任务
		ProcessInstance processInstance = 
				runtimeService.startProcessInstanceById(processDefinition.getId());
		
		//获取到任务的服务
		TaskService taskService = processEngine.getTaskService();
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> list = taskQuery.taskCandidateGroup("tl").list();
		/*
		long count = taskQuery.taskAssignee("lisi").count();
		System.out.println("lisi领取之前的任务数量："+count);
		 */
		for (Task task : list) {
			taskService.claim(task.getId(), "lisi"); //领取任务
			/*taskService.complete(task.getId());*/
		}
		
		taskQuery = taskService.createTaskQuery();
		List<Task> list2 = taskQuery.taskAssignee("lisi").list();
		for (Task task : list2) {
			System.out.println(task.getName());
			taskService.complete(task.getId());
		}
		taskQuery = taskService.createTaskQuery();
		long count2 = taskQuery.taskAssignee("lisi").count();
		System.out.println("lisi领取之后的任务数量："+count2);

	}

	// 包含网关测试
	@Test
	public void testStart03() {
		// 获取ACTIVITI的引擎
		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		// 从引擎当中可以获取到持久层的服务
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// 获取流程定义的服务
		ProcessDefinition processDefinition = processEngine
				.getRepositoryService().createProcessDefinitionQuery()
				.latestVersion().singleResult();
		// 准备流程变量(相当于请假实例参数, 在流程启动就已经存在的数据)
		Map<String, Object> variables = new HashMap<String, Object>();
		// 请假天数小于等于3天
		variables.put("days", 7);
		// variables.put("money", 10000);
		// 请假天数大于3天
		variables.put("money", 10000);
		// 启动流程
		// ProcessInstance processInstance = runtimeService
		// .startProcessInstanceById(processDefinition.getId(), variables);
		// System.out.println("启动的流程实例：" + processInstance.getId());

		// 获取任务服务
		TaskService taskService = processEngine.getTaskService();
		// zhangsan领取任务
		List<Task> list = taskService.createTaskQuery().taskAssignee("lisi")
				.list();

		for (Task task : list) {
			// 任务的名称
			System.out.println(task.getName());
			taskService.complete(task.getId());
		}

		// HistoricProcessInstance historicProcessInstance =
		// processEngine.getHistoryService()
		// .createHistoricProcessInstanceQuery()
		// .processInstanceId(processInstance.getId())
		// .finished()
		// .singleResult();

		// System.out.println("流程是否完成 = "+(historicProcessInstance!=null));

	}

	// 并行网关测试
	@Test
	public void testStart02() {
		// 获取ACTIVITI的引擎
		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		// 从引擎当中可以获取到持久层的服务
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// 获取流程定义的服务
		ProcessDefinition processDefinition = processEngine
				.getRepositoryService().createProcessDefinitionQuery()
				.latestVersion().singleResult();
		// 准备流程变量(相当于请假实例参数, 在流程启动就已经存在的数据)
		// Map<String,Object> variables = new HashMap<String,Object>();
		// 请假天数小于等于3天
		// variables.put("days", 3);
		// variables.put("money", 10000);
		// 请假天数大于3天
		// variables.put("days", 4);
		// 启动流程
		// ProcessInstance processInstance = runtimeService
		// .startProcessInstanceById(processDefinition.getId());
		// System.out.println("启动的流程实例：" + processInstance.getId());

		// 获取任务服务
		TaskService taskService = processEngine.getTaskService();
		// zhangsan领取任务
		List<Task> list = taskService.createTaskQuery()
				.taskAssignee("zhangsan").list();

		for (Task task : list) {
			// 任务的名称
			System.out.println(task.getName());
			taskService.complete(task.getId());
		}

		// HistoricProcessInstance historicProcessInstance =
		// processEngine.getHistoryService()
		// .createHistoricProcessInstanceQuery()
		// .processInstanceId(processInstance.getId())
		// .finished()
		// .singleResult();

		// System.out.println("流程是否完成 = "+(historicProcessInstance!=null));

	}

	// 排他网关测试
	@Test
	public void testStart01() {
		// 获取ACTIVITI的引擎
		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		// 从引擎当中可以获取到持久层的服务
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// 获取流程定义的服务
		ProcessDefinition processDefinition = processEngine
				.getRepositoryService().createProcessDefinitionQuery()
				.latestVersion().singleResult();
		// 准备流程变量(相当于请假实例参数, 在流程启动就已经存在的数据)
		Map<String, Object> variables = new HashMap<String, Object>();
		// 请假天数小于等于3天
		// variables.put("days", 3);
		// 请假天数大于3天
		variables.put("days", 4);
		// 启动流程
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(processDefinition.getId(), variables);
		System.out.println("启动的流程实例：" + processInstance.getId());

		// 获取任务服务
		TaskService taskService = processEngine.getTaskService();
		// zhangsan领取任务
		List<Task> list = taskService.createTaskQuery()
				.taskAssignee("zhangsan").list();

		for (Task task : list) {
			// 任务的名称
			System.out.println(task.getName());
			taskService.complete(task.getId());
		}
	}

	/**
	 * 创建框架中所需的表结构(启动引擎创建需要的表结构)
	 */
	@Test
	public void testCreateTable() {

		// 数据库当中已经生成了ACTIVITI框架的表, 那么再次运行就不会创建表结构
		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		// processEngine=org.activiti.engine.impl.ProcessEngineImpl@8ff58d4
		System.out.println("processEngine=" + processEngine);
	}

	/**
	 * 将"规划"好的流程图部署到对应的表结构当中
	 */
	@Test
	public void testDeploy() {
		// 获取ACTIVITI的引擎
		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		// 从引擎当中可以获取到持久层的服务
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		// 将流程图对应的MyProcess.bpmn部署到数据库当中(联级调用)
		Deployment deploy = repositoryService.createDeployment()
				.addClasspathResource("auth.bpmn").deploy();
		System.out.println(deploy);
	}

	/**
	 * 启动流程实例: 即开始执行该流程, 从流程起点开始执行
	 */
	@Test
	public void testStart() {
		// 获取ACTIVITI的引擎
		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		// 从引擎当中可以获取到持久层的服务
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		// 查询流程定义
		// myProcess可以从流程图对应的文件中查询到
		// myProcess值与流程图的名称没有关系(委托人可以通过该值查询到所有的当前的所有任务)
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey("myProcess").latestVersion()
				.singleResult();
		// ProcessDefinitionEntity[myProcess:1:4]
		System.out.println(processDefinition);

		// 运行启动流程实例
		/*
		 * ProcessInstance[201] act_hi_procinst : 历史流程实例表 保存了流程实例的信息
		 * act_hi_taskinst : 历史任务实例表 保存了流程任务的相关信息 act_hi_actinst:历史节点表
		 * 保存了流程执行的节点顺序 act_ru_execution : 运行时流程执行实例表
		 * 保存了当前流程执行的节点数据,流程开始会自动完成,直接执行第一个任务 act_ru_task : 运行时任务节点表
		 * 保存当前流程执行的任务数据
		 */

		// 启动该流程
		// 获取到运行流程的服务
		RuntimeService runtimeService = processEngine.getRuntimeService();

		// 调用相应的方法启动流程
		ProcessInstance startProcessInstanceById = runtimeService
				.startProcessInstanceById(processDefinition.getId());

		// 打印启动的流程
		// ProcessInstance[101]
		System.out.println(startProcessInstanceById);

	}

	/**
	 * 流程开始执行, 按照流程节点完成每个节点上应该完成的任务
	 */
	@Test
	public void testTask() {

		ProcessEngine processEngine = (ProcessEngine) ctx
				.getBean("processEngine");
		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		// 查询流程定义
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey("myProcess").latestVersion()
				.singleResult();
		// 获取到流程的ID
		System.out.println(processDefinition.getId());

		// 从引擎当中获取到任务的服务
		TaskService taskService = processEngine.getTaskService();

		// 查询对应的任务列表
		TaskQuery createTaskQuery = taskService.createTaskQuery();
		// 任务记录数, 对应的就是任务表当中的记录数(如果获取到List同样也可以通过集合.size()获取到任务数)
		long count = createTaskQuery.count();
		System.out.println("当前的任务数==" + count);

		// 通过委托的人员来获取该人员的任务数
		TaskQuery taskAssigneeLike = createTaskQuery
				.taskAssigneeLike("zhangsan");
		List<Task> list1 = taskAssigneeLike.list();
		for (Task task : list1) {
			System.out.println("zhangsan的任务==" + task.getName());
		}
		System.out.println("=============================================");
		// 通过委托的人员来获取该人员的任务数(此时"张三"的任务还没有完成, "李四"的任务为空)
		TaskQuery taskAssignee = createTaskQuery.taskAssigneeLike("lisi");
		List<Task> list2 = taskAssignee.list();
		for (Task task : list2) {
			System.out.println("lisi的任务==" + task.getName());
		}

		// "张三"完成任务
		for (Task task : list1) {
			System.out.println("zhangsan的任务=" + task.getName());
			// 完成任务
			taskService.complete(task.getId());
		}

		// 通过委托的人员来获取该人员的任务数
		TaskQuery taskAssigneeLike1 = createTaskQuery
				.taskAssigneeLike("zhangsan");
		List<Task> list3 = taskAssigneeLike1.list();
		for (Task task : list3) {
			System.out.println("zhangsan的任务==" + task.getName());
		}
		System.out.println("=============================================");
		// 通过委托的人员来获取该人员的任务数(此时"张三"的任务完成, "李四"有任务)
		TaskQuery taskAssignee1 = createTaskQuery.taskAssigneeLike("lisi");
		List<Task> list4 = taskAssignee1.list();
		for (Task task : list4) {
			System.out.println("lisi的任务==" + task.getName());
		}

		// "李四"完成任务
		for (Task task : list4) {
			System.out.println("zhangsan的任务=" + task.getName());
			// 完成任务
			taskService.complete(task.getId());
		}

		// 通过委托的人员来获取该人员的任务数
		TaskQuery taskAssigneeLike2 = createTaskQuery
				.taskAssigneeLike("zhangsan");
		List<Task> list5 = taskAssigneeLike2.list();
		for (Task task : list5) {
			System.out.println("zhangsan的任务==" + task.getName());
		}
		System.out.println("=============================================");
		// 通过委托的人员来获取该人员的任务数(此时"张三"的任务完成, "李四"有任务)
		TaskQuery taskAssignee2 = createTaskQuery.taskAssigneeLike("lisi");
		List<Task> list6 = taskAssignee2.list();
		for (Task task : list6) {
			System.out.println("lisi的任务==" + task.getName());
		}
	}

	// 当李四完成任务了, 同样流程也就完成了
}
