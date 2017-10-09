package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by SunBo on 2017-07-27.
 */
public interface AuthCertDao {

    /**
     * 获取会员上传的资质信息
     *
     * @param memberid
     * @return
     */
    List<Map<String,Object>> queryAuthCertInfo(Integer memberid);
}
