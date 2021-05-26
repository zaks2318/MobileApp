package com.example.ad340app;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseSingleton {

    private static AppDatabase db;

    public static AppDatabase getDatabase(Context context){
        if(db == null){
            db = Room.databaseBuilder(context, AppDatabase.class,"Setting_database")
                    .build();
        }
        return db;
    }
}
