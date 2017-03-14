package com.feerlaroc.utils;

import android.content.res.Resources;

/**
 * Created by root on 2016/05/05.
 */
public class ScreenUtils {

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
