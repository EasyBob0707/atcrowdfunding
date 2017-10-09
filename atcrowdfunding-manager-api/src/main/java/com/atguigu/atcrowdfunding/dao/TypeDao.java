package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Type;

public interface TypeDao {
	
	//批量删除type数据
	int deleteBatch(Integer[] id);
	
	//添加一条type数据
	int insertType(Type type);
	
	//根据id号查询一条数据
	Type queryOneById(int id);
	
	//根据id号删除t_type表中的数据
	int delete(int id);
	
	//查询t_type表中有总记录数
	int queryTypeCount(Map<String,Object> paramMap);
	
	//查询表中数据，分页
	List<Type> queryTypeByPage(Map<String,Object> paramMap);
	
	//修改一条type对象
	int updateType(Type type);
}
