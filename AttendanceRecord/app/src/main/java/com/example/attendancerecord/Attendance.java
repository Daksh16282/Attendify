package com.example.attendancerecord;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;

@Entity(tableName = "Attendance")
public class Attendance {

    @PrimaryKey(autoGenerate = true)
    int key;

    @ColumnInfo(name = "Subject")
    @NonNull
    String subject;

    @ColumnInfo(name = "Absents")
    int absents;

    @ColumnInfo(name = "Presents")
    int presents;

    @ColumnInfo(name="Required")
    int required;

    @ColumnInfo(name = "Percentage")
    int percentage;



    @Ignore
    public Attendance(@NonNull String subject) {
        this.subject = subject;
    }


    public Attendance(int key, @NonNull String subject, int absents, int presents, int required,int percentage) {
        this.key = key;
        this.subject = subject;
        this.absents = absents;
        this.presents = presents;
        this.required = required;
        this.percentage=percentage;

    }

    @Ignore
    public Attendance(int key, @NonNull String subject) {
        this.key = key;
        this.subject = subject;
    }


@Ignore
    public Attendance(@NonNull String subject, int absents, int presents, int required, int percentage) {
        this.subject = subject;
        this.absents = absents;
        this.presents = presents;
        this.required = required;
        this.percentage=percentage;

    }

    @Ignore
    public Attendance(@NonNull String subject, int percentage) {
        this.subject = subject;
        this.percentage = percentage;

    }




    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    public void setSubject(@NonNull String subject) {
        this.subject = subject;
    }

    public int getAbsents() {
        return absents;
    }

    public void setAbsents(int absents) {
        this.absents = absents;
    }

    public int getPresents() {
        return presents;
    }

    public void setPresents(int presents) {
        this.presents = presents;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }



}
