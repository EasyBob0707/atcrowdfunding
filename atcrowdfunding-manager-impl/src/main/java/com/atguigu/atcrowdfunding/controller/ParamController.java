package com.atguigu.atcrowdfunding.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Param;
import com.atguigu.atcrowdfunding.service.ParamService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Page;
import com.atguigu.atcrowdfunding.utils.StringUtil;

/**
 * 
 * @Author Ash
 * @Date 2017年7月11日 下午2:10:41
 * @Version V1.0
 */
@RequestMapping("/param")
@Controller
public class ParamController {

	@Autowired
	private ParamService paramService;

	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String toManagerIndex() {
		return "manager/main";
	}
	
	/**
	 * 
	 * @return 跳转到param的首页面
	 */
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String toParamIndex() {
		return "param/param";
	}

	/**
	 * 分页查询数据
	 * 
	 * @param pageno
	 *            页面索引
	 * @param pagesize
	 *            每页显示的记录条数
	 * @param queryText
	 *            管理员信息检索条件信息
	 * @param map
	 *            传递数据
	 * @return 返回JSON数据
	 */
	//带条件的分页查询
	@ResponseBody
	@RequestMapping(value = "/doParam", method = RequestMethod.POST)
	public Object doindex(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "2") Integer pagesize,
			String queryText, Map<String, Object> map) {

		AjaxResult result = new AjaxResult();

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>(); // VO,DTO
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			
			//判断检索的信息条件是否为空(检索条件当中是否存在有%等特殊的符号)
			if(StringUtil.isNotEmpty(queryText) && queryText.contains("%")){
				queryText = queryText.replaceAll("%", "\\\\%");
			}
			//将处理完成的检索条件封装到Map当中
			paramMap.put("queryText", queryText);
			
			Page<Param> page = paramService.queryPage(paramMap);

			// 返回分页的信息
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("数据加载数据失败!");
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 跳转到项目分类操作页
	 * 
	 * @return 
	 */
	@RequestMapping("/projectType")
	public String projectType() {
		return "param/project_type";
	}

	
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Integer id,Map<String, Object> map){
		Param param = paramService.getParamById(id);
		
		map.put("param1", param);
		return "param/edit";
	}
	
	@ResponseBody
	@RequestMapping(value="/doEdit",method=RequestMethod.POST)
	public Object doEdit(Param param){
		AjaxResult result = new AjaxResult();
		
		try {
			int count = paramService.updateparam(param);
			
			result.setSuccess(count==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
}
