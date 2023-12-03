package com.example.attendancerecord;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    String sub;
    BottomNavigationView navView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       toolbar=findViewById(R.id.toolbar);
     setSupportActionBar(toolbar);
     if(getSupportActionBar()!=null){
         getSupportActionBar().setTitle("Attendance");
     }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameLayout,new HomeFragment());

        ft.commit();
        navView = findViewById(R.id.nav_view);
        navView.setElevation(0);
        
       navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id=item.getItemId();
               item.setChecked(false);

               if(id==R.id.navigation_home){
                   loadFragment(new HomeFragment(),0);
               }

               if(id==R.id.navigation_calendar){
                   Intent intent = new Intent(Intent.ACTION_VIEW, CalendarContract.CONTENT_URI.buildUpon().appendPath("time").build());
                   startActivity(intent);

               }


               return true;
           }
       });
    }
    public void loadFragment(Fragment fragment,int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag==0) {

             fm.popBackStack();
        }else{
            ft.addToBackStack(null);

        }

            ft.replace(R.id.frameLayout, fragment);




        ft.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}