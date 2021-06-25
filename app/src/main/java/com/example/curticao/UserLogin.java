package com.example.curticao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }

    public void cadastrar(View view) {
        Intent it=new Intent(UserLogin.this,UserRegister.class);
        startActivity(it);
    }

    public void entrar(View view) {
        Intent it=new Intent(UserLogin.this,UserProfile.class);
        startActivity(it);
    }
}