package com.example.curticao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TableCurticaoHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "CurticaoDatabaseTeste";



    private static  String USER_QUERY      = "CREATE TABLE user(               " +
                                             "foto  BLOB NOT NULL,                      " +
                                             "email TEXT PRIMARY KEY NOT NULL, " +
                                             "nome TEXT NOT NULL,              " +
                                             "idade INTEGER NOT NULL,          " +
                                             "telefone TEXT NOT NULL,          " +
                                             "senha TEXT NOT NULL,             " +
                                             "cidade TEXT NOT NULL,            " +
                                             "slogan TEXT NOT NULL             " +
                                                                               ");";

    private static  String FOTO_QUERY      = "CREATE TABLE foto(                        " +
                                             "codFoto INTEGER PRIMARY KEY AUTOINCREMENT," +
                                             "foto  BLOB NOT NULL,                      " +
                                             "nome TEXT NOT NULL,                       " +
                                             "cidade TEXT NOT NULL,                     " +
                                             "Slogan TEXT NOT NULL,                     " +
                                             "legenda TEXT NOT NULL,                    " +
                                             "email   TEXT NOT NULL                     " +
                                                                                        ");";

    private static String AVALIACAO_QUERY = "CREATE TABLE avaliacao(           " +
                                            "codFoto INTEGER NOT NULL,         " +
                                            "email TEXT NOT NULL,              " +
                                            "curti INTEGER NOT NULL,           " +
                                            "bom INTEGER NOT NULL,             " +
                                            "naoGostei INTEGER NOT NULL,       " +
                                            "FOREIGN KEY (codFoto)                " +
                                            "REFERENCES foto(codFoto)             " +
                                            "ON DELETE CASCADE                 " +
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

    /*
     *  CRUD User
     * */

    // Insert User
    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesUser = new ContentValues();

        valuesUser.put("foto",user.getFoto());
        valuesUser.put("nome",user.getNome());
        valuesUser.put("idade",user.getIdade());
        valuesUser.put("telefone",user.getTelefone());
        valuesUser.put("email",user.getEmail());
        valuesUser.put("senha",user.getSenha());
        valuesUser.put("cidade",user.getCidade());
        valuesUser.put("slogan",user.getSlogan());

        db.insert("user",null, valuesUser);

        db.close();
    }

    // Search User
    public boolean searchUser(User user){

        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT email, senha FROM user";
        Cursor cursor=db.rawQuery(query,null);


        if(cursor.moveToFirst()){
            do{
                String emailTable    = cursor.getString(0);
                String passwordTable = cursor.getString(1);
                if(emailTable.equals(user.getEmail()) && passwordTable.equals(user.getSenha())){
                    db.close();
                    return true;
                }
            }while (cursor.moveToNext());
        }
        db.close();
        return false;
    }

    // Search Date User
    public User searchDateUser(String email){

        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT nome, idade, telefone, email, senha, cidade, slogan,foto FROM user";
        Cursor cursor=db.rawQuery(query,null);

        User u = new User();

        if(cursor.moveToFirst()){
            do{
                String emailTable = cursor.getString(3);
                if(emailTable.equals(email)){

                    u.setNome(    cursor.getString(0));
                    u.setIdade(      cursor.getInt(1));
                    u.setTelefone(cursor.getString(2));
                    u.setEmail(   cursor.getString(3));
                    u.setSenha(   cursor.getString(4));
                    u.setCidade(  cursor.getString(5));
                    u.setSlogan(  cursor.getString(6));
                    u.setFoto(      cursor.getBlob(7));
                }
            }while (cursor.moveToNext());
            db.close();
            return u;
        }
        db.close();
        return null;
    }

    // Search Foto User
    public User searchDateFoto(String email){

        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT email,foto FROM user";
        Cursor cursor=db.rawQuery(query,null);

        User u = new User();

        if(cursor.moveToFirst()){
            do{
                String emailTable = cursor.getString(0);
                if(emailTable.equals(email)){
                    u.setFoto(cursor.getBlob(1));
                }
            }while (cursor.moveToNext());
            db.close();
            return u;
        }
        db.close();
        return null;
    }


    // Update User
    public boolean alterarDadosCurso(User user){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values  = new ContentValues();


        values.put("foto",user.getFoto()); // ERRO! Aqui deverá ser convertido a byte array...
        values.put("nome",user.getNome());
        values.put("idade",user.getIdade());
        values.put("telefone",user.getTelefone());
        values.put("email",user.getEmail());
        values.put("senha",user.getSenha());
        values.put("cidade",user.getCidade());
        values.put("slogan",user.getSlogan());

        db.update("user",values,"cursoID = ?",new String[]{ user.getEmail() });

        db.close();

        return true;
    }

    public boolean deleteUser(String email){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete("user","email=?",new String[]{ email });

        return true;
    }



    /*
    *  CRUD foto
    * */
    
    // Insert Foto
    public void insertFoto(Foto f){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("foto",f.getFoto());
        values.put("nome",f.getNome());
        values.put("cidade",f.getCidade());
        values.put("Slogan",f.getSlogan());
        values.put("legenda",f.getLegenda());
        values.put("email",f.getEmail());

        db.insert("foto",null, values);

        // Buscando os ultimos dados para inserir na tabela de avaliação

        String query = "SELECT codFoto,email FROM foto";
        Cursor cursor = db.rawQuery(query,null);

        values = new ContentValues();

        cursor.moveToLast();
        values.put("codFoto",cursor.getInt(0));
        values.put("email",cursor.getString(1));
        values.put("curti",0);
        values.put("bom",0);
        values.put("naoGostei",0);

        db.insert("avaliacao",null,values);

        db.close();
    }

    // Search all Foto
    public ArrayList<Foto> searchAllFotos(){

        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Foto> fotos = new ArrayList<Foto>();
        Foto f;

        String query = "SELECT foto,nome,cidade,Slogan,legenda,email FROM foto";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                f = new Foto();

                User u = searchDateFoto(cursor.getString(5));
                f.setFotoPerfil(u.getFoto());

                f.setFoto(cursor.getBlob(0));
                f.setNome(cursor.getString(1));
                f.setCidade(cursor.getString(2));
                f.setSlogan(cursor.getString(3));
                f.setLegenda(cursor.getString(4));
                f.setEmail(cursor.getString(5));

                fotos.add(f);

            }while (cursor.moveToNext());
        }
        db.close();
        return fotos;
    }



    public boolean deleteFoto(String email){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete("foto","email=?",new String[]{ email });

        return true;
    }

    /*
     *  CRUD Avaliação
     * */

    public ArrayList<Avaliacao> searchAllavaliacoes(){

        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
        Avaliacao a;

        String query = "SELECT codFoto, email,curti, bom, naoGostei FROM avaliacao";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                a = new Avaliacao();

                a.setCodFoto(cursor.getInt(0));
                a.setEmail(cursor.getString(1));
                a.setCurti(cursor.getInt(2));
                a.setBom(cursor.getInt(3));
                a.setNaoGostei(cursor.getInt(4));

                avaliacoes.add(a);

            }while (cursor.moveToNext());
        }
        db.close();
        return avaliacoes;
    }

    public void searchInsertCurti(int codAvaliacao, int curti){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value  = new ContentValues();

        value.put("curti",curti);

        db.update("avaliacao",value,"codFoto = "+curti,null);

        db.close();

    }

    public void searchInsertGostei(int codAvaliacao, int gostei){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value  = new ContentValues();


        value.put("curti",gostei);


        db.update("avaliacao",value,"codFoto = "+gostei,null);

        db.close();
    }

    public void searchInsertNaoGostei(int codAvaliacao, int naoGostei){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value  = new ContentValues();

        value.put("curti",naoGostei);

        db.update("avaliacao",value,"codFoto = "+naoGostei,null);

        db.close();
    }

}
