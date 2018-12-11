package com.fox.andrey.etsyshop;

import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;


public class SearchPresenter  implements MvpPresenter {
    private static final String TAG = "SearchPresenter";
    private NetworkManager networkManager;
    private CharSequence[] listCategory;


    //Компоненты MVP приложения
    private SearchActivity searchView;

    //View сообщает, что кнопка категорий нажата
    @Override
    public void onCategoryClick() {
        Log.d(TAG, "onCategoryClick");
        searchView.createDialog(listCategory);
    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onSubmitClick(String category, String searchText) {
        Log.d(TAG, "onSubmitClick");
        networkManager.getActive(category,searchText);
    }

    @Override
    public void attachView(MvpView view){
        searchView = (SearchActivity) view;
        networkManager = new NetworkManager();
        networkManager.getApi();
        listCategory = networkManager.getCategory();
    }

    @Override
    public void detachView(){
        searchView = null;
    }




}
