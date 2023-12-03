package com.example.attendancerecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryRecord extends AppCompatActivity {

    ArrayList<DateSet> historyRecord;
    DateHelper helper;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_recycler);
        helper=DateHelper.getInstance(this);
        historyRecord=(ArrayList<DateSet>) helper.getDateDao().getSubData(getIntent().getExtras().get("SUB").toString());
        if(historyRecord.size()>0) {
            recyclerView = findViewById(R.id.historyRcl);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            RecyclerView.Adapter adapter = new HistoryAdapter(this, historyRecord);
            recyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}