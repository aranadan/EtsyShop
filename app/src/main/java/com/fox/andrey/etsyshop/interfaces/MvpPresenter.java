package com.fox.andrey.etsyshop.interfaces;



public interface MvpPresenter {
    void onCategoryClick();
    //void onSubmitClick(String category, String searchText);
    void attachView(MvpView view);
    void detachView();


}
