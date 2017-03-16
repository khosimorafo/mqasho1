package com.feerlaroc.mqasho.schema.tenant.view;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.widgets.ReactiveTextView;

/**
 * Created by root on 2017/03/15.
 */

public class RoomNumberReactiveTextView extends ReactiveTextView {

    public RoomNumberReactiveTextView(Context context) {
        super(context);
    }

    public RoomNumberReactiveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoomNumberReactiveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void updateValue(String str) {

        String new_str = "Room : " + str;
        super.updateValue(new_str);
    }
}
