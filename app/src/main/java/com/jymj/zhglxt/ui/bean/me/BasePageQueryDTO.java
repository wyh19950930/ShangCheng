package com.jymj.zhglxt.ui.bean.me;

/**
 * 分页
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-17
 */
public class BasePageQueryDTO {

//    @ApiModelProperty("页码")
    private Integer page;

//    @ApiModelProperty("每页条数")
    private Integer size;

//    @ApiModelProperty("排序方式 1-升序 2-降序")
    private Integer direction;

//    @ApiModelProperty("排序字段 默认创建日期")
    private String properties;

    public Integer getPage() {
        if (this.page != null) {
            return this.page - 1;
        }
        return 0;
    }

    public Integer getSize() {
        if (this.size != null) {
            return this.size;
        }
        return 10;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
