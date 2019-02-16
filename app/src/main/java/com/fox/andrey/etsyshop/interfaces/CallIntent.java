package com.fox.andrey.etsyshop.interfaces;

import android.app.Activity;
import android.content.Intent;
import com.fox.andrey.etsyshop.ActiveResult;
import com.fox.andrey.etsyshop.ItemDetailsActivity;

public interface CallIntent {
    default void onSavedItemClick(ActiveResult item, String urlPhoto, Activity activity){
        Intent intent = new Intent(activity, ItemDetailsActivity.class);
        intent.putExtra("price", item.getPrice());
        intent.putExtra("currency_code", item.getCurrencyCode());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("listingId", item.getListingId());
        intent.putExtra("photo_url",urlPhoto);
        //строка для определения какое действие будет выполнять детальная активность
        intent.putExtra("activity",activity.getClass().getSimpleName());
        activity.startActivity(intent);
    }
}
