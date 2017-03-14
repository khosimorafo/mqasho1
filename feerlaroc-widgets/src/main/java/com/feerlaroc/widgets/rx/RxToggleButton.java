package com.feerlaroc.widgets.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.feerlaroc.widgets.ReactiveToggleButton;
import com.feerlaroc.widgets.ReactiveWheelView;

import rx.functions.Action1;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by root on 2017/03/06.
 */

public class RxToggleButton {

    /**
     * An action which sets the text property of {@code view} with character sequences.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult
    @NonNull
    public static Action1<? super Integer> value(@NonNull final ReactiveToggleButton view) {

        checkNotNull(view, "view == null");
        return (Action1<Integer>) view::setValue;
    }

    @CheckResult
    @NonNull
    public static Action1<? super Integer> selected(@NonNull final ReactiveToggleButton view) {

        checkNotNull(view, "view == null");


        return (Action1<Integer>) view::setValue;
    }
}
