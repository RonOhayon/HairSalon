package com.example.hair_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HairSalonSetUp extends AppCompatActivity {

    private ImageView HairSet_IMG_maleH,HairSet_IMG_hairDye
            ,HairSet_IMG_shave,HairSet_IMG_hairDry,HairSet_IMG_pin,HairSet_IMG_BackB;
    private Button HairSet_BTN_update;
    private com.google.android.material.textfield.TextInputLayout HairSet_TIP_maleH
            ,HairSet_TIP_HairDye,HairSet_TIP_shave,HairSet_TIP_straighteningHair,HairSet_TIP_pin;
    private HairSalon hairSalon;
    private TextView HairSet_LBL_location,HairSet_LBL_straightHair,HairSet_LBL_shave,
            HairSet_LBL_haircut,HairSet_LBL_title,HairSet_LBL_dye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_salon_set_up);
        findView();
        initView();
        hairSalon = new HairSalon();
    }


    private void findView(){
        HairSet_BTN_update = HairSet_BTN_update.findViewById(R.id.HairSet_BTN_update);

        HairSet_IMG_maleH = HairSet_IMG_maleH.findViewById(R.id.HairSet_IMG_maleH);
        HairSet_IMG_hairDye = HairSet_IMG_hairDye.findViewById(R.id.HairSet_IMG_hairDye);
        HairSet_IMG_shave = HairSet_IMG_shave.findViewById(R.id.HairSet_IMG_shave);
        HairSet_IMG_hairDry = HairSet_IMG_hairDry.findViewById(R.id.HairSet_IMG_hairDry);
        HairSet_IMG_pin = HairSet_IMG_pin.findViewById(R.id.HairSet_IMG_pin);
        HairSet_IMG_BackB = HairSet_IMG_BackB.findViewById(R.id.HairSet_IMG_BackB);

        HairSet_TIP_maleH = HairSet_TIP_maleH.findViewById(R.id.HairSet_TIP_maleH);
        HairSet_TIP_HairDye =HairSet_TIP_HairDye.findViewById(R.id.HairSet_TIP_HairDye);
        HairSet_TIP_shave = HairSet_TIP_shave.findViewById(R.id.HairSet_TIP_shave);
        HairSet_TIP_straighteningHair =HairSet_TIP_straighteningHair.findViewById(R.id.HairSet_TIP_straighteningHair);
        HairSet_TIP_pin = HairSet_TIP_pin.findViewById(R.id.HairSet_TIP_pin);

        HairSet_LBL_location= HairSet_LBL_location.findViewById(R.id.HairSet_LBL_location);
        HairSet_LBL_straightHair =HairSet_LBL_straightHair.findViewById(R.id.HairSet_LBL_location);
        HairSet_LBL_shave=HairSet_LBL_shave.findViewById(R.id.HairSet_LBL_shave);
        HairSet_LBL_haircut=HairSet_LBL_haircut.findViewById(R.id.HairSet_LBL_haircut);
        HairSet_LBL_title=HairSet_LBL_title.findViewById(R.id.HairSet_LBL_title);
        HairSet_LBL_dye= HairSet_LBL_dye.findViewById(R.id. HairSet_LBL_dye);
    }

    private void initView(){
        HairSet_IMG_BackB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        HairSet_BTN_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHairSalon();
                if(HairSet_TIP_pin.getEditText().getText().toString().trim().isEmpty()){
                    HairSet_TIP_pin.setError("Need location");
                    return;
                }

            }
        });
        HairSet_TIP_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void closeActivity() {
        finish();
    }

    private void setHairSalon(){
        String haircut = HairSet_TIP_maleH.getEditText().getText().toString().trim();
        String shave = HairSet_TIP_shave.getEditText().getText().toString().trim();
        String hairStraight =HairSet_TIP_straighteningHair.getEditText().getText().toString().trim();
        String hairDye =HairSet_TIP_HairDye.getEditText().getText().toString().trim();

        if (!haircut.matches("[0-9]+")){
            HairSet_TIP_maleH.setError("please provide Valid Number");
            return;
        }
        if (haircut.isEmpty()){
            hairSalon.setHairCut(0);
        }

               if(!shave.matches("[0-9]+")){
            HairSet_TIP_shave.setError("please provide Valid Number");
            return;
        }
        if (shave.isEmpty()) {
            hairSalon.setShave(0);
        }

        if(!hairStraight.matches("[0-9]+")){
            HairSet_TIP_straighteningHair.setError("please provide Valid Number");
            return;
        }
        if(hairStraight.isEmpty()){
            hairSalon.setStraighteningHair(0);
        }

        if (!hairDye.matches("[0-9]+")){
            HairSet_TIP_HairDye.setError("please provide Valid Number");
            return;
        }
        if (hairDye.isEmpty()){
            hairSalon.setHairColor(0);
        }
        createHairSalon(haircut,shave,hairStraight,hairDye);

    }

    private void createHairSalon(String haircut,String shave,String hairStraight, String hairDye){
        if (!hairDye.isEmpty()){
            hairSalon.setHairColor(Integer.parseInt(hairDye));
        }
        if(!hairStraight.isEmpty()){
            hairSalon.setStraighteningHair(Integer.parseInt(hairStraight));
        }
        if (!shave.isEmpty()) {
            hairSalon.setShave(Integer.parseInt(shave));
        }

        if (!haircut.isEmpty()){
            hairSalon.setHairCut(Integer.parseInt(haircut));
        }

    }


}