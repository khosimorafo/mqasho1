package com.feerlaroc.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.feerlaroc.widgets.rx.RxImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;

/**
 * Created by root on 2017/03/08.
 */

public class ReactiveCircleImageView extends CircleImageView
    implements Reactive{

    public ReactiveCircleImageView(Context context) {
        super(context);
    }

    public ReactiveCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactiveCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void updateValue(Bitmap bitmap){

        try {

            RxImageView.value(this).call(bitmap);
        } catch (Exception e) {

        }
    }

    @Override
    public void subscribe(Observable observable) {

    }
}
