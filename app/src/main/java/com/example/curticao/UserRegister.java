package com.example.curticao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

public class UserRegister extends AppCompatActivity {

    private ImageView imgFotoPerfil;
    private EditText edtNome, edtIdade, edtTelefone, edtEmail, edtSenha, edtCidade, edtSlogan;

    private static final int PERMISSION_REQUEST_CODE = 200;
    private int GALLERY = 1, CAMERA = 2;

    byte foto[];

    private static final String IMAGE_DIRECTORY = "/curticao";

    TableCurticaoHelper userRegister = new TableCurticaoHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        imgFotoPerfil = findViewById(R.id.imgFotoPerfil);
        edtNome       = findViewById(R.id.edtNome);
        edtIdade      = findViewById(R.id.edtIdade);
        edtTelefone   = findViewById(R.id.edtTelefone);
        edtEmail      = findViewById(R.id.edtEmail);
        edtSenha      = findViewById(R.id.edtSenha);
        edtCidade     = findViewById(R.id.edtCidade);
        edtSlogan     = findViewById(R.id.edtSlogan);


    }


    public void tirarFoto(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }



    public void carregarFoto(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermission()) {
            // Success
        } else {
            requestPermission();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgFotoPerfil.setImageBitmap(resizeImage(bitmap, 600, 700));
            saveImage(bitmap);
            Toast.makeText(UserRegister.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }else if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                            contentURI);
                    // Salvando a imagem
                    String path = saveImage(bitmap);
                    Log.i("TAG","Path: " + path);
                    Toast.makeText(UserRegister.this, "Image Saved!",
                            Toast.LENGTH_SHORT).show();
                    // Print on screen
                    imgFotoPerfil.setImageBitmap(resizeImage(bitmap, 600, 700));
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(UserRegister.this, "Failed!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public String saveImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        byte arrayImage[] = bytes.toByteArray();
        foto = arrayImage;

        File directory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // Criando o diretório caso ele não exista!
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            File fileImage = new File(directory, Calendar.getInstance().getTimeInMillis() +
                    ".jpg");
            fileImage.createNewFile();
            FileOutputStream fo = new FileOutputStream(fileImage);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{fileImage.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved:->" + fileImage.getAbsolutePath());
            return fileImage.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap output = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,  Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted",
                            Toast.LENGTH_SHORT).show();
                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(UserRegister.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void registrar(View view) {

       ByteArrayOutputStream imagem =  new ByteArrayOutputStream();

       String nome     =  edtNome.getText().toString();
       int    idade    =  Integer.parseInt(edtIdade.getText().toString());
       String telefone =  edtTelefone.getText().toString();
       String email    =  edtEmail.getText().toString();
       String senha    =  edtSenha.getText().toString();
       String cidade   =  edtCidade.getText().toString();
       String slogan   =  edtSlogan.getText().toString();

       User usuario    = new User();
       Foto fotoPerfil = new Foto();

       usuario.setNome(nome);
       usuario.setIdade(idade);
       usuario.setTelefone(telefone);
       usuario.setEmail(email);
       usuario.setSenha(senha);
       usuario.setCidade(cidade);
       usuario.setSlogan(slogan);
       fotoPerfil.setFoto(foto);

       userRegister.insertUser(usuario,fotoPerfil);

       Toast.makeText(getBaseContext(),"Dados Cadastrados com Suscesso.", Toast.LENGTH_SHORT).show();

       Intent it=new Intent(UserRegister.this,UserLogin.class);
       startActivity(it);
    }
}