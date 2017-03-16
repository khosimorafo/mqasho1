package com.feerlaroc.mqasho.schema.tenant.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.feerlaroc.widgets.ReactiveToggleButton;
import com.feerlaroc.widgets.rx.RxToggleButton;

/**
 * Created by root on 2017/03/14.
 */

public class BlockToggleButton extends ReactiveToggleButton {

    private static final String TAG = "BlockToggleButton";

    public BlockToggleButton(Context context) {
        super(context);
    }

    public BlockToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void updateValue(String value) {

        try {

            RxToggleButton.selected(this).call(getIndex(value));

        } catch (Exception e) {

            Log.e(TAG, getContext().getString(com.feerlaroc.widgets.R.string.str_dbkey_undefined) + " : " + e.getMessage());
        }
    }
}
