package com.example.ad340app;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SettingEntity {
    @PrimaryKey
    @NonNull
    private String email = "";

    @ColumnInfo(name = "DailyReminderTime")
    private String DailyReminderTime;

    @ColumnInfo(name = "MaxDistance")
    private String MaxDistance;

    @ColumnInfo(name = "Gender")
    private String Gender;

    @ColumnInfo(name = "PrivateAccount")
    private boolean PrivateAccount;

    @ColumnInfo(name = "InterestedAgeMax")
    private String InterestedAgeMax;

    @ColumnInfo(name = "InterestedAgeMin")
    private String InterestedAgeMin;

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getDailyReminderTime() {
        return DailyReminderTime;
    }

    public void setDailyReminderTime(String dailyReminderTime) {
        DailyReminderTime = dailyReminderTime;
    }

    public String getMaxDistance() {
        return MaxDistance;
    }

    public void setMaxDistance(String maxDistance) {
        MaxDistance = maxDistance;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public boolean isPrivateAccount() {
        return PrivateAccount;
    }

    public void setPrivateAccount(boolean privateAccount) {
        PrivateAccount = privateAccount;
    }

    public String getInterestedAgeMax() {
        return InterestedAgeMax;
    }

    public void setInterestedAgeMax(String interestedAgeMax) {
        InterestedAgeMax = interestedAgeMax;
    }

    public String getInterestedAgeMin() {
        return InterestedAgeMin;
    }

    public void setInterestedAgeMin(String interestedAgeMin) {
        InterestedAgeMin = interestedAgeMin;
    }
}
