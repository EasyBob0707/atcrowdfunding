package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.atcrowdfunding.bean.Cert;

/**
 * 资质管理
 * 
 * @Author SUNBO
 * @Date 2017年7月24日 下午6:43:03
 * @Version V1.0
 */
public interface CertTypeDao {
	
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
	 * @param certid 资质Id
	 * @param accttype 账户类型
	 * @return
	 */
	int insertCertType(@Param("certid")Integer certid, @Param("accttype")String accttype);

	/**
	 * 删除未勾选的资质
	 * 
	 * @param certid 资质Id
	 * @param accttype 账户类型
	 * @return
	 */
	int deleteCertType(@Param("certid")Integer certid, @Param("accttype")String accttype);

}
