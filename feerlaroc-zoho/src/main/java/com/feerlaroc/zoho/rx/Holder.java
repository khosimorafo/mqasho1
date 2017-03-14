package com.feerlaroc.zoho.rx;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    SelectedItemListener mListener;

    public Holder(View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View view) {

        mListener.onItemClick(getAdapterPosition());
    }

    public void setListener(SelectedItemListener mListener) {

        this.mListener = mListener;
    }

    public interface SelectedItemListener{

        void onItemClick(int position);
    }
}
