package com.jymj.zhglxt.bean

import android.graphics.Color

/**
 * Administrator on 2018/1/17 create this class
 */
class TextViewsEntity(title: String?, value: String?) {
    var title:String?=""
    var value:String?=""
    var titleColor:Int= Color.BLACK
    var valueColor:Int= Color.BLACK
    init {
        if (title!=null){
            this.title=title
        }else{
            this.title=""
        }
        if (value!=null){
            this.value=value
        }else{
            this.value=""
        }
        titleColor= Color.BLACK
        valueColor= Color.BLACK
    }
    constructor(title: String?, value: String?,valueColor:Int) : this(title,value,Color.BLACK,valueColor)
    constructor(title: String?, value: String?,titleColor:Int,valueColor:Int) : this(title,value) {
        this.titleColor=titleColor
        this.valueColor=valueColor
    }

}