package com.example.attendancerecord;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM ATTENDANCE")
    List<Attendance> getRecord();

    @Insert
    void insertRecord(Attendance attendance);

    @Update
    void  updateRecord(Attendance attendance);

    @Delete
    void deleteRecord(Attendance attendance);
}

