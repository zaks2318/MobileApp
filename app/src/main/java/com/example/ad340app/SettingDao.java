package com.example.ad340app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingDao {
    @Query("SELECT * FROM settingentity")
    LiveData<List<SettingEntity>> getAll();

    @Query("SELECT * FROM settingEntity WHERE email IN (:emails)")
    LiveData<List<SettingEntity>> loadAllByIds(String[] emails);

    @Update
    void updateSetting(SettingEntity... settingEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSettings(SettingEntity... settingEntities);

    @Delete
    void delete(SettingEntity settingEntities);
}
