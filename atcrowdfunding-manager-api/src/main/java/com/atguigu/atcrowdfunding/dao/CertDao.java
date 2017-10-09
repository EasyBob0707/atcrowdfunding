package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Cert;

/**
 * cert模块dao
 * @Author LvBingYu
 * @Date 2017年7月13日 上午8:43:06   
 * @Version V1.0
 */
public interface CertDao {

	/**
	 * 从数据库中查询所有cert
	 * @return
	 */
	List<Cert> queryCerts(Map<String, Object> paramMap);
	
	/**
	 * 向数据库中保存一个cert
	 * @param cert
	 * @return
	 */
	int addCert(Cert cert);
	
	/**
	 * 更新一条cert
	 * @param cert
	 * @return
	 */
	int updateCert(Cert cert);
	
	/**
	 * 删除一条cert
	 * @param id
	 * @return
	 */
	int deleteCert(Integer id);

	/**
	 * 批量删除数据
	 * @param id
	 * @return
	 */
	int deleteBatchUser(Integer[] id);
	
	/**
	 * 查询流程单中的账户类型
	 * 
	 * @param accttype
	 * @return
	 */
	List<Cert> queryCertByAcctType(String accttype);
}
