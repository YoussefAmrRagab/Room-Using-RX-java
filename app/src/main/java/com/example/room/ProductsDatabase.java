package com.example.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Products.class, version = 1)
abstract class ProductsDatabase extends RoomDatabase {

    private static ProductsDatabase instance;

    public abstract ProductsDAO productsDAO();

    public static synchronized ProductsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProductsDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
