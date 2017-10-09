package com.atguigu.atcrowdfunding.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 表t_member_cert 会员资质表
 *
 * @Author Alison
 * @Date 2017年7月7日 下午8:16:44
 * @Version V1.0
 */
public class MemberCert implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer memberid;//会员ID
    private Integer certid;//资质ID
    private String iconpath;//图片路径
    private MultipartFile multiPartFile;//多文件上传

    public MemberCert() {
        super();
    }

    public MemberCert(Integer id, Integer memberid, Integer certid,
                      String iconpath, MultipartFile multiPartFile) {
        super();
        this.id = id;
        this.memberid = memberid;
        this.certid = certid;
        this.iconpath = iconpath;
        this.multiPartFile = multiPartFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public MultipartFile getMultiPartFile() {
        return multiPartFile;
    }

    public void setMultiPartFile(MultipartFile multiPartFile) {
        this.multiPartFile = multiPartFile;
    }

    @Override
    public String toString() {
        return "MemberCert [id=" + id + ", memberid=" + memberid + ", certid="
                + certid + ", iconpath=" + iconpath + ", multiPartFile=" + multiPartFile + "]";
    }

}
