package com.fox.andrey.etsyshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fox.andrey.etsyshop.interfaces.MvpView;
import com.squareup.picasso.Picasso;

public class ItemDetailsActivity extends AppCompatActivity implements MvpView, View.OnClickListener {
    TextView priceTV, currencyCodeTV, titleTV, descriptionTv;
    ImageView imageView;
    ImageButton saveButton;
    ItemDetailsPresenter presenter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

       intent =  getIntent();



       initializeView();

       presenter = new ItemDetailsPresenter();
       presenter.attachView(this);


       priceTV.setText(intent.getStringExtra("price"));
       currencyCodeTV.setText(intent.getStringExtra("currency_code"));
       titleTV.setText(intent.getStringExtra("title"));
       descriptionTv.setText(intent.getStringExtra("description"));
       String url = intent.getStringExtra("photo_url");
       Picasso.get().load(url).into(imageView);
    }

    void initializeView(){
        priceTV = findViewById(R.id.priceTV);
        currencyCodeTV = findViewById(R.id.currencyCodeTV);
        titleTV = findViewById(R.id.titleTV);
        descriptionTv = findViewById(R.id.descriptionTV);
        imageView = findViewById(R.id.imageDetail);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        presenter.onClick();
    }
}
