package com.atguigu.atcrowdfunding.utils;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.atcrowdfunding.bean.Manager;
import com.atguigu.atcrowdfunding.bean.MemberCert;

/**
 * 数据传输类
 *
 * @Author SUNBO
 * @Date 2017年7月12日 下午4:10:47
 * @Version V1.0
 */
public class Datas {

    // 用多个对象传递参数
    private List<Manager> datas = new ArrayList<Manager>();

    // 传递多个ID
    private List<Integer> ids = new ArrayList<Integer>();

    // 用于接受资质文件(图片)替换老师的中间类型CertImg
    private List<MemberCert> certimgs;

    public List<Manager> getDatas() {
        return datas;
    }

    public void setDatas(List<Manager> datas) {
        this.datas = datas;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<MemberCert> getCertimgs() {
        return certimgs;
    }

    public void setCertimgs(List<MemberCert> certimgs) {
        this.certimgs = certimgs;
    }
}
