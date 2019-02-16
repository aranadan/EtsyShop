package com.fox.andrey.etsyshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fox.andrey.etsyshop.interfaces.CallBackSavedItemsTab;
import com.fox.andrey.etsyshop.interfaces.CallBackSearchTab;
import com.fox.andrey.etsyshop.interfaces.CallIntent;
import com.fox.andrey.etsyshop.interfaces.MvpSearchPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements  MvpView, CallIntent, CallBackSearchTab, CallBackSavedItemsTab {
    private static final String TAG = "SearchActivity";
    private MvpSearchPresenter presenter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //загрузка фрагментов по выбору пользователя
        tabLayout = findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0)
                    createSearchTab();
                else
                    createSavedListTab();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        attachPresenter();
    }

    private void attachPresenter() {
        //получаю ранее сохраненный экземпляр презентера
        presenter = (MvpSearchPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new SearchPresenter();
            //при первой загрузке загружаю вкладку поиска
            createSearchTab();
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

    public void createSavedListTab() {
        SavedListTabFragment fragment = new SavedListTabFragment();
        replaceFragment(fragment);
    }

    public void createSearchTab() {
        SearchTabFragment fragment = new SearchTabFragment();
        replaceFragment(fragment);
    }

    void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}
