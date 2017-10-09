package com.atguigu.atcrowdfunding.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Page;

/**
 * 资质管理
 * 
 * @Author SUNBO
 * @Date 2017年7月23日 上午12:56:32
 * @Version V1.0
 */
@RequestMapping("/process")
@Controller
public class ProcessController {

	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 跳转到首页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "process/index";
	}

	/**
	 * 跳转到首页面(流程图)
	 * 
	 * @return
	 */
	@RequestMapping("/showImg")
	public String showImg() {
		return "process/show";
	}

	/**
	 * 显示流程图(相当于下载, 不写头文件信息就可以直接显示)
	 * 
	 * @param id
	 * @param response
	 *            为输出流做准备
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("queryProcDefImg")
	public void queryProcDefImg(String id, HttpServletResponse response) throws IOException {
		//根据流程定义id查询流程定义对象,通过流程对象可以获取部署DEPLOYEMNT_ID和DGRM_RESOUCE_NAME
		//获取数据库流程定义图片
		ProcessDefinition processDefinition = 
				repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(id).singleResult();
		String deploymentId = processDefinition.getDeploymentId();
		String diagramResourceName = processDefinition.getDiagramResourceName();
		
		//输入流
		InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
		
		//输出流
		ServletOutputStream outputStream = response.getOutputStream();
		
		IOUtils.copy(resourceAsStream, outputStream);
		
	}

	/**
	 * 根据id删除流程图
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(String id) {
		AjaxResult result = new AjaxResult();
		try {
			// 删除之前首先要通过流程定义查询到该流程
			ProcessDefinition processDefinition = repositoryService
					.createProcessDefinitionQuery().processDefinitionId(id)
					.singleResult();

			repositoryService.deleteDeployment(
					processDefinition.getDeploymentId(), true); // true表示级联删除数据.
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("流程定义删除失败!");
		}
		return result;
	}

	/**
	 * 部署流程
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deploy")
	public Object deploy(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		try {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			// 获取到表单域中的多文件上传, 根据name获取到上传的文件
			MultipartFile multipartFile = multipartHttpServletRequest
					.getFile("procDefFile");
			// multipartFile.getName(); 指的是文件域的name属性值
			// 获取上传文件的原始名称
			String resourceName = multipartFile.getOriginalFilename();
			// 获取到上传的文件流(写入流)
			InputStream inputStream = multipartFile.getInputStream();
			// 获取到部署相关的类
			// 依据在类路径下的流程图直接进行部署
			// Deployment deploy =
			// repositoryService.createDeployment().addClasspathResource("MyProcess08").deploy();
			// 现在的需求是: 根据上传的流程图进行部署
			repositoryService.createDeployment()
					.addInputStream(resourceName, inputStream).deploy();
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("部署流程失败!");
		}
		return result;
	}

	/**
	 * 去首页面加载数据做分页展示
	 * 
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize) {
		// 返回JSON数据
		AjaxResult result = new AjaxResult();
		// 将activitie的数据放到该集合当中进行返回
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

		try {
			// 从activitie框架当中获取对应的部署信息
			ProcessDefinitionQuery processDefinitionQuery = repositoryService
					.createProcessDefinitionQuery();
			// 查询的结果数据
			List<ProcessDefinition> data = processDefinitionQuery.listPage(
					(pageno - 1) * pagesize, pagesize);

			// 由于activiti框架封装的数据返回json数据格式有问题, 自己进一步处理
			for (ProcessDefinition processDefinition : data) {
				// 以键-值对的形式存放数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", processDefinition.getId());
				map.put("name", processDefinition.getName());
				map.put("key", processDefinition.getKey());
				map.put("version", processDefinition.getVersion());

				maps.add(map);
			}

			Long totalsize = processDefinitionQuery.count();

			Page<Map<String, Object>> page = new Page<Map<String, Object>>(
					pageno, pagesize);
			// 将数据封装到page当中
			page.setData(maps);
			// 分页总记录数
			page.setTotalsize(totalsize.intValue());
			// 将整个的page封装到AjaxResult中
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("流程定义数据加载失败!");
		}

		return result;
	}
}
