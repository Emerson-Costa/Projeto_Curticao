package com.example.curticao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainPage extends AppCompatActivity {
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
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
                it=new Intent(MainPage.this,UserProfile.class);
                startActivity(it);
                break;

            case R.id.itemPublicar:
                it=new Intent(MainPage.this,PhotoLegend.class);
                startActivity(it);
                break;

            case R.id.itemCurticao:
                it=new Intent(MainPage.this,MainPage.class);
                startActivity(it);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}