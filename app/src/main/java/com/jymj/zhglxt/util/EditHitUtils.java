package com.jymj.zhglxt.util;

import android.widget.EditText;
import android.widget.TextView;

import com.setsuna.common.commonutils.ToastUtils;

import java.math.BigDecimal;

public class EditHitUtils {

    public static BigDecimal getSum(EditText... eds){
        BigDecimal bigDecimal = BigDecimal.ZERO;
        for (int i = 0; i < eds.length; i++) {
            EditText editText = eds[i];
            if (!editText.getText().toString().equals("")){
                try {
                    bigDecimal = bigDecimal.add(new BigDecimal(editText.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bigDecimal;
    }

    public static boolean setEditToast(TextView... eds){
        boolean isEdit = false;
        for (int i = 0; i < eds.length; i++) {
            TextView edt = eds[i];
            if (edt.getText().toString().equals("")){
                ToastUtils.showShort(edt.getHint().toString());
                isEdit = true;
                break;
            }else {
                isEdit = false;
            }
        }
        return isEdit;
    }

}
