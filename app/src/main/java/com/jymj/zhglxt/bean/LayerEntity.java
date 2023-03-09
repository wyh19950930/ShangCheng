package com.jymj.zhglxt.bean;

import com.amap.api.maps2d.model.TileOverlayOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator on 2017/11/30 create this class
 */

public class LayerEntity {
    private String name;
    private TileOverlayOptions opt;
    private TileOverlayOptions[] optList;
    private List<LayerEntity> layerEntityList;
    private boolean isShow=true;
    private boolean isCheck=true;
    private boolean isType=true;//区分产业发展，市政公服等
    private int colorTl;
    private int num;

    public LayerEntity(String name, TileOverlayOptions opt, boolean isShow, boolean isCheck, boolean isType) {
        this.name = name;
        this.opt = opt;
        this.isShow = isShow;
        this.isCheck = isCheck;
        this.isType = isType;
    }

    public LayerEntity(String name, TileOverlayOptions opt) {
        this.name = name;
        this.opt = opt;
    }

    public LayerEntity(String name, TileOverlayOptions opt, boolean isShow, boolean isCheck) {
        this.name = name;
        this.opt = opt;
        this.isShow = isShow;
        this.isCheck = isCheck;
    }

    public LayerEntity(String name, TileOverlayOptions opt, boolean isCheck, List<LayerEntity> layerEntityList) {
        this.name = name;
        this.opt = opt;
        this.isCheck = isCheck;
        this.layerEntityList = layerEntityList;
    }
    public LayerEntity(String name, TileOverlayOptions opt, boolean isCheck) {
        this.name = name;
        this.opt = opt;
        this.isCheck = isCheck;
        this.num = num;
    }
    public LayerEntity(String name, TileOverlayOptions opt, boolean isCheck, int num) {
        this.name = name;
        this.opt = opt;
        this.isCheck = isCheck;
        this.num = num;
    }
    /*public LayerEntity(String name, TileOverlayOptions[] optList, boolean isCheck) {
        this.name = name;
        this.optList = optList;
        this.isCheck = isCheck;
    }*/

    public LayerEntity(String name, int colorTl) {
        this.name = name;
        this.colorTl = colorTl;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TileOverlayOptions getOpt() {
        return opt;
    }

    public void setOpt(TileOverlayOptions opt) {
        this.opt = opt;
    }

    public boolean isShow() {
        return !(getName().equals("工业大院") ) && isShow;//|| getName().equals("村界")  || getName().equals("镇界")
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isType() {
        return isType;
    }

    public void setType(boolean type) {
        isType = type;
    }

    public List<LayerEntity> getLayerEntityList() {
        if (layerEntityList==null){
            layerEntityList = new ArrayList<>();
        }
        return layerEntityList;
    }

    public void setLayerEntityList(List<LayerEntity> layerEntityList) {
        this.layerEntityList = layerEntityList;
    }

    public int getColorTl() {
        return colorTl;
    }

    public void setColorTl(int colorTl) {
        this.colorTl = colorTl;
    }

    public TileOverlayOptions[] getOptList() {
        return optList;
    }

    public void setOptList(TileOverlayOptions[] optList) {
        this.optList = optList;
    }
}
