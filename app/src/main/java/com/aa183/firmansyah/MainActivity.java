package com.aa183.firmansyah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Book> dataBook = new ArrayList<>();
    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private DataBaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBook = findViewById(R.id.rv_tampil_book);
        dbHandler = new DataBaseHandler(this);
        tampilDataBook();

    }

    private void tampilDataBook(){
        dataBook = dbHandler.getAllBook();
        bookAdapter = new BookAdapter(this,dataBook);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
        rvBook.setLayoutManager(layoutManager);
        rvBook.setAdapter(bookAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.item_menu_tambah){
            Intent BukaInput = new Intent(getApplicationContext(), InputBookActivity.class);
            BukaInput.putExtra("OPERASI", "insert");
            startActivity(BukaInput);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilDataBook();
    }

}
