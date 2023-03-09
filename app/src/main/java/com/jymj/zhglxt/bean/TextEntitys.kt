package com.jymj.zhglxt.bean

import android.graphics.Color

/**
 * Administrator on 2018/1/17 create this class
 */
class TextEntitys(name: String?, area: String?, dlbm: String?) {
    var name:String?=""
    var area:String?=""//编号
    var dlbm:String?=""//面积
    var titleColor:Int= Color.BLACK
    var valueColor:Int= Color.BLACK
    init {
        if (name!=null){
            this.name=name
        }else{
            this.name=""
        }
        if (area!=null){
            this.area=area
        }else{
            this.area=""
        }
        if (dlbm!=null){
            this.dlbm=dlbm
        }else{
            this.dlbm=""
        }
        titleColor= Color.BLACK
        valueColor= Color.BLACK
    }
    constructor(name: String?, area: String?, dlbm: String?,valueColor:Int) : this(name,area,dlbm,Color.BLACK,valueColor)
    constructor(name: String?, area: String?, dlbm: String?,titleColor:Int,valueColor:Int) : this(name,area,dlbm) {
        this.titleColor=titleColor
        this.valueColor=valueColor
    }

}