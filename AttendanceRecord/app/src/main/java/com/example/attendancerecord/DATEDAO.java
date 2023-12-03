package com.example.attendancerecord;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface DATEDAO {

    @Query("SELECT * FROM DATESET")
    List<DateSet> getDateRecord();

    @Insert
    void insertDate(DateSet dateSet);

    @Delete
    void deleteDate(DateSet dateSet);

    @Query("SELECT * FROM DATESET WHERE SUBJECT=:SUB")
    List<DateSet> getSubData(String SUB);

    @Query("UPDATE DATESET SET PRESENTS=PRESENTS+1 WHERE SUBJECT=:SUB AND DATE=:TARIK")
    void updatePrs(String SUB, @TypeConverters(DateConverter.class) LocalDate TARIK);
    @Query("UPDATE DATESET SET ABSENTS=ABSENTS+1 WHERE SUBJECT=:SUB AND DATE=:TARIK")
    void updateAbs(String SUB, @TypeConverters(DateConverter.class) LocalDate TARIK);

    @Query("UPDATE DATESET SET PRESENTS=:PRESENTS,ABSENTS=:ABSENTS WHERE SUBJECT=:SUB AND DATE=:TARIK")
    void updatePA(String SUB, @TypeConverters(DateConverter.class) LocalDate TARIK,int PRESENTS,int ABSENTS);

    @Query("DELETE FROM DATESET WHERE SUBJECT=:SUB")
    void deleteDate(String  SUB);
}
