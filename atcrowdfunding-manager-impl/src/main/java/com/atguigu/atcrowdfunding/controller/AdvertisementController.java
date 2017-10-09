package com.atguigu.atcrowdfunding.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.bean.Manager;
import com.atguigu.atcrowdfunding.service.AdvertisementService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Const;
import com.atguigu.atcrowdfunding.utils.Datas;
import com.atguigu.atcrowdfunding.utils.Page;
import com.atguigu.atcrowdfunding.utils.StringUtil;

/**
 * 广告模块
 * 
 * @Author SUNBO
 * @Date 2017年7月23日 下午4:58:51
 * @Version V1.0
 */
@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;

	/**
	 * 跳转到广告模块的首页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "advertisement/index";
	}

	/**
	 * 跳转到广告的添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "advertisement/add";
	}

	/**
	 * 跳转到广告的编辑页面
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Integer id, Model model, HttpSession session) {

		// 根据主键查询资质信息
		Advertisement advertisement = advertisementService.queryById(id);
		
		String imgPath = "E:\\AdvertiseImg\\";
		
		advertisement.setIconpath(imgPath+advertisement.getIconpath());
		
		model.addAttribute("advertisement", advertisement);
		
		return "advertisement/edit";
	}

	/**
	 * 批量删除广告信息
	 * 
	 * @param ds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/batchDelete")
	public Object batchDelete(Datas ds) {
		AjaxResult result = new AjaxResult();

		try {
			int count = advertisementService.deleteAdvertisements(ds);
			if (count == ds.getDatas().size()) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 单条删除广告信息
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id) {
		AjaxResult result = new AjaxResult();

		try {
			int count = advertisementService.deleteAdvertisement(id);
			if (count == 1) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 修改广告的信息
	 * 
	 * @param Advertisement
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object update(Advertisement Advertisement) {
		AjaxResult result = new AjaxResult();

		try {
			int count = advertisementService.updateAdvertisement(Advertisement);
			if (count == 1) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 新增资质数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(HttpServletRequest request,
			Advertisement advertisement, HttpSession session) {
		AjaxResult result = new AjaxResult();

		try {
			// 多文件上传请求
			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
           // 请求中获取多个文件
			MultipartFile mfile = mreq.getFile("advpic");
           // 获取到真正的文件名称(上传时的文件名称)
			String name = mfile.getOriginalFilename();// java.jpg
           // 截取扩展名
			String extname = name.substring(name.lastIndexOf(".")); // .jpg
			
			//去除字符串中的-
			String str = UUID.randomUUID().toString().replaceAll("\\-", "");
			
			String iconpath = str + extname; // 232243343.jpg

			//ServletContext servletContext = session.getServletContext();
			//String realpath = servletContext.getRealPath("/pic");
			
			//String path = realpath + "\\adv\\" + iconpath;
			
			//广告图片存储路径
			String imgPath = "E:\\AdvertiseImg\\"+iconpath;
			
			mfile.transferTo(new File(imgPath));

			Manager Manager = (Manager) session
					.getAttribute(Const.LOGIN_MANAGER);
			advertisement.setManagerId(Manager.getId());
			advertisement.setStatus("1");
			advertisement.setIconpath(iconpath);
			
			int count = advertisementService.insertAdvertisement(advertisement);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	// 同步请求处理.
	/*
	 * @RequestMapping("/doAdd") public Object doAdd(HttpServletRequest request,
	 * Advertisement Advertisement ,HttpSession session) {
	 * 
	 * try { MultipartHttpServletRequest mreq =
	 * (MultipartHttpServletRequest)request;
	 * 
	 * MultipartFile mfile = mreq.getFile("advpic");
	 * 
	 * String name = mfile.getOriginalFilename();//java.jpg String extname =
	 * name.substring(name.lastIndexOf(".")); // .jpg
	 * 
	 * String iconpath = UUID.randomUUID().toString()+extname; // 232243343.jpg
	 * 
	 * ServletContext servletContext = session.getServletContext(); String
	 * realpath = servletContext.getRealPath("/pic");
	 * 
	 * String path =realpath+ "\\adv\\"+iconpath;
	 * 
	 * mfile.transferTo(new File(path)); //文件上传.
	 * 
	 * Manager Manager = (Manager)session.getAttribute(Const.LOGIN_Manager);
	 * Advertisement.setManagerid(Manager.getId());
	 * Advertisement.setStatus("1"); Advertisement.setIconpath(iconpath);
	 * 
	 * int count = AdvertisementService.insertAdvertisement(Advertisement);
	 * 
	 * } catch ( Exception e ) { e.printStackTrace();
	 * 
	 * }
	 * 
	 * return "redirect:/Advertisement/index.htm"; }
	 */

	/**
	 * 分页查询资质数据
	 * 
	 * @Advertisement pageno
	 * @Advertisement pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer pageno, Integer pagesize) {
		AjaxResult result = new AjaxResult();

		try {
			// 查询资质数据
			Map<String, Object> AdvertisementMap = new HashMap<String, Object>();
			AdvertisementMap.put("pageno", pageno);
			AdvertisementMap.put("pagesize", pagesize);
			if (StringUtil.isNotEmpty(pagetext)) {
				pagetext = pagetext.replaceAll("%", "\\\\%");
			}
			AdvertisementMap.put("pagetext", pagetext);

			// 分页查询
			Page<Advertisement> page = advertisementService
					.pageQuery(AdvertisementMap);
			result.setPage(page);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}
}
