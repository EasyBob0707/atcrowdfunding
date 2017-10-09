package com.atguigu.atcrowdfunding.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.utils.Page;

public interface TypeService {
	
	//批量删除type数据
	int deleteBatch(Integer[] id);
	
	//添加一条type数据
	int insertType(Type type);
	
	//根据Id查询一条Type数据
	Type queryOneById(Integer id);
	
	//根据id号删除t_type表中的数据
	int delete(Integer id);

	//查询Type类型的分页
	Page<Type> queryForPage(Map<String, Object> paramMap);

	//更新Type的一条数据
	int updateTpye(Type type);

}
