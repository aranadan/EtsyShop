package com.fox.andrey.etsyshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements MvpView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ListActivity";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.Adapter mAdapter;
    ArrayList<ActiveResult> activeResults;


    String category;
    String searchText;
    ListPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        category = getIntent().getStringExtra("category");
        searchText = getIntent().getStringExtra("searchText");

        mSwipeRefreshLayout = findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        presenter = new ListPresenter(this);

        mRecyclerView = findViewById(R.id.my_recycler_view);

        //Если вы уверены, что размер RecyclerView не будет изменяться, вы можете добавить этот код для улучшения производительности:
        mRecyclerView.setHasFixedSize(true);

        // менеджер компоновки для управления позиционированием своих элементов
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //слушатель на прокрутку списка
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                // при прокрутке вниз списка
                if (dy > 0){
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0 && !presenter.isDownloading() /*&& totalItemCount >= PAGE_SIZE*/) {
                    Log.d(TAG, " load more data");
                    presenter.makeOffset();
                    //отправляю запрос
                    presenter.getActiveList(category, searchText);
                }
            }
            }
        });

        //отправляю запрос
        presenter.getActiveList(category, searchText);

        mAdapter = new ListAdapter(this, activeResults = new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh");

        //сбрасываю смещение результатов
        presenter.resetOffset();

        //получаю новые данные
        presenter.getActiveList(category, searchText);

        // Отменяем анимацию обновления
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
