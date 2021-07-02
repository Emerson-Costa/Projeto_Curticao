package com.example.curticao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {

 private EditText edtNomeLogin, edtPasswordLogin;

    TableCurticaoHelper userLogin = new TableCurticaoHelper(this);

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

        String email = edtNomeLogin.getText().toString();
        String password = edtPasswordLogin.getText().toString();

        User user = new User();

        user.setEmail(email);
        user.setSenha(password);

        // (Restrição)Verificar se o mesmo email esta cadastrado no banco

        if(userLogin.searchUser(user)){
            Intent it=new Intent(UserLogin.this,UserProfile.class);
            Bundle bundle=new Bundle();
            bundle.putString("ch_email",email);
            it.putExtras(bundle);
            startActivity(it);
        }else{
            Toast.makeText(getBaseContext(),"Dados Incorretos.", Toast.LENGTH_SHORT).show();
        }

    }
}