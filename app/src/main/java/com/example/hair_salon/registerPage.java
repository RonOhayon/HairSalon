package com.example.hair_salon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerPage extends AppCompatActivity {
    public static final String EXTRA_KEY_LAT = "EXTRA_KEY_LAT";
    public static final String EXTRA_KEY_LONG = "EXTRA_KEY_LONG";
    public static final String EXTRA_KEY_CHECK = "EXTRA_KEY_CHECK";
    private TextView rPage_LBL_SignUp;
    private CheckBox rPage_CB_customer,rPage_CB_hairdresser;
    private com.google.android.material.textfield.TextInputLayout rPage_Name,rPage_email,
            rPage_phoneNum, rPage_hairSalonName,rPage_password,rPage_hairSalonName_location;
    private Button rPage_BTN_signUp,rPage_BTN_SetLocation;
    private ImageView rPage_IMG_BB;
    private FirebaseAuth mAuth;
    private ProgressBar rPage_PB_loadBar;
    private double lat,lng;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth = FirebaseAuth.getInstance();
        findView();
        initView();
        if(getIntent().getIntExtra(EXTRA_KEY_CHECK,-1)==1){

        }

    }

    void findView(){
        rPage_LBL_SignUp = findViewById(R.id.rPage_LBL_SignUp);
        rPage_CB_customer = findViewById(R.id.rPage_CB_customer);
        rPage_CB_hairdresser =findViewById(R.id.rPage_CB_hairdresser);
        rPage_BTN_signUp = findViewById(R.id.rPage_BTN_signUP);
        rPage_Name =findViewById(R.id.rPage_Name);
        rPage_email =findViewById(R.id.rPage_email);
        rPage_phoneNum = findViewById(R.id.rPage_phoneNum);
        rPage_password =findViewById(R.id.rPage_password);
        rPage_hairSalonName =findViewById(R.id.rPage_hairSalonName);
        rPage_hairSalonName.setVisibility(View.GONE);
        rPage_IMG_BB = findViewById(R.id.rPage_IMG_BB);
        rPage_PB_loadBar = findViewById(R.id.rPage_PB_loadBar);
        rPage_hairSalonName_location = findViewById(R.id.rPage_hairSalonName_location);
        rPage_BTN_SetLocation = findViewById(R.id.rPage_BTN_SetLocation);
        rPage_BTN_SetLocation.setVisibility(View.GONE);
    }

    void initView() {
        rPage_BTN_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( rPage_CB_customer.isChecked()) {
                    cleanErrorLog();
                    registerUser();


                }
                if(rPage_CB_hairdresser.isChecked()){
                    cleanErrorLog();
                    registerHairdresser();


                }
            }
        });
        rPage_CB_hairdresser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rPage_CB_hairdresser.isChecked()) {
                    rPage_CB_hairdresser.setChecked(true);
                    rPage_CB_customer.setChecked(false);
                    rPage_hairSalonName.setVisibility(View.VISIBLE);
                    rPage_hairSalonName_location.setVisibility(View.VISIBLE);
                    rPage_BTN_SetLocation.setVisibility(View.VISIBLE);
                }
            }
        });
        rPage_CB_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rPage_CB_customer.isChecked()) {
                    rPage_CB_customer.setChecked(true);
                    rPage_CB_hairdresser.setChecked(false);
                    rPage_hairSalonName.setVisibility(View.GONE);
                    rPage_hairSalonName_location.setVisibility(View.GONE);
                    rPage_BTN_SetLocation.setVisibility(View.GONE);
                }
            }
        });
        rPage_IMG_BB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        rPage_BTN_SetLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               setLocationPage();
            }
        });

    }

    private void closeActivity() {
        finish();
    }

    private void registerUser() {
        String name = rPage_Name.getEditText().getText().toString().trim();
        String email = rPage_email.getEditText().getText().toString().trim();
        String phone = rPage_phoneNum.getEditText().getText().toString().trim();
        String password = rPage_password.getEditText().getText().toString().trim();


        if (name.isEmpty()) {
            rPage_Name.setError("first name is required!");
            rPage_Name.requestFocus();
            return;
        }
        if (name.matches("[0-9]+")) {
            rPage_Name.setError("invalid name no numbers!!!");
            rPage_Name.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            rPage_email.setError("Email is required");
            rPage_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            rPage_email.setError("please provide a valid Email!");
            rPage_email.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            rPage_phoneNum.setError("phoneNumber is required!");
            rPage_phoneNum.requestFocus();
            return;
        }
        if (!Patterns.PHONE.matcher(phone).matches()) {
            rPage_phoneNum.setError("please provide a valid Phone number");
            rPage_phoneNum.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            rPage_password.setError("password is required!");
            rPage_password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            rPage_password.setError("Min password is 6 lathers ");
            rPage_password.requestFocus();
            return;
        }

        rPage_PB_loadBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,email,password,phone);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(registerPage.this,"Register is Successful",Toast.LENGTH_LONG).show();
                                        closeActivity();

                                    }
                                    else {
                                        Toast.makeText(registerPage.this,"Register is failed",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    rPage_PB_loadBar.setVisibility(View.GONE);
                                }
                            });
                        }
                        else {
                            Toast.makeText(registerPage.this,"fail",Toast.LENGTH_LONG).show();
                            rPage_PB_loadBar.setVisibility(View.GONE);
                            return;
                        }
                    }
                });

    }

    private void registerHairdresser(){
        String name = rPage_Name.getEditText().getText().toString().trim();
        String email = rPage_email.getEditText().getText().toString().trim();
        String phone = rPage_phoneNum.getEditText().getText().toString().trim();
        String password = rPage_password.getEditText().getText().toString().trim();
        String salonName = rPage_hairSalonName.getEditText().getText().toString().trim();


        if (name.isEmpty()) {
            rPage_Name.setError("name is required!");
            rPage_Name.requestFocus();
            return;
        }
        if (name.matches("[0-9]+")) {
            rPage_Name.setError("invalid name no numbers!!!");
            rPage_Name.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            rPage_email.setError("Email is required");
            rPage_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            rPage_email.setError("please provide a valid Email!");
            rPage_email.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            rPage_phoneNum.setError("phoneNumber is required!");
            rPage_phoneNum.requestFocus();
        }
        if (!Patterns.PHONE.matcher(phone).matches()) {
            rPage_phoneNum.setError("please provide a valid Phone number");
            rPage_phoneNum.requestFocus();
        }
        if (password.isEmpty()) {
            rPage_password.setError("password is required!");
            rPage_password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            rPage_password.setError("Min password is 6 lathers ");
            rPage_password.requestFocus();
            return;
        }
        if (salonName.isEmpty()){
           rPage_hairSalonName.setError(("name is required!"));
           rPage_hairSalonName.requestFocus();
           return;
       }

        rPage_PB_loadBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(lat==0 || lng ==0){
                                rPage_hairSalonName.setError("please set Location");
                                return;
                            }
                            Location location = new Location("new");
                            location.setLatitude(lat);
                            location.setLongitude(lng);

                            HairSalon hs = new HairSalon(location);
                            UserHairdresser udh = new UserHairdresser(name,email,password,phone,salonName,hs);
                            FirebaseDatabase.getInstance().getReference("Salons")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(udh).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(registerPage.this,"Register is Successful",Toast.LENGTH_LONG).show();
                                        closeActivity();
                                    }
                                    else {
                                        Toast.makeText(registerPage.this,"Register is failed",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    rPage_PB_loadBar.setVisibility(View.GONE);
                                }
                            });
                        }
                        else {
                            Toast.makeText(registerPage.this,"fail",Toast.LENGTH_LONG).show();
                            rPage_PB_loadBar.setVisibility(View.GONE);
                            return;
                        }
                    }
                });



    }

    private void cleanErrorLog(){
        rPage_Name.setError(null);
        rPage_Name.setErrorEnabled(false);
        rPage_email.setError(null);
        rPage_email.setErrorEnabled(false);
        rPage_password.setError(null);
        rPage_password.setErrorEnabled(false);
        rPage_phoneNum.setError(null);
        rPage_phoneNum.setErrorEnabled(false);
        rPage_hairSalonName.setError(null);
        rPage_hairSalonName.setErrorEnabled(false);
        rPage_hairSalonName_location.setError(null);
        rPage_hairSalonName_location.setErrorEnabled(false);
    }

    private void setLocationPage(){
        Intent myIntent = new Intent(this, LocationSetUp.class);
        startActivityForResult(myIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                lat = data.getDoubleExtra(EXTRA_KEY_LAT,0);
                lng = data.getDoubleExtra(EXTRA_KEY_LONG,0);
                rPage_hairSalonName_location.setHint(""+lat+" : "+lng);
            }if(resultCode == RESULT_CANCELED){
                rPage_hairSalonName_location.setError("Location is needed!");
            }

        }



    }
}


