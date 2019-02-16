package com.fox.andrey.etsyshop.interfaces;



public interface MvpPresenter {
    void addData(int id);
    void deleteData(int id);
    void attachView(MvpView view);
    void detachView();
}
