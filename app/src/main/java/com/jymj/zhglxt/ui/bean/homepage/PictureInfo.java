package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;

/**
 * 图片信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-02
 */
public class PictureInfo implements Serializable {

    //("图片id")
    private Long pictureId;

    //("图片地址")
    private String url;

    //("图片类型")
    private PictureType type;

    //("商品id")
    private Long mdseId;

    //("规格id")
    private Long stockId;

    //("卡id")
    private Long cardId;

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getUrl() {
        return url == null?"":url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PictureType getType() {
        return type;
    }

    public void setType(PictureType type) {
        this.type = type;
    }

    public Long getMdseId() {
        return mdseId;
    }

    public void setMdseId(Long mdseId) {
        this.mdseId = mdseId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
