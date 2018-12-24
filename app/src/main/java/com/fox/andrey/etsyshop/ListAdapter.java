package com.fox.andrey.etsyshop;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private ArrayList<ActiveResult> mList;
    public static final String TAG = "ListAdapter";
    private NetworkManager networkManager;

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


     ListAdapter(ArrayList<ActiveResult> list) {
        networkManager = new NetworkManager();

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
        ArrayList<ImageItem> imagesList;
        ActiveResult item = mList.get(i);

        Log.d(TAG,"onBindViewHolder");
        viewHolder.mTextView.setText(item.getTitle());
        imagesList = networkManager.getAllImages(item.getListingId());
        Picasso.get().load(imagesList.get(i).getUrl170x135()).into(viewHolder.mImageView);

    }

    //Возвращаю количество элементов массива
    @Override
    public int getItemCount() {
        Log.d(TAG,"getItemCount");
        return mList.size();
    }


}
