package com.feerlaroc.widgets.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.feerlaroc.widgets.ReactiveActualNumberPicker;

import rx.functions.Action1;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by root on 2016/09/03.
 */
public class RxActualNumberPicker {

    /**
     * An action which sets the text property of {@code view} with character sequences.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult
    @NonNull
    public static Action1<? super Double> value(@NonNull final ReactiveActualNumberPicker view) {
        checkNotNull(view, "view == null");

        return (Action1<Double>) view::setValue;
    }
}
