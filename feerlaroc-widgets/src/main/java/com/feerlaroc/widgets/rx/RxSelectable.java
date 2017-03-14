package com.feerlaroc.widgets.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.feerlaroc.widgets.ReactiveWheelView;

import java.util.List;

import rx.functions.Action1;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by root on 2017/03/11.
 */

public class RxSelectable {

    @CheckResult
    @NonNull
    public static Action1<? super List> items(@NonNull final ReactiveWheelView view) {

        checkNotNull(view, "view == null");
        return (Action1<List>) view::setItems;
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> selected(@NonNull final ReactiveWheelView view) {

        checkNotNull(view, "view == null");
        return (Action1<Integer>) view::setSeletion;
    }
}
