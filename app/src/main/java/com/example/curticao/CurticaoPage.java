package com.example.curticao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CurticaoPage extends AppCompatActivity {

    Intent it;
    TableCurticaoHelper userFotos = new TableCurticaoHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curticao_page);

        ListView lvCurticao = findViewById(R.id.lvCurticao);
        ArrayAdapter adapter = new CurticaoArrayAdapter(this, adicionar_fotos(), adicionar_Avaliacoes());
        lvCurticao.setAdapter(adapter);

    }

    private ArrayList<Foto> adicionar_fotos(){
        ArrayList<Foto> fotos = userFotos.searchAllFotos();
        return fotos;
    }

    private ArrayList<Avaliacao> adicionar_Avaliacoes(){
        ArrayList<Avaliacao> avaliacoes = null;
        return avaliacoes;
    }



    /*
    * Menu Activity
    * */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemPerfil:
                it=new Intent(CurticaoPage.this,UserProfile.class);
                startActivity(it);
                break;

            case R.id.itemPublicar:
                it=new Intent(CurticaoPage.this,PhotoLegend.class);
                startActivity(it);
                break;

            case R.id.itemCurticao:
                it=new Intent(CurticaoPage.this,CurticaoPage.class);
                startActivity(it);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}