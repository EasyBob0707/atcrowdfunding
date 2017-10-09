package com.atguigu.atcrowdfunding.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.service.CertTypeService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;

@RequestMapping("type")
@Controller
public class CertTypeController {

	@Autowired
	private CertTypeService certTypeService;

	/**
	 * 跳转到资质分类页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {

		List<Cert> certList = certTypeService.queryAllCert();
		map.put("certList", certList);
		
		// 用于复选框回显
		List<Map<String, Object>> certAccttypeList = certTypeService.queryCertAcctType();
		map.put("certAccttypeList", certAccttypeList);

		return "certtype/index";
	}
	
	/**
	 * 添加新勾选的资质
	 * 
	 * @param certid
	 * @param accttype
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertCertType")
	public Object insertCertType(Integer certid,String accttype) {
		AjaxResult result = new AjaxResult();
		
		try {
			int count = certTypeService.insertCertType(certid,accttype);
			
			result.setSuccess(count==1);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 删除资质的未勾选项
	 * 
	 * @param certid
	 * @param accttype
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteCertType")
	public Object deleteCertType(Integer certid,String accttype) {
		AjaxResult result = new AjaxResult();
		
		try {
			int count = certTypeService.deleteCertType(certid,accttype);
			
			result.setSuccess(count==1);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
