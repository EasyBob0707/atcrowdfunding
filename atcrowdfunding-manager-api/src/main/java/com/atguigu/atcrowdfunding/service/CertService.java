package com.atguigu.atcrowdfunding.service;
import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Cert;

/**
 * cert模块的功能接口
 * @Author LvBingYu
 * @Date 2017年7月12日 下午8:14:47   
 * @Version V1.0
 */
public interface CertService {

	/**
	 * 查询所有cert
	 * @param paramMap
	 * @return
	 */
	List<Cert> queryCerts(Map<String, Object> paramMap);
	
	/**
	 * 添加cert
	 * @param cert
	 * @return
	 */
	int addCert(Cert cert);
	
	/**
	 * 修改cert
	 * @param cert
	 * @return
	 */
	int updateCert(Cert cert);
	
	/**
	 * 删除单条cert
	 * @param id
	 * @return
	 */
	int deleteCert(Integer id);

	/**
	 * 批量删除cert
	 * @param id    
	 * @return
	 */
	int deleteBatchUser(Integer[] id);
	
	/**
	 * 查询资质的账户类型
	 * 
	 * @param accttype
	 * @return
	 */
	List<Cert> queryCertByAcctType(String accttype);
}
