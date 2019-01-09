package com.fox.andrey.etsyshop.interfaces;



public interface MvpPresenter {
    void onClick();
    void attachView(MvpView view);
    void detachView();
}
