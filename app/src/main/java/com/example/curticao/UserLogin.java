package com.example.curticao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserLogin extends AppCompatActivity {
 private EditText edtNomeLogin, edtPasswordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        edtNomeLogin = findViewById(R.id.edtNomeLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
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