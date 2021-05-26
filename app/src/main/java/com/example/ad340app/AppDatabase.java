package com.example.ad340app;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SettingEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingDao SettingDao();
}