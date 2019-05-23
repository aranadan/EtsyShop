package com.fox.andrey.etsyshop;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fox.andrey.etsyshop.interfaces.CallIntent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<ActiveResult> mList;
    private static final String TAG = "ListAdapter";
    private NetworkManager networkManager;
    private Activity mActivity;

    // Определяю элементы представления
    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView mTextView;
        @BindView(R.id.imageView)
        ImageView mImageView;

        MyViewHolder(View v) {
            super(v);
            //Log.d(TAG, "MyViewHolder");
            ButterKnife.bind(this,v);
        }
    }

    ListAdapter(Activity context, ArrayList<ActiveResult> list) {
        networkManager = new NetworkManager();
        mActivity = context;
        mList = list;
        Log.d(TAG, "Constructor");
    }

    //указываю макет для каждого элемента RecyclerView. Затем LayoutInflater заполняет макет, и передает его в конструктор ViewHolder.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder");
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);

        return new MyViewHolder(v);
    }

    //устанавливаю значения полей
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Observable<ImagesResult> imagesList;
        ActiveResult item = mList.get(i);
        AtomicReference<String> urlPhoto = new AtomicReference<>();

        Log.d(TAG, "onBindViewHolder");
        viewHolder.mTextView.setText(item.getTitle());

        //по клику на позицию вызываю новый интент
        viewHolder.itemView.setOnClickListener(view -> {
            CallIntent callIntent = (CallIntent) mActivity;
            callIntent.onSavedItemClick(item, urlPhoto.toString(),mActivity);
        });

        //получаю картинку
        imagesList = networkManager.getAllImages(item.getListingId());
        imagesList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(imagesResult -> Observable.just(imagesResult.getResults())).subscribe(results -> {
            //загружаю с помощью пикассо картинку по ссылке
            urlPhoto.set(results.get(0).getUrl570xN());
            Picasso.get().load(results.get(0).getUrl570xN()).into(viewHolder.mImageView);

        }, throwable -> Log.d(TAG, throwable.getMessage()));
    }

    //Возвращаю количество элементов массива
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return mList.size();
    }
}
