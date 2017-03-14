package com.feerlaroc.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;

import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;

public class ReactiveEditText extends AppCompatEditText {

    private static final String TAG = "ReactiveEditText";
    private String mKey;

    public ReactiveEditText(Context context) {
        super(context);
    }

    public ReactiveEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactiveEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReactiveEditText setKey(String key){

        mKey = key;
        return this;
    }

    private void updateValue(String str_value){

        try {

            if(str_value.isEmpty())
                str_value = "";

            RxTextView.text(this).call(str_value);

        } catch (Exception e) {

            RxTextView.text(this).call(getContext().getString(R.string.str_dbkey_undefined));
            Log.e(TAG, getContext().getString(R.string.str_dbkey_undefined) + " : " + e.getMessage());
        }
    }

    public void subscribeToString(Observable<String> observable){

        observable.subscribe(this::updateValue);
    }

}
