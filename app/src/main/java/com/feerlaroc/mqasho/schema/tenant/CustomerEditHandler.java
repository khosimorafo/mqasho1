package com.feerlaroc.mqasho.schema.tenant;

import android.graphics.Color;
import android.widget.Toast;

import com.feerlaroc.mqasho.R;
import com.feerlaroc.mqasho.rx.RxHelper;
import com.feerlaroc.mqasho.rx.formvalidation.ValidationResult;
import com.feerlaroc.mqasho.rx.formvalidation.Validations;
import com.feerlaroc.mqasho.schema.Constants;
import com.feerlaroc.mqasho.schema.tenant.view.CustomerEditView;
import com.feerlaroc.zoho.entity.HttpResult;
import com.feerlaroc.zoho.retrofit.exception.ApiException;
import com.feerlaroc.zoho.subscribers.CommonSubscriber;
import com.feerlaroc.zoho.utils.DialogHelper;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func5;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class CustomerEditHandler {

    private CustomerFactory mCustomerFactory = new CustomerFactory().initialize();

    private static PublishSubject<String> mCustomerNameSubject = PublishSubject.create();
    private static PublishSubject<String> mCustomerZAIDSubject = PublishSubject.create();
    private static PublishSubject<String> mCustomerMobileSubject = PublishSubject.create();
    private static PublishSubject<String> mCustomerTelephoneSubject = PublishSubject.create();
    private static PublishSubject<String> mCustomerGenderSubject = PublishSubject.create();

    private static PublishSubject<String> mCustomerSiteSubject = PublishSubject.create();
    private static PublishSubject<Integer> mCustomerBlockSubject = PublishSubject.create();
    private static PublishSubject<String> mCustomerRoomSubject = PublishSubject.create();

    private PublishSubject<Boolean> mResultSubject = PublishSubject.create();

    private CustomerEditView mView;


    private TenantInvoiceHandler mInvoiceHandler;

    public CustomerEditHandler(CustomerEditView view){

        mView = view;
        configureObservables();
        mInvoiceHandler = new TenantInvoiceHandler();
    }

    public void create(){

        mCustomerFactory.post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(saveCustomerSubscriber());
    }

    private void createRecurringInvoice(){

        mInvoiceHandler.saveInvoice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(saveInvoiceSubscriber());
    }

    private void configureObservables(){

        mCustomerFactory.captureCustomerName(mCustomerNameSubject);
        mCustomerFactory.captureCustomerZAID(mCustomerZAIDSubject);
        mCustomerFactory.captureCustomerMobile(mCustomerMobileSubject);
        mCustomerFactory.captureCustomerTelephone(mCustomerTelephoneSubject);
        mCustomerFactory.captureCustomerGender(mCustomerGenderSubject);
        mCustomerFactory.captureCustomerSite(mCustomerSiteSubject);
        mCustomerFactory.captureCustomerRoom(mCustomerRoomSubject);


        Observable<Boolean> nameObservable = RxHelper.getTextWatcherObservable(mView.getTenantName())
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(s -> {

                    ValidationResult result = Validations.validatePerson(s);
                    mView.getTenantName().setError(result.getReason());

                    if(result.isValid()) {
                        mCustomerNameSubject.onNext(s);
                        CustomerObservable.getCustomerNameSubject()
                                .onNext(s);
                    }

                    return result.isValid();
                });

        Observable<Boolean> zaidObservable = RxHelper.getTextWatcherObservable(mView.getTenantZAID())
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(s -> {

                    ValidationResult result = Validations.validateZAID(s);
                    mView.getTenantZAID().setError(result.getReason());

                    if(result.isValid()) { mCustomerZAIDSubject.onNext(s);}

                    return result.isValid();
                });

        Observable<Boolean> mobileObservable = RxHelper.getTextWatcherObservable(mView.getTenantMobile())
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(s -> {

                    ValidationResult result = Validations.validatePhone(s);
                    mView.getTenantMobile().setError(result.getReason());

                    if(result.isValid()) { mCustomerMobileSubject.onNext(s);}

                    return result.isValid();
                });

        Observable<Boolean> genderObservable = RxHelper.getToggleWatcherObservable(mView.getGenderToggleButton())
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> {

                    //ValidationResult result = Validations.validatePhone(s);
                    //mView.getTenantMobile().setError(result.getReason());
                    //if(result.isValid()) { mCustomerMobileSubject.onNext(s);}
                    //return result.isValid();

                    String _str_value = mView.getGenderToggleButton().getTextAtChild(i);

                    mCustomerGenderSubject.onNext(_str_value);

                    return true;
                });

        Observable<Boolean> siteObservable = RxHelper.getToggleWatcherObservable(mView.getSiteToggleButton())
                .debounce(800, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(i -> {

                    //ValidationResult result = Validations.validatePhone(s);
                    //mView.getTenantMobile().setError(result.getReason());
                    //if(result.isValid()) { mCustomerMobileSubject.onNext(s);}
                    //return result.isValid();

                    String _str_value = mView.getSiteToggleButton().getTextAtChild(i);

                    mCustomerSiteSubject.onNext(_str_value);

                    return true;
                });


        Observable
                .combineLatest(nameObservable, mobileObservable, zaidObservable,
                        genderObservable, siteObservable,
                        new Func5<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>()
                        {

            @Override
            public Boolean call(Boolean name, Boolean mobile, Boolean zaid, Boolean gender, Boolean site) {

                return name && mobile && zaid && gender && site;
            }
        }).subscribe(aBoolean -> {

            if(!aBoolean){

                mView.getSaveButton().setTextColor(Color.LTGRAY);
            }else {

                mView.getSaveButton().setTextColor(Color.WHITE);
            }
            mView.getSaveButton().setEnabled(aBoolean);
        });


    }

    public PublishSubject<String> getCustomerRoomSubject(){

        return mCustomerRoomSubject;
    }

    public PublishSubject<Integer> getCustomerBlockSubject(){

        return mCustomerBlockSubject;
    }

    public PublishSubject<Boolean> getResultSubject(){

        return mResultSubject;
    }

    private CommonSubscriber<HttpResult<Map<String, Object>>> saveCustomerSubscriber(){

        return new CommonSubscriber<HttpResult<Map<String, Object>>>(mView.getContext()) {

            @Override
            public void onStart() {

                super.onStart();
                DialogHelper.showProgressDlg(mView.getContext(), "Kancane nje!");
            }

            @Override
            public void onCompleted() {

                DialogHelper.stopProgressDlg();
            }

            @Override
            public void onError(Throwable e) {

                Timber.e("Failed to save tenant");
                Toast.makeText(mView.getContext(), R.string.failed_to_save, Toast.LENGTH_LONG).show();
                mResultSubject.onNext(false);
            }

            @Override
            public void onNext(HttpResult<Map<String, Object>> response) {

                Timber.i(String.valueOf(response.getMessage()));

                if(response.getCode() == 0){

                    CustomerObservable.getInstance()
                            .set(mView.getContext(), String.valueOf(response.getData().get(Constants.ZOHOCONTACTSCHEMA.CUSTOMER_ID)));
                    createRecurringInvoice();
                }
            }
        };
    }

    private CommonSubscriber<HttpResult<Map<String, Object>>> saveInvoiceSubscriber(){

        return new CommonSubscriber<HttpResult<Map<String, Object>>>(mView.getContext()) {

            @Override
            public void onStart() {

                super.onStart();
                DialogHelper.showProgressDlg(mView.getContext(), "Saving Recurring Invoice...");
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            protected void onError(ApiException ex) {
                super.onError(ex);
                mResultSubject.onNext(false);
            }

            @Override
            public void onNext(HttpResult<Map<String, Object>> result) {

                if(result.getCode() == 0) {

                    mResultSubject.onNext(true);
                }
                else {
                    mResultSubject.onNext(false);
                }
            }
        };
    }

}
