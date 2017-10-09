package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.service.TypeService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Page;

/**
 * 项目分类
 * @Author Alison
 * @Date 2017年7月11日 上午8:54:10   
 * @Version V1.0
 */
@Controller
@RequestMapping("/project")
public class TypeController {
	@Autowired
	private TypeService typeService;
	
	
	//批量删除
	@ResponseBody
	@RequestMapping(value="/deleteBatch",method=RequestMethod.POST)
	public Object toDeleteBatch(Integer[] id){
		AjaxResult result = new AjaxResult();
		 try {
			int totalCount = typeService.deleteBatch(id);
			result.setSuccess(totalCount==id.length);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("批量删除失败!");
		}
		
		return result;
	}
	
	//真正的添加
	@ResponseBody
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public Object toAdd(Type type){
		AjaxResult result = new AjaxResult();
		
		try {
			int count = typeService.insertType(type);
			result.setSuccess(count==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("添加失败!");
		}
		
		return result;
	}
	
	
	//去到添加type页面
	@RequestMapping("/add")
	public String add(){
		return "project/project_add";
	}
	
	//真正的修改type数据
	@ResponseBody
	@RequestMapping(value="/doEdit",method=RequestMethod.POST)
	public Object doEdit(Type type){
		AjaxResult result = new AjaxResult();
		try {
			int count = typeService.updateTpye(type);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("修改失败!");
		}
		return result;
	}
	
	
	//去到修改页面
	@RequestMapping("/edit")
	public String edit(Integer pageno,Integer id,Map<String,Object> map){
		map.put("type", typeService.queryOneById(id));
		return "project/project_edit";
	}
	
	//删除type的一条数据
	@ResponseBody
	@RequestMapping(value="/dodelete",method=RequestMethod.POST)
	public Object dodelete(Integer id){
		System.out.println(id);
		AjaxResult result = new AjaxResult();
		try {
			int count = typeService.delete(id);
			result.setSuccess(count==1);
			//System.out.println(1/0);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("删除失败!");
		}
		return result;
		
	}
	
	//项目分类 分页查询数据
	@ResponseBody
	@RequestMapping("/reallyProjectType")
	public Object reallyProjectType(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="2")Integer pagesize,
			String queryText){
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			
			paramMap.put("pageno" ,pageno);//当前页码
			paramMap.put("pagesize", pagesize);//每页几条数据
			
			if(queryText != null  &&  queryText.contains("%")){
				queryText = queryText.replaceAll("%", "\\\\%");
			}
			paramMap.put("queryText", queryText);
			
			Page<Type> page = typeService.queryForPage(paramMap);
			
			result.setSuccess(true);
			result.setPage(page);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrorMessage("分页失败!");
		}
		
		return result;
	}
	
	//项目分类,去到项目分类的页面
	@RequestMapping("/toProjectType")
	public String toProjectType(){
		return "project/project_type";
	}
}
