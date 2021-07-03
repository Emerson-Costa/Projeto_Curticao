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

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curticao_page);

        // Pega o email informado pelo usuário
        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        if(bundle != null){
            email = bundle.getString("ch_email");
        }

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
        Bundle bundle;
        switch (item.getItemId()){
            case R.id.itemPerfil:
                it=new Intent(CurticaoPage.this,UserProfile.class);
                bundle=new Bundle();
                bundle.putString("ch_email",email);
                it.putExtras(bundle);
                startActivity(it);
                break;

            case R.id.itemPublicar:
                it=new Intent(CurticaoPage.this,PhotoLegend.class);
                bundle=new Bundle();
                bundle.putString("ch_email",email);
                it.putExtras(bundle);
                startActivity(it);
                break;

            case R.id.itemCurticao:
                it=new Intent(CurticaoPage.this,CurticaoPage.class);
                bundle=new Bundle();
                bundle.putString("ch_email",email);
                it.putExtras(bundle);
                startActivity(it);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}