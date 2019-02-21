package com.fox.andrey.etsyshop;


import android.util.Log;
import android.widget.Toast;

import com.fox.andrey.etsyshop.interfaces.MvpListPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ListPresenter implements MvpListPresenter {
    private static final String TAG = "ListPresenter";
    private ArrayList<ActiveResult> results;
    private ListActivity listActivity;
    private boolean isDownloadNewData = false;
    private int offset = 0;
    private final int offsetCount = 25;

    public ListPresenter(String category, String searchText) {
        getActiveList(category, searchText);
    }

    public void resetOffset() {
        Log.d(TAG, "resetOffset");
        offset = 0;
    }

    public void makeOffset() {
        Log.d(TAG, "makeOffset");
        offset = offset + offsetCount;
    }

    //получаю результат из сети
    public void getActiveList(String category, String searchText) {

        Log.d(TAG, "get data from internet");
        isDownloadNewData = true;
        results = new ArrayList<>();
        NetworkManager manager = new NetworkManager();

        Observable<ActiveObject> active = manager.getActive(category, searchText, offset);
        active.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                flatMap(activeObject -> Observable.just(activeObject.getResults())).
                subscribe(result -> results.addAll(result), throwable -> Log.d(TAG, throwable.getMessage()), () -> {
                    getNewData();
                    isDownloadNewData = false;
                });
    }

    private void getNewData() {
        //если этот запрос пагинация то список не очищаю
        if (offset == 0) refreshMethod();
        else paginationMethod();
    }

    public boolean isDownloading() {
        Log.d(TAG, "isDownloading");
        return isDownloadNewData;
    }

    //обновление списка
    private void refreshMethod() {
        listActivity.activeResults.clear();
        listActivity.activeResults.addAll(results);
        listActivity.mAdapter.notifyDataSetChanged();
        Log.d(TAG, "refreshMethod");
    }

    //пагинация списка
    private void paginationMethod() {
        Toast.makeText(listActivity, "start pagination", Toast.LENGTH_SHORT).show();
        int oldArraySize = listActivity.activeResults.size();
        listActivity.activeResults.addAll(results);
        listActivity.mAdapter.notifyItemRangeInserted(oldArraySize, offsetCount);
        Log.d(TAG, "paginationMethod");
    }


    @Override
    public void attachView(MvpView view) {
        this.listActivity = (ListActivity) view;
        refreshMethod();
        Log.d(TAG, "attachView");
    }

    @Override
    public void detachView() {
        listActivity = null;
        Log.d(TAG, "detachView");
    }
}
