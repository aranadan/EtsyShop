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
    private int offset = 0;
    private final int offsetCount = 25;

    public void resetOffset() {
        Log.d(TAG,"resetOffset");
        offset = 0;}

    public void makeOffset() {
        Log.d(TAG,"makeOffset");
        offset = offset + offsetCount;}

    ListPresenter(ListActivity listActivity) {
        this.listActivity = listActivity;
    }

    //получаю результат по запросу
    void getActiveList(String category, String searchText) {
        isDownloadNewData = true;
        results = new ArrayList<>();
        NetworkManager manager = new NetworkManager();

        Observable<ActiveObject> active = manager.getActive(category, searchText, offset);
        active.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                flatMap(activeObject -> Observable.just(activeObject.getResults())).
                subscribe(result -> results.addAll(result), throwable -> Log.d(TAG, throwable.getMessage()), () -> {
                    //если этот запрос пагинация то список не очищаю
                    if (offset == 0) refreshMethod();
                    else paginationMethod();
                    isDownloadNewData = false;
                });
    }

    boolean isDownloading(){
        return isDownloadNewData;
    }

    //обновление списка
    void refreshMethod(){
        listActivity.activeResults.clear();
        listActivity.activeResults.addAll(results);
        listActivity.mAdapter.notifyDataSetChanged();
    }

    //пагинация списка
    void paginationMethod(){
        int oldArraySize = listActivity.activeResults.size();
        listActivity.activeResults.addAll(results);
        listActivity.mAdapter.notifyItemRangeInserted(oldArraySize, offsetCount);
    }
}
