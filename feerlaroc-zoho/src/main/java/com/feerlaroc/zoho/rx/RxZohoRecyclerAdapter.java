package com.feerlaroc.zoho.rx;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;


public abstract class RxZohoRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private static final String TAG = "RxZohoRecyclerAdapter";

    protected List<Map<String, Object>> mItems = new ArrayList<>();
    protected int mModelLayout;
    protected Class<VH> mViewHolderClass;

    protected Holder.SelectedItemListener mListener;

    /**
     * Each time the data at the given Firebase location changes, this method will be called for each item that needs
     * to be displayed. The first two arguments correspond to the mLayout and mModelClass given to the constructor of
     * this class. The third argument is the item's position in the list.
     * <p>
     * Your implementation should populate the view using the data contained in the model.
     *
     * @param viewHolder The view to populate
     * @param model      The object containing the data used to populate the view
     * @param position  The position in the list of the view being populated
     */
    abstract protected void populateViewHolder(VH viewHolder, Map<String, Object> model, int position);

    public abstract String getReference();

    public RxZohoRecyclerAdapter(int layout, Class<VH> vhClass,
                                 Holder.SelectedItemListener listener){

        mModelLayout = layout;
        mViewHolderClass = vhClass;
        mListener = listener;
    }

    public void updateItem(Map<String, Object>  snapshot){

        mItems.add(snapshot);
    }

    public Map<String, Object>  getItem(int position){

        return mItems.get(position);
    }

    public List<Map<String, Object>>  getItems(){

        return mItems;
    }

    public void clear(){

        mItems.clear();
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mModelLayout, parent, false);

        try {

            Constructor<VH> constructor = mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {

        populateViewHolder(viewHolder, getItem(position), position);
    }



    public void updateDataset(List<Map<String, Object>> mapList) {

        clear();

        Observable
                .from(mapList)
                .forEach(this::updateItem);

        notifyDataSetChanged();
    }
}
