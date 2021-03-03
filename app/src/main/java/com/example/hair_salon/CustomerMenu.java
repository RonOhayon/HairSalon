package com.example.hair_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class CustomerMenu extends AppCompatActivity  {
    private Button cm_BTN_byName, cm_BTN_byMap;
    private ImageView cm_IMG_logo,cm_IMG_menu,
           cm_IMG_haircut,cm_IMG_shave,cm_IMG_straightHair,cm_IMG_hairDye;
    private CheckBox cm_CB_haircut,cm_CB_shave,cm_CB_straightHair,cm_CB_hairDye;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        findView();
        initView();
    }

    private void findView(){
        cm_BTN_byName = findViewById(R.id.cm_BTN_byName);

        cm_IMG_haircut = findViewById(R.id.cm_IMG_haircut);
        cm_IMG_hairDye = findViewById(R.id.cm_IMG_hairDye);
        cm_IMG_logo = findViewById(R.id.cm_IMG_logo);
        cm_IMG_menu = findViewById(R.id.cm_IMG_menu);

        cm_IMG_shave = findViewById(R.id.cm_IMG_shave);
        cm_IMG_straightHair = findViewById(R.id.cm_IMG_straightHair);

        cm_CB_shave = findViewById(R.id.cm_CB_shave);
        cm_CB_straightHair= findViewById(R.id.cm_CB_straightHair);
        cm_CB_hairDye = findViewById(R.id.cm_CB_hairDye);
        cm_CB_haircut = findViewById(R.id.cm_CB_haircut);

    }

    private void  initView() {
        cm_BTN_byName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cm_CB_haircut.isChecked() && !cm_CB_hairDye.isChecked()
                        && !cm_CB_shave.isChecked() && !cm_CB_straightHair.isChecked()) {
                    Toast.makeText(CustomerMenu.this, "please check one of the Options", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CustomerMenu.this, "notWorking", Toast.LENGTH_LONG).show();
                }

            }
        });
        cm_IMG_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });

    }


}