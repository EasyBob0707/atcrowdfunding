package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Param;

/**
 * 
 * @Author Ash
 * @Date 2017年7月11日 下午2:31:02   
 * @Version V1.0
 */
public interface ParamDao {

	

	/**
	 * 查询分页信息的接口
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 返回分页数据
	 */
	List<Param> queryPage(Map<String, Object> paramMap);

	/**
	 * 查询分页记录数的条数
	 * 
	 * @param paramMap
	 *            携带要检索的分页信息
	 * @return 记录条数
	 */
	Integer queryCount(Map<String, Object> paramMap);
	

	/**
	 * 根据id主键,查询参数对象
	 * @param id
	 * @return
	 */
	Param getParamById(Integer id);

	/**
	 * 修改参数
	 * @param user
	 * @return
	 */
	int updateParam(Param param);

	

	
	
}
