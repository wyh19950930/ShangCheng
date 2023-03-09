package com.jymj.zhglxt.bean.daobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 用于测试生成
 * Dao操作数据库的文件
 */
@Entity
public class TestBean {
    @Id
    private Long id;//默认自增
    private String name;//
    @Generated(hash = 1979658847)
    public TestBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 2087637710)
    public TestBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
