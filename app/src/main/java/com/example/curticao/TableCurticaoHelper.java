package com.example.curticao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TableCurticaoHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Curticao";



    private static  String USER_QUERY      = "CREATE TABLE user(" +
            "codUser INTEGER PRIMARY KEY NOT NULL, " +
            "nome TEXT NOT NULL," +
            "idade INTEGER NOT NULL," +
            "telefone TEXT NOT NULL," +
            "email TEXT NOT NULL," +
            "senha TEXT NOT NULL," +
            "cidade TEXT NOT NULL," +
            "slogan TEXT NOT NULL)" +
            ";";

    private static  String FOTO_QUERY      = "CREATE TABLE foto(" +
            "codFoto INTEGER PRIMARY KEY NOT NULL," +
            "codUser INTEGER NOT NULL," +
            "foto BLOB NOT NULL," +
            "legenda TEXT NOT NULL," +
            "FOREIGN KEY (codUser)" +
            "REFERENCES user(codUser)" +
            ");";

    private static String AVALIACAO_QUERY = "CREATE TABLE avaliacao(" +
            "codFoto INTEGER NOT NULL," +
            "curti INTEGER NOT NULL," +
            "bom INTEGER NOT NULL," +
            "naoGostei INTEGER NOT NULL," +
            "FOREIGN KEY (codFoto)" +
            "REFERENCES foto(codFoto)" +
            ");";

    public TableCurticaoHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(USER_QUERY);
       db.execSQL(FOTO_QUERY);
       db.execSQL(AVALIACAO_QUERY);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // CRUD User

    // inserir
    public void insertUser(User a, Foto f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome",a.getNome());
        values.put("idade",a.getIdade());
        values.put("telefone",a.getTelefone());
        values.put("email",a.getEmail());
        values.put("senha",a.getSenha());
        values.put("cidade",a.getCidade());
        values.put("slogan",a.getSlogan());
        values.put("foto",f.getFoto());
        values.put("legenda","foto de perfil");

        db.insert("user",null, values);
        db.close();
    }

    // buscar

    // atualizar

    // deletar


    // CRUD Foto
    public void insertFoto(Foto f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("foto",f.getFoto());
        values.put("legenda",f.getLegenda());

        db.insert("foto",null, values);
        db.close();
    }

    // CRUD Avaliacao



}
