package com.aa183.firmansyah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilBook extends AppCompatActivity {

    private ImageView imgBook;
    private TextView tvJudul, tvPenulis, tvGenre, tvTanggal, tvIsiSinopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_book);

        imgBook = findViewById(R.id.iv_book);
        tvJudul = findViewById(R.id.tv_judul);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvGenre = findViewById(R.id.tv_genre);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvIsiSinopsis = findViewById(R.id.tv_sinopsis);


        Intent terimaDataBook = getIntent();
        tvJudul.setText(terimaDataBook.getStringExtra("JUDUL"));
        tvPenulis.setText(terimaDataBook.getStringExtra("PENULIS"));
        tvGenre.setText(terimaDataBook.getStringExtra("GENRE"));
        tvTanggal.setText(terimaDataBook.getStringExtra("TANGGAL"));
        tvIsiSinopsis.setText(terimaDataBook.getStringExtra("ISI_SINOPSIS"));
        String imgLocation = terimaDataBook.getStringExtra("GAMBAR");
        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgBook.setImageBitmap(bitmap);
            imgBook.setContentDescription(imgLocation);
        } catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }
}
