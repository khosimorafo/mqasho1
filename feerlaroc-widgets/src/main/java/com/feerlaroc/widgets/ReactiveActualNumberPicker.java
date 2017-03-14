package com.feerlaroc.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.widgets.numberpicker.ActualNumberPicker;
import com.feerlaroc.widgets.rx.RxActualNumberPicker;

import rx.Observable;

public class ReactiveActualNumberPicker extends ActualNumberPicker {

    String TAG = "ReactiveActualNumberPicker";
    String mKey;

    public ReactiveActualNumberPicker(Context context) {
        super(context);
    }

    public ReactiveActualNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactiveActualNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReactiveActualNumberPicker setKey(String key){

        mKey = key;
        return this;
    }

    private void updateValue(Double amt){

        RxActualNumberPicker.value(this).call(amt);
    }

    public void setValue(Double number) {

        int val = number.intValue();
        setValue(val);
        invalidate();
    }

    public void subscribeTo(Observable<Double> observable){

        observable.subscribe(this::updateValue);
    }
}
