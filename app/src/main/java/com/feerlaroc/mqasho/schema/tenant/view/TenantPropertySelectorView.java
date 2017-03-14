package com.feerlaroc.mqasho.schema.tenant.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.feerlaroc.mqasho.R;
import com.feerlaroc.mqasho.common.widget.CustomLinearLayout;
import com.feerlaroc.mqasho.schema.tenant.CustomerEditHandler;
import com.feerlaroc.mqasho.schema.tenant.screen.TenantPropertySelectorScreen;
import com.feerlaroc.widgets.ReactiveTextView;
import com.feerlaroc.widgets.ReactiveWheelView;
import com.feerlaroc.widgets.view.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

public class TenantPropertySelectorView extends CustomLinearLayout<TenantPropertySelectorScreen.Presenter> {

    @Inject
    TenantPropertySelectorScreen.Presenter mPresenter;

    @InjectView(R.id.text_wheel_header) ReactiveTextView mWheelHeader;
    @InjectView(R.id.toggle_site_blocks) BlockToggleButton mBlockSelectorToggleButton;
    @InjectView(R.id.wheel_view_room_number_selector) ReactiveWheelView mRoomNumberSelector;

    public TenantPropertySelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();

        // With an array
        CharSequence[] texts = new CharSequence[]{"A", "B", "C", "D", "E"};
        mBlockSelectorToggleButton.setElements(texts);

        mBlockSelectorToggleButton.setOnValueChangedListener(value ->
                mBlockSelectorToggleButton.getSelectedSubject().onNext(value));

        final Integer[] ROOMS = new Integer[]{1,2,3,4,5,6,};

        List<Integer> old = Arrays.asList(ROOMS);

        List<String> newList = new ArrayList<>(old.size());
        for (Integer myInt : old) {
            newList.add(String.valueOf(myInt));
        }

        mRoomNumberSelector.setOffset(1);
        mRoomNumberSelector.setItems(newList);
        mRoomNumberSelector.setSeletion(3);

    }

    public ReactiveWheelView getRoomNumberSelector(){

        return mRoomNumberSelector;
    }

    public BlockToggleButton getBlockSelectorToggleButton() {

        return mBlockSelectorToggleButton;
    }

    @Override
    public TenantPropertySelectorScreen.Presenter getPresenter() {

        return mPresenter;
    }
}
