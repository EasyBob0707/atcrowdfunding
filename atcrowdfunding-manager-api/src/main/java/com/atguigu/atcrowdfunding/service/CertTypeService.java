package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Cert;

public interface CertTypeService {

	/**
	 * 查询所有的资质与账号类型的关系(资质与账号的中间表)
	 * 
	 * @return
	 */
	List<Cert> queryAllCert();

	/**
	 * 通过账号下对应的资质进行回显的操作
	 * 
	 * @return
	 */
	List<Map<String, Object>> queryCertAcctType();

	/**
	 * 添加最新勾选的资质
	 * 
	 * @param certid
	 * @param accttype
	 * @return
	 */
	int insertCertType(Integer certid, String accttype);

	/**
	 * 删除未勾选的资质
	 * 
	 * @param certid
	 * @param accttype
	 * @return
	 */
	int deleteCertType(Integer certid, String accttype);

}
