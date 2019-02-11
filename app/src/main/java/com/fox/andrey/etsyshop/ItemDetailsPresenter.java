package com.fox.andrey.etsyshop;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fox.andrey.etsyshop.data.DbContract;
import com.fox.andrey.etsyshop.data.DbHelper;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

public class ItemDetailsPresenter  implements MvpPresenter {
    private ItemDetailsActivity view;
    private DbHelper mDbHelper;


    @Override
    public void onClick() {
        mDbHelper = new DbHelper(view);
        insertValue();
    }

    @Override
    public void attachView(MvpView view) {
        this.view = (ItemDetailsActivity) view;


    }

    @Override
    public void detachView() {
        mDbHelper.close();
    }

    // TODO: 23.01.2019 решить как сохранять картинку
    private void insertValue(){
        // получаем базу
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int listingId = view.intent.getIntExtra("listingId",0);
        // Создаем объект ContentValues, где имена столбцов ключи,
        // а информация о является значениями ключей
        ContentValues values = new ContentValues();
        values.put(DbContract.ItemsEntry.COLUMN_CURRENT_CODE, view.currencyCodeTV.getText().toString());
        values.put(DbContract.ItemsEntry.COLUMN_PRICE,view.priceTV.getText().toString());
        values.put(DbContract.ItemsEntry.COLUMN_DESCRIPTION,view.descriptionTv.getText().toString());
        values.put(DbContract.ItemsEntry.COLUMN_TITLE,view.titleTV.getText().toString());
        values.put(DbContract.ItemsEntry.COLUMN_LISTING_ID,listingId);

        // Вставляем новый ряд в базу данных и запоминаем его идентификатор
        long newRowId = db.insert(DbContract.ItemsEntry.TABLE_NAME, null, values);

        // Выводим сообщение в успешном случае или при ошибке
        if (newRowId == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(view, "Ошибка при заведении карточки", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view, "Карточка заведена под номером: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }
}
