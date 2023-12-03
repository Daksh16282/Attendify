package com.example.attendancerecord;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {


    ArrayList<Attendance> dataSet = new ArrayList<>();
    RecyclerView recyclerView;
    String sub;
    int flag=0;
    SubjectAdapter adapter;

    Helper helper;
    int abs,prs,req,total,percentage;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        BottomNavigationView nv = getActivity().findViewById(R.id.nav_view);

        MenuItem  fb = nv.getMenu().getItem(1);
        fb.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.add_dialog);
                dialog.setCancelable(false);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(lp);
                dialog.show();

                Button cancel = dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button save = dialog.findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText subject = dialog.findViewById(R.id.enteredSubject);
                        sub = String.valueOf(subject.getText());
                        isContains(sub);
                        if (sub.equals("")) {
                            Toast.makeText(getContext(), "Enter Subject Name", Toast.LENGTH_SHORT).show();
                        } else if (flag==-1) {
                            Toast.makeText(getContext(), "Subject already exists", Toast.LENGTH_SHORT).show();
                        } else {

                            helper.getDao().insertRecord(new Attendance(sub, 0));
                            showData();
                            recyclerView.scrollToPosition(dataSet.size() - 1);
                            dialog.dismiss();
                        }
                    }

                });
                return true;
            }

        });
        helper= Helper.getInstance(getActivity().getApplicationContext());
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        showData();
        return view;
    }
public  void showData(){

    dataSet=(ArrayList<Attendance>) helper.getDao().getRecord();
    adapter = new SubjectAdapter(getContext(),dataSet,helper,this);
    recyclerView.setAdapter(adapter);
}

public void isContains(String subject){
    for (int i = 0; i < dataSet.size(); i++) {
        if(dataSet.get(i).getSubject().toUpperCase().equals(subject.toUpperCase())){
            flag=-1;
        }
    }
}



}

