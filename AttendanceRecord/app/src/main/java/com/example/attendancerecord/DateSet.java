package com.example.attendancerecord;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "DATESET")
public class DateSet {
    @PrimaryKey(autoGenerate = true)
    int pk;

    @ColumnInfo(name = "SUBJECT")
    String subject;

    @ColumnInfo(name="DATE")
    @TypeConverters(DateConverter.class)
    LocalDate date;

    @ColumnInfo(name="PRESENTS")
    int presents;

    @ColumnInfo(name="ABSENTS")
    int absents;



    public DateSet(String subject, LocalDate date, int presents, int absents) {
        this.subject = subject;
        this.date = date;
        this.presents = presents;
        this.absents = absents;
    }

    @Ignore
    public DateSet(String subject) {
        this.subject = subject;
    }

    @Ignore
    public DateSet(int pk, String subject, LocalDate date, int presents, int absents) {
        this.pk = pk;
        this.subject = subject;
        this.date = date;
        this.presents = presents;
        this.absents = absents;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPresents() {
        return presents;
    }

    public void setPresents(int presents) {
        this.presents = presents;
    }

    public int getAbsents() {
        return absents;
    }

    public void setAbsents(int absents) {
        this.absents = absents;
    }
}
