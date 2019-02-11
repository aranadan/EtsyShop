package com.fox.andrey.etsyshop.interfaces;


import com.fox.andrey.etsyshop.ActiveResult;

import java.util.ArrayList;

public interface MvpSearchPresenter {
    void onClick();
    void attachView(MvpView view);
    void detachView();
    void createSearchTab();
    void createSavedListTab();
    ArrayList<ActiveResult> getSavedList();
}
