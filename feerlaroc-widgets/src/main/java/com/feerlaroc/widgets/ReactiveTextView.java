package com.feerlaroc.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;


public class ReactiveTextView extends AppCompatTextView
    implements Reactive{

    private static final String TAG = "ReactiveTextView";
    private String[] mKey;
    int mVarCount = 0;


    public ReactiveTextView(Context context) {
        super(context);
    }

    public ReactiveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactiveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReactiveTextView setKey(String... key){

        mKey = key;
        mVarCount = mKey.length;
        return this;
    }

    public void updateValue(String str){

        try {

            RxTextView.text(this).call(str);
        } catch (Exception e) {

            RxTextView.text(this).call(getContext().getString(R.string.str_dbkey_undefined));
            Log.e(TAG, getContext().getString(R.string.str_dbkey_undefined) + " : " + e.getMessage());
        }
    }

    public void subscribeTo(Observable<String> observable){

        observable.subscribe(this::updateValue);
    }

    @Override
    public void subscribe(Observable observable) {

    }
}
