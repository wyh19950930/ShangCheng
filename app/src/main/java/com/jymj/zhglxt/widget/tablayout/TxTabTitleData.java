package com.jymj.zhglxt.widget.tablayout;

import com.setsuna.common.baseapp.AppCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojing on 2017/12/11.
 */

public class TxTabTitleData {

    public List<List<String>> getTitleList(){
        List<List<String>> list = new ArrayList<>();
        List<String> tab1List1 = new ArrayList<>();
//        tab1List1.add("土地利用");
        /*if (!AppCache.getInstance().getCode().equals("100110")){
            tab1List1.add("土地利用2009");
        }*/
        tab1List1.add("土地利用");//土地利用2018
        /*if (!AppCache.getInstance().getCode().equals("110111009022")&&!AppCache.getInstance().getCode().equals("100110")){
            tab1List1.add("土地利用2020");
        }*/
        list.add(tab1List1);

        List<String> tab1List2 = new ArrayList<>();
        tab1List2.add("三块田");
        list.add(tab1List2);

        List<String> tab1List3 = new ArrayList<>();
        tab1List3.add("土地权属");
        list.add(tab1List3);

        List<String> tab1List4 = new ArrayList<>();
        if (!AppCache.getInstance().getCode().equals("110111009022")){
            tab1List4.add("空间规划");
        }else {
            tab1List4.add("村庄规划");
        }
        list.add(tab1List4);

        return list;
    }
}
