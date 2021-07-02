package com.example.curticao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class CurticaoArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Foto> elementos;
    private final ArrayList<Avaliacao> avaliacao;



    public CurticaoArrayAdapter(Context context, ArrayList<Foto> elementos, ArrayList<Avaliacao> avaliacao) {
        super(context, R.layout.activity_main_page, elementos);
        this.context = context;
        this.elementos = elementos;
        this.avaliacao = avaliacao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main_page, parent,false);

        // Elementos da classe Foto

        ImageView imgCurticaoPhotoPerfil   =  rowView.findViewById(R.id.imgCurticaoPhotoPerfil);
        TextView  txtCurticaoNomePerfil    =  rowView.findViewById(R.id.txtCurticaoNomePerfil);
        TextView  txtCurticaoCidadePerfil  =  rowView.findViewById(R.id.txtCurticaoCidadePerfil);
        TextView  txtCurticaoSloganPerfil  =  rowView.findViewById(R.id.txtCurticaoSloganPerfil);
        ImageView imgCurticaoPhoto         =  rowView.findViewById(R.id.imgCurticaoPhoto);
        TextView  txtCurticaoLegend        =  rowView.findViewById(R.id.txtCurticaoLegend);

        // Elementos da classe Avaliação

        ImageButton btnCutir     = rowView.findViewById(R.id.btnCutir) ;
        ImageButton btnBom       = rowView.findViewById(R.id.btnBom) ;
        ImageButton btnNaoCurtir = rowView.findViewById(R.id.btnNaoCurtir);

        // Método para curtir nos botões
        btnCutir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = 1;
            }
        });

        btnBom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = 1;

            }
        });

        btnNaoCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int values = 1;
            }
        });




        // Foto de Perfil
        byte[] imgP = elementos.get(position).getFotoPerfil();
        Bitmap bmpP = BitmapFactory.decodeByteArray(imgP,0,imgP.length);
        imgCurticaoPhoto.setImageBitmap(bmpP);

        imgCurticaoPhotoPerfil.setImageBitmap(bmpP);

        // Nome User
        txtCurticaoNomePerfil.setText(elementos.get(position).getNome());
        // Cidade User
        txtCurticaoCidadePerfil.setText(elementos.get(position).getCidade());
        // Slogan User
        txtCurticaoSloganPerfil.setText(elementos.get(position).getSlogan());
        // Foto a ser curtida
        byte[] img = elementos.get(position).getFoto();
        Bitmap bmp = BitmapFactory.decodeByteArray(img,0,img.length);
        imgCurticaoPhoto.setImageBitmap(bmp);
        // Legenda da Foto a ser curtida
        txtCurticaoLegend.setText(elementos.get(position).getLegenda());

        return rowView;
    }
}
