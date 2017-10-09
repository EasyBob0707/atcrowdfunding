package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.dao.AuthCertDao;
import com.atguigu.atcrowdfunding.service.AuthCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by SunBo on 2017-07-27.
 */
@Service
public class AuthCertServiceImpl implements AuthCertService {

    @Autowired
    private AuthCertDao authCertDao;

    /**
     * 获取会员上传的资质信息
     *
     * @param memberid
     * @return
     */
    @Override
    public List<Map<String, Object>> queryAuthCertInfo(Integer memberid) {
        return authCertDao.queryAuthCertInfo(memberid);
    }
}
