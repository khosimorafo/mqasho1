package com.feerlaroc.widgets.rx;

import android.graphics.Bitmap;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.feerlaroc.widgets.ReactiveCircleImageView;

import rx.functions.Action1;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by root on 2017/03/08.
 */

public class RxImageView {

    /**
     * An action which sets the text property of {@code view} with character sequences.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     */
    @CheckResult
    @NonNull
    public static Action1<? super Bitmap> value(@NonNull final ReactiveCircleImageView view) {

        checkNotNull(view, "view == null");
        return (Action1<Bitmap>) view::setImageBitmap;
    }
}
