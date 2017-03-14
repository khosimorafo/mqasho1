package com.feerlaroc.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import rx.Observable;

public class ReactiveCurrencyBite extends ReactiveDataBite
    implements Reactive{

    Observable<Double> mObservable;

    public ReactiveCurrencyBite(Context context) {

        super(context);
    }

    public ReactiveCurrencyBite(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public ReactiveCurrencyBite(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    @Override
    public void subscribeTo(Object observable) {

        mObservable = (Observable<Double>) observable;
        mObservable.subscribe(this::updateValue);
    }

    private void updateValue(Double amount){

        //Change this label to local currency
        setValue("R " + amount.toString());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void subscribe(Observable observable) {

    }
}
