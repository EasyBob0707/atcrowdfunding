package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.dao.TypeDao;
import com.atguigu.atcrowdfunding.service.TypeService;
import com.atguigu.atcrowdfunding.utils.Page;

/**
 * 查询Type类的分页
 * @Author Alison
 * @Date 2017年7月11日 上午9:28:12   
 * @Version V1.0
 */
@Service
public class TypeServiceImpl implements TypeService{
	@Autowired
	private TypeDao typeDao;

	@Override
	public Page<Type> queryForPage(Map<String, Object> paramMap) {
		
		Page<Type> page = new Page<Type>();
		page.setPageno((Integer)paramMap.get("pageno"));//设置当前页码
		page.setPagesize((Integer)paramMap.get("pagesize"));//设置每页显示几条数据
		
		int startIndex = page.getStartIndex();//查询起始
		paramMap.put("startIndex", startIndex);
		
		
		
		int totalsize = typeDao.queryTypeCount(paramMap);
		page.setTotalsize(totalsize);//设置总记录数
		
		List<Type> data = typeDao.queryTypeByPage(paramMap);
		page.setData(data);
		
		return page;
	}

	//根据id号删除t_type表中的数据
	@Override
	public int delete(Integer id) {
		
		return typeDao.delete((Integer)id);
	}

	//根据Id查询一条Type数据
	@Override
	public Type queryOneById(Integer id) {
		
		return typeDao.queryOneById((Integer)id);
	}
	
	
	//修改一条type数据
	@Override
	public int updateTpye(Type type) {
		return typeDao.updateType(type);
	}

	
	//添加一条type数据
	@Override
	public int insertType(Type type) {
		return typeDao.insertType(type);
	}

	
	//批量删除
	@Override
	public int deleteBatch(Integer[] id) {
		int totalCount = typeDao.deleteBatch(id);
		if(totalCount != id.length){
			throw new RuntimeException("批量删除失败!");
		}
		return totalCount;
	}
	
	
}
