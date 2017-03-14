package com.feerlaroc.widgets.numberpicker.listener;

import com.feerlaroc.widgets.numberpicker.ActualNumberPicker;

/**
 * A listener for {@link ActualNumberPicker} View. Gives information about clicked {@link ActualNumberPicker#mValue}.
 */
public interface OnNumberClickedListener {

    /**
     * Invoked when the value text is clicked on the {@link ActualNumberPicker}. Remember to set this listener to the View.
     *
     * @param value Tha value picker has now, after the change
     */
    void onNumberClicked(int value);
}
