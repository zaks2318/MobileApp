package com.example.ad340app.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ad340app.AppDatabase;
import com.example.ad340app.AppDatabaseSingleton;
import com.example.ad340app.SettingEntity;

import java.util.List;

public class SettingViewModel extends ViewModel {
    public LiveData<List<SettingEntity>> loadAllByIds(Context context, String[] emailIds) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.SettingDao().loadAllByIds(emailIds);
    }

    public void updateSetting(Context context, SettingEntity... settingEntities) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.SettingDao().updateSetting(settingEntities);
        });
    }

    public void deleteUser(Context context, SettingEntity settingEntities) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.SettingDao().delete(settingEntities);
        });
    }

    public void insertAll(Context context, SettingEntity... settingEntities) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.SettingDao().insertAll(settingEntities);
        });
    }
}
