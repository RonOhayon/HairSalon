package com.example.hair_salon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
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

public class MainActivity extends AppCompatActivity {

    private Button main_BTN_login;
    private ImageView main_IMG_logo;
    private com.google.android.material.textfield.TextInputLayout  main_email,main_password;
    private TextView main_LBL_name,main_LBL_register;
    private FirebaseAuth mAuth;
    private CheckBox main_CB_customer,main_CB_hairdresser;
    private ProgressBar main_PB_loadBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        findView();
        initView();
    }

     private void findView(){
        main_BTN_login = findViewById(R.id.main_BTN_login);
        main_IMG_logo = findViewById(R.id.main_IMG_logo);
        main_LBL_name = findViewById(R.id.main_LBL_name);
        main_LBL_register = findViewById(R.id.main_LBL_register);
        main_LBL_register.setPaintFlags(main_LBL_register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        main_email = findViewById(R.id.main_Email);
        main_password = findViewById(R.id.main_password);
        main_CB_customer =findViewById(R.id.main_CB_customer);
        main_CB_hairdresser =findViewById(R.id.main_CB_hairdresser);
        main_PB_loadBar = findViewById(R.id.rPage_PB_loadBar);
    }
    private void initView(){
        main_LBL_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterFile();
            }
        });

        main_CB_hairdresser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(main_CB_hairdresser.isChecked()){
                    main_CB_hairdresser.setChecked(true);
                    main_CB_customer.setChecked(false);
                }
            }
        });
        main_CB_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(main_CB_customer.isChecked()){
                    main_CB_customer.setChecked(true);
                    main_CB_hairdresser.setChecked(false);
                }
            }
        });
        main_BTN_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuOpen();
            }
        });
    }

    private void openRegisterFile(){
        Intent myIntent = new Intent(this, registerPage.class);
        this.startActivity(myIntent);

    }

    private void signIn(){
        String email = main_email.getEditText().getText().toString().trim();
        String password = main_password.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            main_email.setError("Email is required");
            main_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            main_email.setError("please provide a valid Email!");
            main_email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            main_password.setError("password is required!");
            main_password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            main_password.setError("Min password is 6 lathers ");
            main_password.requestFocus();
            return;
        }
        main_PB_loadBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mainMenuOpen();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Check your credentials",Toast.LENGTH_LONG).show();
                    }
            }
        });


    }

    private void mainMenuOpen(){
        Intent myIntent = new Intent(this, MainMenu.class);
        this.startActivity(myIntent);
    }
}