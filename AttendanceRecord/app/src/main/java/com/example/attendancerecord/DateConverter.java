package com.example.attendancerecord;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static LocalDate fromString(String string) {

        return LocalDate.parse(string);
    }

    @TypeConverter
    public static String toString(LocalDate date) {
        if(date!=null) {
            return date.toString();
        }else{
        return null;
    }
    }
}
