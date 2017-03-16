package com.feerlaroc.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.feerlaroc.widgets.rx.RxToggleButton;
import com.feerlaroc.widgets.view.FeerlarocToggleButton;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public abstract class ReactiveToggleButton extends FeerlarocToggleButton {

    static BehaviorSubject<Integer> mSelectedSubject = BehaviorSubject.create();

    List<String> mElements = new ArrayList<>();

    private static final String TAG = "ReactiveToggleButton";
    private String mKey;

    public ReactiveToggleButton(Context context) {
        super(context);
    }

    public ReactiveToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactiveToggleButton setKey(String key){

        mKey = key;
        return this;
    }

    public abstract void updateValue(String value);

    public void subscribeTo(Observable<String> observable){

        observable.subscribe(this::updateValue);
    }

/*

    @Override
    public void setElements(List<?> elements) {
        super.setElements(elements);

        mElements = new ArrayList<>();
        mElements = (List<String>) elements;
    }

    @Override
    public void setElements(CharSequence[] elements) {
        super.setElements(elements);

        mElements = new ArrayList<>();
        for(CharSequence ch: elements){

            mElements.add(ch.toString());
        }
    }
*/

    @Override
    public void setValue(int position) {
        super.setValue(position);

        mSelectedSubject.onNext(position);
    }

    public BehaviorSubject<Integer> getSelectedSubject(){

        return mSelectedSubject;
    }

    public String getTextAtChild(int index){

        Button btn = (Button) getChildAt(index);
        return  (String) btn.getText();
    }
}
