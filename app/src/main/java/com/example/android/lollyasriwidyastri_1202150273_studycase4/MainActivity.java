package com.example.android.lollyasriwidyastri_1202150273_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void listNama(View view) {
        Intent list = new Intent(MainActivity.this,ListNama.class); //pindah ke class list nama
        startActivity(list); //memulai pindah ke class list nama
    }

    public void cariGambar(View view) {
        Intent cari = new Intent(MainActivity.this,CariGambar.class); //pindah ke class cari gambar
        startActivity(cari); //memulai pindah ke class cari gambar
    }
}
