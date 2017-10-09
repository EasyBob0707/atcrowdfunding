package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.dao.CertTypeDao;
import com.atguigu.atcrowdfunding.service.CertTypeService;

@Service
public class CertTypeServiceImpl implements CertTypeService {

	@Autowired
	private CertTypeDao certTypeDao;
	
	/**
	 * 查询所有的资质与账号类型的关系(资质与账号的中间表)
	 * 
	 * @return
	 */
	@Override
	public List<Cert> queryAllCert() {
		return certTypeDao.queryAllCert();
	}

	/**
	 * 查询该账户类型需要上传的资质
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryCertAcctType() {
		return certTypeDao.queryCertAcctType();
	}

	/**
	 * 添加新勾选的资质
	 * 
	 * @param certid
	 * @param accttype
	 * @return
	 */
	@Override
	public int insertCertType(Integer certid, String accttype) {
		return certTypeDao.insertCertType(certid, accttype);
	}

	/**
	 * 删除资质的未勾选项
	 * 
	 * @param certid
	 * @param accttype
	 * @return
	 */
	@Override
	public int deleteCertType(Integer certid, String accttype) {
		return certTypeDao.deleteCertType(certid, accttype);
	}

}
