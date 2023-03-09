package com.jymj.basemessagesystem.ui.views

import android.graphics.Color

/**
 * Administrator on 2018/1/17 create this class
 */
class LegendEntity(title: String?, titleColor:Int?) {
    var title:String?=""
    var titleColor:Int= Color.BLACK
    init {
        if (title!=null){
            this.title=title
        }
        if (titleColor!=null){
            this.titleColor= titleColor
        }
    }
}