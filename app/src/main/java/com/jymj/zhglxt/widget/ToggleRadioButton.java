package com.jymj.zhglxt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.jymj.zhglxt.R;

import androidx.appcompat.widget.AppCompatRadioButton;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/3/28 17:01
 * radiobutton 取消选中
 */
public class ToggleRadioButton extends AppCompatRadioButton {
    public ToggleRadioButton(Context context) {
        this(context, null);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.radioButtonStyle);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
        if (!isChecked()) {
            ((RadioGroup)getParent()).clearCheck();
        }
    }
}
