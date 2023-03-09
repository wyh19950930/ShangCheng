package com.jymj.zhglxt.widget

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.TextEntitys
import com.jymj.zhglxt.bean.TextViewsEntity

/**
 * Administrator on 2018/1/17 create this class
 */
class TextViews : RelativeLayout {
    var list: RecyclerView?=null
    private var onClickLinera: OnClicikLinear? =null

    fun setOnClick(onClickLinera: OnClicikLinear){
        this.onClickLinera=onClickLinera
    }
    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}
    /*
    *setLayoutManager before setDatas
     */
    fun setLayoutManager(layoutManager:RecyclerView.LayoutManager){
        list?.layoutManager=layoutManager
    }
    fun setDatas(datas:ArrayList<TextViewsEntity>){
        this.removeAllViews()
        list= LayoutInflater.from(context).inflate(R.layout.textviews_layout,null) as RecyclerView
        this.addView(list,LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT))
        list?.layoutManager= GridLayoutManager(context, 2)
        list?.adapter=object :BaseQuickAdapter<TextViewsEntity,BaseViewHolder>(R.layout.item_textviews,datas){

            override fun convert(helper: BaseViewHolder?, item: TextViewsEntity?) {
                helper?.setText(R.id.tvTextViews,item?.title)
                        ?.setTextColor(R.id.tvTextViews,item?.titleColor!!)
                        ?.setText(R.id.tvValueTextViews,item.value)
                        ?.setTextColor(R.id.tvValueTextViews,item.valueColor)
                val tvTextViews = helper!!.getView<TextView>(R.id.tvTextViews)
                tvTextViews.setTextColor(Color.BLACK)

                if (item!!.title!!.contains("联系方式")){
                    helper!!.setTextColor(R.id.tvValueTextViews,Color.parseColor("#0642BC"))
                    if (onClickLinera!=null){
                        if (!item.value.equals("")){
                            helper!!.itemView.setOnClickListener {
                                onClickLinera!!.onClick(item.value!!)
                            }
                        }
                    }
                }
            }
        }
    }


    fun setDatas(datas:ArrayList<TextViewsEntity>,layout:Int){
        this.removeAllViews()
        list= LayoutInflater.from(context).inflate(R.layout.textviews_layout,null) as RecyclerView
        this.addView(list,android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT))
        list?.layoutManager= GridLayoutManager(context, 2)
        list?.adapter=object :BaseQuickAdapter<TextViewsEntity,BaseViewHolder>(layout,datas){

            override fun convert(helper: BaseViewHolder?, item: TextViewsEntity?) {
                helper?.setText(R.id.tvTextViews,item?.title)
                        ?.setTextColor(R.id.tvTextViews,item?.titleColor!!)
                        ?.setText(R.id.tvValueTextViews,item.value)
                        ?.setTextColor(R.id.tvValueTextViews,item.valueColor)
            }
        }
    }
    public interface OnClicikLinear{
        fun onClick(title:String)
    }
}