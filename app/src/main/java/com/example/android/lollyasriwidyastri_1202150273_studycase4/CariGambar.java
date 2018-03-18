package com.example.android.lollyasriwidyastri_1202150273_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import java.io.InputStream;
import java.net.URL;

public class CariGambar extends AppCompatActivity {

    //deklarasi atribut yang akan di gunakan
    private EditText etInputLink;
    private Button ButtonCari;
    private ImageView ivTampilGambar;
    private ProgressDialog mProgressDialog;


//masukkan <uses-permission android:name="android.permission.INTERNET"/> kedalam manifest

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_cari_gambar);
        //mengambil atribut yang akan digunakan
        etInputLink = findViewById(R.id.inputLink);
        ButtonCari = findViewById(R.id.buttonCari);
        ivTampilGambar = findViewById(R.id.tampilGambar);
    }

    public void klikCari(View view) {
        loadImageInit();
    } //method untuk mencari gambar

    private void loadImageInit(){
        String ImgUrl = etInputLink.getText().toString();
        //AsyncTask mencari gambar di internet
        new loadImage().execute(ImgUrl);
    }

    private class loadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Membuat Progress Dialog
            mProgressDialog = new ProgressDialog(CariGambar.this);

           // Judul Progress Dialog
            //mProgressDialog.setTitle("Downloading image");

            // Seting message Progress Dialog
            mProgressDialog.setMessage("Loading...");

            // menampilkan Progress Dialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url
                URL url = new URL(params[0]);
                // mengkonversikan gambar ke bitmat (decode to bitmap)
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // menampung gambar ke imageView dan menampilkannya
            ivTampilGambar.setImageBitmap(bitmap);

            // menghilangkan Progress Dialog
            mProgressDialog.dismiss();
        }
    }
}
