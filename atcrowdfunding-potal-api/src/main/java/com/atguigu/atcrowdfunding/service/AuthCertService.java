package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

/**
 * Created by SunBo on 2017-07-27.
 */
public interface AuthCertService {

    /**
     * 获取会员资质信息
     *
     * @return
     */
    List<Map<String,Object>> queryAuthCertInfo(Integer memberid);
}
