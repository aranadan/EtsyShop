package com.fox.andrey.etsyshop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    String typeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        intent = getIntent();
        typeActivity = intent.getStringExtra("activity");

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

    void initializeView() {
        priceTV = findViewById(R.id.priceTV);
        currencyCodeTV = findViewById(R.id.currencyCodeTV);
        titleTV = findViewById(R.id.titleTV);
        descriptionTv = findViewById(R.id.descriptionTV);
        imageView = findViewById(R.id.imageDetail);
        saveButton = findViewById(R.id.saveButton);
        //если открыватеся активность сохраненных позиций то доавляю иконку удаления
        if (!isAddButton(typeActivity))
            saveButton.setImageResource(android.R.drawable.ic_menu_delete);

        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = intent.getIntExtra("listingId", 0);

        if (isAddButton(typeActivity)) {
            presenter.addData(id);
        } else {
            presenter.deleteData(id);
            finish();
        }
    }

    boolean isAddButton(String value) {
        return value.equals("ListActivity");
    }
}
