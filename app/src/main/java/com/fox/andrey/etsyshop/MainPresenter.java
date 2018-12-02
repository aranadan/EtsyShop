package com.fox.andrey.etsyshop;

import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.EtsyApi;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MvpPresenter {
    private static final String TAG = "MainPresenter";
    private static final String BASE_URL = "https://openapi.etsy.com";
    private static final String KEY = "3wqphzh72t03m3pkyr8tle86";
    ArrayList<Result> titlesList = new ArrayList<>();

    //Компоненты MVP приложения
    private MvpView mView;

    //В конструктор передаю экземпляр представления
    public MainPresenter(MvpView mView) {
        this.mView = mView;
        Log.d(TAG, "Constructor");
    }

    //View сообщает, что спинер был нажат
    @Override
    public void onSpinnerClick() {
        Log.d(TAG, "onSpinnerClick");
        getCategory();
    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onSubmitClick() {
        Log.d(TAG, "onSubmitClick");
    }

    private void getCategory(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        EtsyApi etsyApi = retrofit.create(EtsyApi.class);

        Observable<PageTitle> pageTitle = etsyApi.getMessages(KEY);
        pageTitle.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(title -> titlesList.addAll(title.getResults()),throwable -> Log.d(TAG, throwable.getMessage()),() -> mView.showCategory(titlesList));
    }
}
