package com.atguigu.atcrowdfunding.service;


import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Param;
import com.atguigu.atcrowdfunding.utils.Page;

public interface ParamService {
	

	/**
	 * 查询分页信息的接口
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 返回分页数据
	 */
	public Page<Param> queryPage(Map<String, Object> paramMap);
	

	Param getParamById(Integer id);

	int updateparam(Param param);

	


}
