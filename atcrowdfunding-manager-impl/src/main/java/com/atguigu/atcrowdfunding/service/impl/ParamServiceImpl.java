package com.atguigu.atcrowdfunding.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Param;
import com.atguigu.atcrowdfunding.dao.ParamDao;
import com.atguigu.atcrowdfunding.service.ParamService;
import com.atguigu.atcrowdfunding.utils.Page;


@Service
public class ParamServiceImpl implements ParamService {

	@Autowired
	private ParamDao paramDao;
	
	/**
	 * 查询分页信息的接口
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 返回分页数据
	 */
	@Override
	public Page<Param> queryPage(Map<String, Object> paramMap) {
		//将数据封装到page中进行返回
		Page<Param> page = 
				new Page<Param>(
						(Integer)paramMap.get("pageno"), 
						(Integer)paramMap.get("pagesize"));
		
		paramMap.put("startIndex", page.getStartIndex());
		
		List<Param> managerList = paramDao.queryPage(paramMap);
		
		page.setData(managerList);
		
		//查询分页总记录数
		Integer totalsize = paramDao.queryCount(paramMap);
		
		page.setTotalsize(totalsize);
		
		return page;
	}

	@Override
	public Param getParamById(Integer id) {
		// TODO Auto-generated method stub
		return paramDao.getParamById(id);
	}

	@Override
	public int updateparam(Param param) {
		// TODO Auto-generated method stub
		return paramDao.updateParam(param);
	}

}
