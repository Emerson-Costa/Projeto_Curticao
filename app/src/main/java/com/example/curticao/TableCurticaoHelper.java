package com.example.curticao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TableCurticaoHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "CurticaoDatabase";



    private static  String USER_QUERY      = "CREATE TABLE user(               " +
                                             "email TEXT PRIMARY KEY NOT NULL, " +
                                             "nome TEXT NOT NULL,              " +
                                             "idade INTEGER NOT NULL,          " +
                                             "telefone TEXT NOT NULL,          " +
                                             "senha TEXT NOT NULL,             " +
                                             "cidade TEXT NOT NULL,            " +
                                             "slogan TEXT NOT NULL             " +
                                                                               ");";

    private static  String FOTO_QUERY      = "CREATE TABLE foto(               " +
                                             "email TEXT NOT NULL,             " +
                                             "foto BLOB NOT NULL,              " +
                                             "legenda TEXT NOT NULL,           " +
                                             "FOREIGN KEY (email)              " +
                                             "REFERENCES user(email)           " +
                                             "ON DELETE CASCADE                " +
                                                                               ");";

    private static String AVALIACAO_QUERY = "CREATE TABLE avaliacao(           " +
                                            "email TEXT NOT NULL,              " +
                                            "curti INTEGER NOT NULL,           " +
                                            "bom INTEGER NOT NULL,             " +
                                            "naoGostei INTEGER NOT NULL,       " +
                                            "FOREIGN KEY (email)               " +
                                            "REFERENCES foto(email)            " +
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

    // INSERT
    public void insertUser(User user, Foto foto){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuesUser = new ContentValues();
        ContentValues valuesFoto  = new ContentValues();

        valuesUser.put("nome",user.getNome());
        valuesUser.put("idade",user.getIdade());
        valuesUser.put("telefone",user.getTelefone());
        valuesUser.put("email",user.getEmail());
        valuesUser.put("senha",user.getSenha());
        valuesUser.put("cidade",user.getCidade());
        valuesUser.put("slogan",user.getSlogan());

        db.insert("user",null, valuesUser);

        valuesFoto.put("email",user.getEmail());
        valuesFoto.put("foto",foto.getFoto());
        valuesFoto.put("legenda","foto de perfil");

        db.insert("foto",null, valuesFoto);

        db.close();
    }

    // GET

    // Buscando email
    public String searchUser(String email){

        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT email FROM user";
        Cursor cursor=db.rawQuery(query,null);


        if(cursor.moveToFirst()){
            do{
                String emailTable=cursor.getString(0);
                if(emailTable.equals(email)){
                    return cursor.getString(       0);
                }
            }while (cursor.moveToNext());
        }
        return "Nao Encontrado";
    }

    // Buscando dados User
    public User searchDateUser(String email){

        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT nome, idade, telefone, email, senha, cidade, slogan FROM user";
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
                }
            }while (cursor.moveToNext());
            return u;
        }
        return null;
    }

    public Foto searchDateFoto(String email){

        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT foto FROM foto";
        Cursor cursor=db.rawQuery(query,null);

        Foto f = new Foto();

        if(cursor.moveToFirst()){
            do{
                String emailTable = cursor.getString(3);
                if(emailTable.equals(email)){
                    f.setFoto(cursor.getBlob(0));
                }
            }while (cursor.moveToNext());
            return f;
        }
        return null;
    }



    // Autenticação
    public boolean searchUser(User user){

        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT email, senha FROM user";
        Cursor cursor=db.rawQuery(query,null);


        if(cursor.moveToFirst()){
            do{
                String emailTable    = cursor.getString(0);
                String passwordTable = cursor.getString(1);
                if(emailTable.equals(user.getEmail()) && passwordTable.equals(user.getSenha())){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }

    // UPDATE
    public boolean alterarDadosCurso(User user, Foto foto){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues valuesUser = new ContentValues();
        ContentValues valuesFoto  = new ContentValues();

        valuesUser.put("nome",user.getNome());
        valuesUser.put("idade",user.getIdade());
        valuesUser.put("telefone",user.getTelefone());
        valuesUser.put("email",user.getEmail());
        valuesUser.put("senha",user.getSenha());
        valuesUser.put("cidade",user.getCidade());
        valuesUser.put("slogan",user.getSlogan());

        db.update("user",valuesUser,"cursoID = ?",new String[]{ user.getEmail() });

        valuesFoto.put("foto",foto.getFoto());

        db.update("foto",valuesUser,"cursoID = ?",new String[]{ user.getEmail() });

        db.close();

        return true;
    }

    // DELETE
    public boolean deleteUser(String email){
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete("user","email=?",new String[]{ email });

        return true;
    }



    /*
    *  CRUD foto
    * */
    
    // INSERT
    public void insertFoto(Foto foto, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("foto",foto.getFoto());
        values.put("legenda",foto.getLegenda());

        db.insert("foto",null, values);
        db.close();
    }

    // GET

    // UPDATE

    // DELETE

    /*
     *  CRUD Avaliação
     * */
    public void insertAvaliacao(Foto f, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("foto",f.getFoto());
        values.put("legenda",f.getLegenda());

        db.insert("foto",null, values);
        db.close();
    }

}
