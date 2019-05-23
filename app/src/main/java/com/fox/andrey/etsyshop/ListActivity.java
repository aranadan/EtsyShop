package com.fox.andrey.etsyshop;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.fox.andrey.etsyshop.interfaces.CallIntent;
import com.fox.andrey.etsyshop.interfaces.MvpListPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements MvpView, CallIntent, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ListActivity";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView.Adapter mAdapter;
    ArrayList<ActiveResult> activeResults;

    String category;
    String searchText;
    MvpListPresenter presenter;

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

        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view);

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

        mAdapter = new ListAdapter(this, activeResults = new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

        attachPresenter(category, searchText);
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

    private void attachPresenter(String category, String searchText) {
        //получаю ранее сохраненный экземпляр презентера
        presenter = (MvpListPresenter) getLastCustomNonConfigurationInstance();

        if (presenter == null) {
            presenter = new ListPresenter(category, searchText);
            Log.d(TAG,"created new presenter");
        }
        presenter.attachView(this);
    }

    //сохраняю презентер
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    /*@Override
    public void onSavedItemClick(ActiveResult item, String urlPhoto) {
        Intent intent = new Intent(ListActivity.this, ItemDetailsActivity.class);
        intent.putExtra("price", item.getPrice());
        intent.putExtra("currency_code", item.getCurrencyCode());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("listingId", item.getListingId());
        intent.putExtra("photo_url",urlPhoto);
        startActivity(intent);
    }*/


}
