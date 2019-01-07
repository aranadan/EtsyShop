package com.fox.andrey.etsyshop.data;

import android.provider.BaseColumns;

public final class DbContract {

    public DbContract() {
    }

    public static final class ItemsEntry implements BaseColumns{

        public final static String TABLE_NAME = "Items";

        //беру столбез из интерфейса
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_CURRNET_CODE = "code";
        public final static String COLUMN_TITLE = "title";
        public final static String COLUMN_DESCRIPTION = "description";
        public final static String COLUMN_IMAGE = "image";

    }
}
