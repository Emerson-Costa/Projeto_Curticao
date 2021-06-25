package com.example.curticao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class UserProfile extends AppCompatActivity {
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemPerfil:
                it=new Intent(UserProfile.this,UserProfile.class);
                startActivity(it);
                break;

            case R.id.itemPublicar:
                it=new Intent(UserProfile.this,PhotoLegend.class);
                startActivity(it);
                break;

            case R.id.itemCurticao:
                it=new Intent(UserProfile.this,MainPage.class);
                startActivity(it);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void alterarDados(View view) {
        it= new Intent(UserProfile.this,UserAlter.class);
        startActivity(it);
    }

    public void sair(View view) {
        it= new Intent(UserProfile.this,UserLogin.class);
        startActivity(it);
    }

    public void excluirFoto(View view) {
    }
}