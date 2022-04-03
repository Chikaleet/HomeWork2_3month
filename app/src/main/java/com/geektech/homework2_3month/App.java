package com.geektech.homework2_3month;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(this, AppDataBase.class, "Lol")
                .allowMainThreadQueries().build();
    }
}
