package com.aa183.firmansyah;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter <BookAdapter.BookViewHolder> {

    private Context context;
    private ArrayList<Book> dataBook;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public BookAdapter(Context context, ArrayList<Book> dataBook) {
        this.context = context;
        this.dataBook = dataBook;
    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {
        Book tempBook = dataBook.get(position);

        holder.idBook = tempBook.getIdBook();
        holder.gambar = tempBook.getGambarBook();
        holder.tvJudul.setText(tempBook.getJudulBook());
        holder.tvheadPenulis.setText(tempBook.getPenulis());
        holder.genre = tempBook.getGenre();
        holder.tanggal = sdFormat.format(tempBook.getTanggal());
        holder.isiSinopsis = tempBook.getSinopsisBook();
        try {
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgBook.setImageBitmap(bitmap);
            holder.imgBook.setContentDescription(holder.gambar);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "GAGAL Mengambil Gambar dari Media Penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataBook.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public int idBook;
        private ImageView imgBook;
        private TextView tvJudul, tvheadPenulis;
        private String gambar, tanggal, genre, isiSinopsis;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.iv_book);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvheadPenulis = itemView.findViewById(R.id.tv_penulis);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent BukaBook = new Intent(context, TampilBook.class);

            BukaBook.putExtra("ID", idBook);
            BukaBook.putExtra("GAMBAR", gambar);
            BukaBook.putExtra("JUDUL", tvJudul.getText());
            BukaBook.putExtra("PENULIS", tvheadPenulis.getText());
            BukaBook.putExtra("GENRE", genre);
            BukaBook.putExtra("TANGGAL", tanggal);
            BukaBook.putExtra("ISI_SINOPSIS",isiSinopsis);

            context.startActivity(BukaBook);
        }

        @Override
        public boolean onLongClick(View v){
            Intent BukaInput = new Intent(context, InputBookActivity.class);

            BukaInput.putExtra("OPERASI","update");
            BukaInput.putExtra("ID", idBook);
            BukaInput.putExtra("GAMBAR", gambar);
            BukaInput.putExtra("JUDUL", tvJudul.getText());
            BukaInput.putExtra("PENULIS", tvheadPenulis.getText());
            BukaInput.putExtra("GENRE", genre);
            BukaInput.putExtra("TANGGAL", tanggal);
            BukaInput.putExtra("ISI_SINOPSIS", isiSinopsis);

            context.startActivity(BukaInput);

            return true;
        }
    }
}
