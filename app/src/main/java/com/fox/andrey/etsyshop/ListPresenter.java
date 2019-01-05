package com.fox.andrey.etsyshop;


import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ListPresenter {
    private static final String TAG = "ListPresenter";
    private ArrayList<ActiveResult> results;
    private ListActivity listActivity;
    private boolean isDownloadNewData = false;

    ListPresenter(ListActivity listActivity) {
        this.listActivity = listActivity;
    }

    //получаю результат по запросу
    void getActiveList(String category, String searchText) {
        isDownloadNewData = true;
        results = new ArrayList<>();
        NetworkManager manager = new NetworkManager();

        Observable<ActiveObject> active = manager.getActive(category, searchText);
        active.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                flatMap(activeObject -> Observable.just(activeObject.getResults())).
                subscribe(result -> results.addAll(result), throwable -> Log.d(TAG, throwable.getMessage()), () -> {
                    listActivity.activeResults.clear();
                    listActivity.activeResults.addAll(results);
                    listActivity.mAdapter.notifyDataSetChanged();
                    isDownloadNewData = false;
                });
    }

    boolean isDownloading(){
        return isDownloadNewData;
    }
}
