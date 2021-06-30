package com.example.curticao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    Intent it;
    private TextView txtnomeProfile, txtCidadeProfile, txtSloganProfile;

    TableCurticaoHelper userProfile = new TableCurticaoHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txtnomeProfile   = findViewById(R.id.txtnomeProfile);
        txtCidadeProfile = findViewById(R.id.txtCidadeProfile);
        txtSloganProfile = findViewById(R.id.txtSloganProfile);

        // Pega o email informado pelo usuário
        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        String email="";
        if(bundle != null){
            email = bundle.getString("ch_email");
        }

        User u = userProfile.searchDateUser(email);

        String nome   = u.getNome();
        String cidade = u.getCidade();
        String slogan = u.getSlogan();

        txtnomeProfile.setText("Nome: "+nome);
        txtCidadeProfile.setText("Cidade: "+cidade);
        txtSloganProfile.setText("Slogan: "+slogan);

        /*Criar método onde verifica se o usuário possui alguma foto publicada,
          Caso não haja nenhuma publicação mostrar uma mensagem
         */

    }

    public void alterarDados(View view) {

        //
        it= new Intent(UserProfile.this,UserAlter.class);
        startActivity(it);
    }

    public void sair(View view) {
        it= new Intent(UserProfile.this,UserLogin.class);
        startActivity(it);
    }

    public void excluirFoto(View view) {


    }

    /**
     *  Menu Activity
     * **/

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

}