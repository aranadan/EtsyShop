package com.fox.andrey.etsyshop.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.fox.andrey.etsyshop.data.DbContract.*;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = "DbHelper";
    private static final String DATABASE_NAME = "storage.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */
    private static final int DATABASE_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Строка для создания таблицы
        String SQL_CREATE_TABLE = "CREATE TABLE "
                + ItemsEntry.TABLE_NAME + " ("
                + ItemsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemsEntry.COLUMN_PRICE + " REAL NOT NULL, "
                + ItemsEntry.COLUMN_CURRNET_CODE + " TEXT NOT NULL, "
                + ItemsEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + ItemsEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + ItemsEntry.COLUMN_IMAGE + " BLOD NOT NULL); ";

        // Запускаем создание таблицы
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    /**
     * Вызывается при обновлении схемы базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
