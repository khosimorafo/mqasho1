package com.feerlaroc.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.widgets.rx.RxDataBite;
import com.feerlaroc.widgets.view.DataBite;

/**
 * Created by root on 2016/09/07.
 */
public abstract class ReactiveDataBite<T> extends DataBite {

    public abstract void subscribeTo(T observable);

    public ReactiveDataBite(Context context) {

        super(context);
    }

    public ReactiveDataBite(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public ReactiveDataBite(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    public void setValueText(String value){

        RxDataBite.value(this).call(value);
    }

    public void setLabelText(String label){

        RxDataBite.label(this).call(label);
    }
}
