package com.fox.andrey.etsyshop;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fox.andrey.etsyshop.data.DbHelper;
import com.fox.andrey.etsyshop.interfaces.MvpPresenter;
import com.fox.andrey.etsyshop.interfaces.MvpView;

import static com.fox.andrey.etsyshop.data.DbContract.ItemsEntry.*;

public class ItemDetailsPresenter implements MvpPresenter {
    private ItemDetailsActivity view;
    private DbHelper mDbHelper;

    @Override
    public void attachView(MvpView view) {
        this.view = (ItemDetailsActivity) view;
        mDbHelper = new DbHelper(this.view);
    }

    @Override
    public void detachView() {
        mDbHelper.close();
    }

    @Override
    public void addData(int id) {
        // получаем базу
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Создаем объект ContentValues, где имена столбцов ключи,
        // а информация о является значениями ключей
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENT_CODE, view.currencyCodeTV.getText().toString());
        values.put(COLUMN_PRICE, view.priceTV.getText().toString());
        values.put(COLUMN_DESCRIPTION, view.descriptionTv.getText().toString());
        values.put(COLUMN_TITLE, view.titleTV.getText().toString());
        values.put(COLUMN_LISTING_ID, id);

        // Вставляем новый ряд в базу данных и запоминаем его идентификатор
        long newRowId = db.insert(TABLE_NAME, null, values);

        // Выводим сообщение в успешном случае или при ошибке
        if (newRowId == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(view, "Ошибка при заведении карточки", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view, "Карточка добавлена в Ваш список.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleteData(int id) {
        mDbHelper.getWritableDatabase().delete(TABLE_NAME, COLUMN_LISTING_ID + "=" + id,null);
    }


}
