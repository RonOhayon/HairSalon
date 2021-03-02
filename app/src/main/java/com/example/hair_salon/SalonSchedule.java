package com.example.hair_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SalonSchedule extends AppCompatActivity {

    private CalendarView ss_CLA_calander;
    private FrameLayout ss_LAY_list;
    Fragment_List fragment_list;
    private TextView ss_LBL_name;
    private ImageView ss_IMG_bb,ss_IMG_menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_schedule);
        findView();
        initFragment();
        initView();
    }
    private void findView(){
        ss_CLA_calander = findViewById(R.id.ss_CLA_calander);
        ss_LAY_list= findViewById(R.id.ss_LAY_list);
        ss_LBL_name=findViewById(R.id.li_LBL_name);
        ss_IMG_bb =findViewById(R.id.ss_IMG_bb);
        ss_IMG_menu= findViewById(R.id.ss_IMG_menu);
    }
    private void initFragment(){
        fragment_list = new Fragment_List();
        getSupportFragmentManager().beginTransaction().add(R.id.ss_LAY_list,fragment_list).commit();

    }
    private void initView(){
        ss_IMG_bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        ss_IMG_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    private void closeActivity() {
        finish();
    }
}