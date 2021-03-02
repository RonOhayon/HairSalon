package com.example.hair_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class LocateByMap extends AppCompatActivity {
    private ImageView lbm_IMG_menu,lbm_IMG_bb;
    private FrameLayout lbm_LAY_map;
    private TextView lbm_LBL_priceTag,lbm_LBL_priceNum,lbm_LBL_SalonName,lbm_LBL_rangeNum,lbm_LBL_range;
    private Button lbm_BTN_next;
    private SeekBar lbm_SB_rangeBar,lbm_SB_priceBar;
    Fragment_Map fragment_map;
    Boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_by_map);
        initFragment();
        findView();
        initView();
    }

    private void findView(){
        lbm_IMG_menu =findViewById(R.id.lbm_IMG_menu);
        lbm_IMG_bb=findViewById(R.id.lbm_IMG_bb);

        lbm_LAY_map =findViewById(R.id.lbm_LAY_map);

        lbm_LBL_priceNum = findViewById(R.id.lbm_LBL_priceNum);
        lbm_LBL_priceTag=findViewById(R.id.lbm_LBL_priceTag);
        lbm_LBL_range=findViewById(R.id.lbm_LBL_range);
        lbm_LBL_rangeNum=findViewById(R.id.lbm_LBL_rangeNum);
        lbm_LBL_SalonName=findViewById(R.id.lbm_LBL_SalonName);
        lbm_LBL_SalonName.setVisibility(View.GONE);
        check =false;

        lbm_BTN_next=findViewById(R.id.lbm_BTN_next);

        lbm_SB_priceBar=findViewById(R.id.lbm_SB_priceBar);
        lbm_SB_rangeBar=findViewById(R.id.lbm_SB_rangeBar);
    }

    private void initFragment(){
        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.lbm_LAY_map,fragment_map).commit();

    }

    private void initView(){
        lbm_BTN_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (check= false){
//                    Toast.makeText(LocateByMap.this,"please find a Salon ",Toast.LENGTH_LONG).show();
//                }
//                else {
//
//                }
                openSalonSche();
            }
        });
        lbm_IMG_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });
        lbm_IMG_bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }

        });
        lbm_SB_priceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lbm_LBL_priceNum.setText(""+progress+"$");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        lbm_SB_rangeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lbm_LBL_rangeNum.setText(""+progress+"km");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    private void closeActivity() {
        finish();
    }

    private void openSalonSche(){
        Intent myIntent = new Intent(this, SalonSchedule.class);
        this.startActivity(myIntent);
    }
}