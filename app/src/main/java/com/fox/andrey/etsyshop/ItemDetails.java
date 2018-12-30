package com.fox.andrey.etsyshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetails extends AppCompatActivity {
    TextView priceTV, currencyCodeTV, titleTV, descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

       Intent intent =  getIntent();

       initializeView();

       priceTV.setText(intent.getStringExtra("price"));
       currencyCodeTV.setText(intent.getStringExtra("currency_code"));
       titleTV.setText(intent.getStringExtra("title"));
       descriptionTv.setText(intent.getStringExtra("description"));
    }

    void initializeView(){
        priceTV = findViewById(R.id.priceTV);
        currencyCodeTV = findViewById(R.id.currencyCodeTV);
        titleTV = findViewById(R.id.titleTV);
        descriptionTv = findViewById(R.id.descriptionTV);
    }
}
