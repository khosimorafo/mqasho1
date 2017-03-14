package com.feerlaroc.mqasho.schema.tenant.screen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.feerlaroc.mqasho.ActivityModule;
import com.feerlaroc.mqasho.R;
import com.feerlaroc.mqasho.common.flow.Layout;
import com.feerlaroc.mqasho.common.mortarscreen.WithModule;
import com.feerlaroc.mqasho.schema.Constants;
import com.feerlaroc.mqasho.schema.EntityEditScreen;
import com.feerlaroc.mqasho.schema.tenant.CustomerEditHandler;
import com.feerlaroc.mqasho.schema.tenant.CustomerObservable;
import com.feerlaroc.mqasho.schema.tenant.view.CustomerEditView;
import com.feerlaroc.mqasho.schema.tenant.view.TenantPropertySelectorView;
import com.feerlaroc.widgets.ReactiveWheelView;
import com.feerlaroc.zoho.utils.DialogHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import flow.path.Path;
import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

@Layout(R.layout.layout_customer_edit)
@WithModule(CustomerEditScreen.Module.class)
public class CustomerEditScreen extends Path{

    private static final String TAG = "CustomerEditScreen";

    static EntityEditScreen.EditMode mEditMode;

    @dagger.Module(injects = {CustomerEditView.class, TenantPropertySelectorView.class}, addsTo = ActivityModule.class)
    public class Module {
    }

    public CustomerEditScreen(){

        mEditMode = EntityEditScreen.EditMode.NEW;
    }

    public CustomerEditScreen(EntityEditScreen.EditMode mode){

        mEditMode = mode;
    }

    @Singleton
    public static class Presenter extends EntityEditScreen<CustomerEditView> {

        static PublishSubject<Boolean> mCustomerCreatedResult = PublishSubject.create();

        CustomerEditHandler mEditHandler = null;
        String mSelectedBlock = null;

        TenantPropertySelectorView tpsv = null;

        @Inject
        public Presenter() {}

        @Override
        protected void onLoad(Bundle savedInstanceState) {

            super.onLoad(savedInstanceState);

            mEditHandler = new CustomerEditHandler(getView());

            getView().getSaveButton().setEnabled(false);
            getView().getSaveButton().setTextColor(Color.LTGRAY);

            navigateFromView(mEditHandler.getResultSubject());

            if (mEditMode == EditMode.UPDATE) {

                updateViewValues();
            }
        }

        @Override
        public void onSaveItem() {

            DialogHelper.showProgressDlg(getView().getContext(), "Saving Tenant...");
            mEditHandler.create();
        }

        private void navigateFromView(Observable<Boolean> observable){

            observable.subscribe(aBoolean -> {

                if(aBoolean) {

                    Flow.get(getView()).set(new CustomerDisplayScreen());
                }
                else{
                    Flow.get(getView()).set(new CustomerGridScreen());}
            });
        }

        @Override
        protected void updateViewValues() {

            getView().getTenantName().setKey(Constants.CUSTOMERSCHEMA.NAME)
                    .subscribeToString(CustomerObservable.getCustomerNameSubject());

            getView().getTenantZAID().setKey(Constants.CUSTOMERSCHEMA.ZAID)
                    .subscribeToString(CustomerObservable.getCustomerZAIDSubject());

            getView().getTenantMobile().setKey(Constants.CUSTOMERSCHEMA.TELEPHONE)
                    .subscribeToString(CustomerObservable.getCustomerMobileSubject());

            getView().getTenantTelephone().setKey(Constants.CUSTOMERSCHEMA.MOBILE)
                    .subscribeToString(CustomerObservable.getCustomerTelephoneSubject());

            getView().getGenderToggleButton()
                    .subscribeTo(CustomerObservable.getCustomerGenderSubject());

            getView().getSiteToggleButton()
                    .subscribeTo(CustomerObservable.getCustomerSiteSubject());
        }

        public void openBlockSelector() {

            tpsv = getView().getTenantPropertySelectorView();
            tpsv.getBlockSelectorToggleButton().getSelectedSubject().subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer i) {

                    mEditHandler.getCustomerBlockSubject().onNext(i);
                    mSelectedBlock = tpsv.getBlockSelectorToggleButton().getTextAtChild(i);
                }
            });

            new AlertDialog.Builder(getView().getContext())
                    //.setTitle("WheelView in Dialog")
                    .setView(tpsv)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            completeRoomSelect();
                        }
                    })
                    .setNegativeButton("CANCEL", null)
                    .show();
        }

        public void completeRoomSelect(){

            ReactiveWheelView rwv = tpsv.getRoomNumberSelector();
            mEditHandler.getCustomerRoomSubject().onNext(mSelectedBlock + "-" + rwv.getSeletedItem());
        }
    }
}
