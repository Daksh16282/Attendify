package com.example.attendancerecord;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Attendance.class,version = 7)
public abstract  class Helper extends RoomDatabase {
    private  final static String DATABASE_NAME="ATTENDANCE";
  static   Helper helper;

    public static synchronized Helper getInstance(Context context){
        if(helper==null){
            helper= Room.databaseBuilder(context, Helper.class,DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return helper;
    }

    public abstract DAO getDao();




}
