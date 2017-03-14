package com.feerlaroc.mqasho.schema.tenant.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.feerlaroc.widgets.ReactiveToggleButton;
import com.feerlaroc.widgets.rx.RxToggleButton;

/**
 * Created by root on 2017/03/13.
 */

public class SiteToggleButton extends ReactiveToggleButton {

    private static final String TAG = "SiteToggleButton";

    public SiteToggleButton(Context context) {
        super(context);
    }

    public SiteToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void updateValue(String value) {

        try {

            Integer int_value = 0;

            if(value.toUpperCase().equals("ABC"))
                int_value = 0;
            else if(value.toUpperCase().equals("SIPHAKAMILE"))
                int_value = 1;
            else if(value.toUpperCase().equals("MGANKA"))
                int_value = 2;
            else if(value.toUpperCase().equals("OSLO"))
                int_value = 3;

            RxToggleButton.selected(this).call(int_value);

        } catch (Exception e) {

            Log.e(TAG, getContext().getString(com.feerlaroc.widgets.R.string.str_dbkey_undefined) + " : " + e.getMessage());
        }
    }
}
