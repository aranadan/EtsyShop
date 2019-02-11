package com.fox.andrey.etsyshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.fox.andrey.etsyshop.interfaces.CallBackSavedItemsTab;
import com.fox.andrey.etsyshop.interfaces.CallBackSearchTab;
import com.fox.andrey.etsyshop.interfaces.MvpSearchPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements  MvpView, CallBackSearchTab, CallBackSavedItemsTab {
    private static final String TAG = "SearchActivity";
    private MvpSearchPresenter presenter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //загрузка фрагментов
        tabLayout = findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0)
                    presenter.createSearchTab();
                else
                    presenter.createSavedListTab();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        attachPresenter();
    }

    private void attachPresenter() {
        //получаю ранее сохраненный экземпляр презентера
        presenter = (MvpSearchPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new SearchPresenter();
        }
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    //сохраняю презентер
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void onCategoryClick() {
        presenter.onClick();
    }

    @Override
    public void onSubmitClick(String searchText, String category) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("searchText", searchText);
        startActivity(intent);
    }

    @Override
    public ArrayList<ActiveResult> getSavedList() {

        return presenter.getSavedList();
    }

}
