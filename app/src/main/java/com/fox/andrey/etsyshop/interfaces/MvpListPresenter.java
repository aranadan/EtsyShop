package com.fox.andrey.etsyshop.interfaces;

public interface MvpListPresenter {
    void attachView(MvpView view);
    void detachView();
    boolean isDownloading();
    void resetOffset();
    void makeOffset();
    void getActiveList(String category, String searchText);

}
