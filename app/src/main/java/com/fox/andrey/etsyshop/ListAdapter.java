package com.fox.andrey.etsyshop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<ActiveResult> mList;
    public static final String TAG = "ListAdapter";
    private NetworkManager networkManager;
    private Context mContext;

    // Определяю элементы представления
    public static class MyViewHolder extends RecyclerView.ViewHolder {
         TextView mTextView;
         ImageView mImageView;

         MyViewHolder(View v) {
            super(v);
            Log.d(TAG,"MyViewHolder");
            mTextView = v.findViewById(R.id.textView);
            mImageView = v.findViewById(R.id.imageView);
        }
    }


     ListAdapter(Context context, ArrayList<ActiveResult> list) {
        networkManager = new NetworkManager();
        mContext = context;


        mList = list;
        Log.d(TAG,"Constructor");
    }

    //указываю макет для каждого элемента RecyclerView. Затем LayoutInflater заполняет макет, и передает его в конструктор ViewHolder.
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG,"onCreateViewHolder");
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);

        return new MyViewHolder(v);
    }

    //устанавливаю значения полей
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        Observable<ImagesResult> imagesList;
        ActiveResult item = mList.get(i);


        AtomicReference<String> urlPhoto = new AtomicReference<>();

        Log.d(TAG,"onBindViewHolder");
        viewHolder.mTextView.setText(item.getTitle());

        //по клику на позицию вызываю новый интент
        viewHolder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, ItemDetailsActivity.class);
            intent.putExtra("price", item.getPrice());
            intent.putExtra("currency_code", item.getCurrencyCode());
            intent.putExtra("title", item.getTitle());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("listingId", item.getListingId());
            intent.putExtra("photo_url",urlPhoto.toString());
            mContext.startActivity(intent);
        });

        //получаю картинку
        imagesList = networkManager.getAllImages(item.getListingId());
        imagesList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(imagesResult -> Observable.just(imagesResult.getResults()))
                .subscribe(results ->{
                            //загружаю с помощью пикассо картинку по ссылке
                            urlPhoto.set(results.get(0).getUrl570xN());
                            Picasso.get().load(results.get(0).getUrl570xN()).into(viewHolder.mImageView);

                    }
                        ,throwable -> Log.d(TAG, throwable.getMessage()));


    }

    //Возвращаю количество элементов массива
    @Override
    public int getItemCount() {
        Log.d(TAG,"getItemCount");
        return mList.size();
    }



}
