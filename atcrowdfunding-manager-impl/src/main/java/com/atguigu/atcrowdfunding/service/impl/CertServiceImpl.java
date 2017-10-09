package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.dao.CertDao;
import com.atguigu.atcrowdfunding.service.CertService;

@Service
public class CertServiceImpl implements CertService {
	
	@Autowired
	private CertDao certDao;

	@Override
	public List<Cert> queryCerts(Map<String, Object> paramMap) {
		return certDao.queryCerts(paramMap);
	}

	@Override
	public int addCert(Cert cert) {
		return certDao.addCert(cert);
	}

	@Override
	public int updateCert(Cert cert) {
		return certDao.updateCert(cert);
	}

	@Override
	public int deleteCert(Integer id) {
		return certDao.deleteCert(id);
	}

	@Override
	public int deleteBatchUser(Integer[] id) {
		return certDao.deleteBatchUser(id);
	}

	@Override
	public List<Cert> queryCertByAcctType(String accttype) {
		return certDao.queryCertByAcctType(accttype);
	}
	
}
