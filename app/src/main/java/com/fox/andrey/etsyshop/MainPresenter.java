package com.fox.andrey.etsyshop;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
    private ArrayList categoryArray;


    //Компоненты MVP приложения
    private MvpView mView;
    private Activity mActivity;

    //В конструктор передаю экземпляр представления
    public MainPresenter(MvpView View, Activity activity) {
        mView = View;
        mActivity = activity;
        categoryArray = new ArrayList();
        Log.d(TAG, "Constructor");
        getCategory();
    }

    //View сообщает, что спинер был нажат
    @Override
    public void onSpinnerClick() {
        Log.d(TAG, "onSpinnerClick");
       createDialog();
    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onSubmitClick() {
        Log.d(TAG, "onSubmitClick");
    }
    //загрузка списка категорий товаров
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
                flatMap(pageTitle1 -> Observable.just(pageTitle1.getResults())).
                flatMapIterable(list -> list).
                map(item -> item.getPageTitle()).
                subscribe(titleItem -> categoryArray.add(titleItem), throwable -> Log.d(TAG, throwable.getMessage()));
    }


    void createDialog() {
        CharSequence[] sequences = (CharSequence[]) categoryArray.toArray(new CharSequence[categoryArray.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.
                setTitle(R.string.set_category).
                setItems(sequences, (dialog, which) -> {
                    String category = (String) sequences[which];
                    mView.showCategory(category);
                });
        builder.create().show();
    }
}
