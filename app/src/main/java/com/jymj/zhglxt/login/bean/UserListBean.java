package com.jymj.zhglxt.login.bean;

public class UserListBean {
    public String uname;//用户名
    public String unpwd;//密码
    public String xzqmc;//行政区名称

    public UserListBean() {
    }

    public UserListBean(String uname, String unpwd, String xzqmc) {
        this.uname = uname;
        this.unpwd = unpwd;
        this.xzqmc = xzqmc;
    }

    public String getUname() {
        return uname==null?"":uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUnpwd() {
        return unpwd==null?"":unpwd;
    }

    public void setUnpwd(String unpwd) {
        this.unpwd = unpwd;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }
}
