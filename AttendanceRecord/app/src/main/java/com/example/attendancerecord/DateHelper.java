package com.example.attendancerecord;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = DateSet.class,exportSchema = false,version = 12)
public abstract  class DateHelper extends RoomDatabase {
    private  final static String DATABASE_NAME="DATESETS";
    static   DateHelper helper;

    public static synchronized DateHelper getInstance(Context context){
        if(helper==null){
            helper= Room.databaseBuilder(context, DateHelper.class,DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return helper;
    }


    public abstract DATEDAO getDateDao();




}
