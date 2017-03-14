package com.feerlaroc.mqasho.schema.tenant.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.feerlaroc.widgets.ReactiveToggleButton;
import com.feerlaroc.widgets.rx.RxToggleButton;

/**
 * Created by root on 2017/03/13.
 */

public class GenderToggleButton extends ReactiveToggleButton {

    private static final String TAG = "GenderToggleButton";

    public GenderToggleButton(Context context) {
        super(context);
    }

    public GenderToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void updateValue(String value) {

        try {

            Integer int_value = 0;

            if(value.toUpperCase().equals("MR.")||value.toUpperCase().equals("MALE")||value.toUpperCase().equals("MR"))
                int_value = 1;
            else
                int_value = 0;

            RxToggleButton.selected(this).call(int_value);

        } catch (Exception e) {

            Log.e(TAG, getContext().getString(com.feerlaroc.widgets.R.string.str_dbkey_undefined) + " : " + e.getMessage());
        }
    }
}
