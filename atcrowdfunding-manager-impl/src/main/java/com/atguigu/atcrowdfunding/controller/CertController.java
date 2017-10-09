package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.service.CertService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;

/**
 * cert模块功能处理类
 * @Author LvBingYu
 * @Date 2017年7月12日 下午8:04:34   
 * @Version V1.0
 */
@Controller
@RequestMapping("cert")
public class CertController {
	
	@Autowired
	CertService certService;
	
	@RequestMapping("index")
	public String index(){
		return "cert/index";
	}
	
	/**
	 * 将查出cert集合展示到页面
	 * @param queryText
	 * @return
	 */
	@ResponseBody
	@RequestMapping("listCert")
	public Object doCert(String queryText){
		AjaxResult result = new AjaxResult();
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("queryText", queryText);
			List<Cert> certs = certService.queryCerts(paramMap);
			result.setList(certs);
			result.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			result.setErrorMessage("查询失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 转发到添加&修改cert的编辑页面
	 * @return
	 */
	@RequestMapping("edit")
	public String form(){
		return "cert/edit";
	}
	
	/**
	 * 进行cert的添加
	 * @param cert
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCert")
	public Object addCert(Cert cert){
		AjaxResult result = new AjaxResult();
		try {
			int addCert = certService.addCert(cert);
			result.setSuccess(addCert==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("添加失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 进行cert的修改
	 * @param cert
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCert")
	public Object updateCert(Cert cert){
		AjaxResult result = new AjaxResult();
		try {
			int updateCert = certService.updateCert(cert);
			result.setSuccess(updateCert==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("修改失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 删除单条cert
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteCert")
	public Object deleteCert(Integer id){
		AjaxResult result = new AjaxResult();
		try {
			int deleteCert = certService.deleteCert(id);
			result.setSuccess(deleteCert==1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorMessage("删除失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 批量删除cert
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doDeleteBatch",method=RequestMethod.POST)
	public Object doDeleteBatch(Integer[] id){
		AjaxResult result = new AjaxResult();
		try {
			int count = certService.deleteBatchUser(id);
			result.setSuccess(count==id.length);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
