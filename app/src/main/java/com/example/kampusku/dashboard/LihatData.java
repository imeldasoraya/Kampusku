package com.example.kampusku.dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kampusku.R;
import com.example.kampusku.helper.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LihatData extends AppCompatActivity {

    ListView listView;
    DBHelper SQLite;
    String[] daftar;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Data Mahasiswa");
        setContentView(R.layout.activity_lihat_data);

        SQLite = new DBHelper(getApplicationContext());
        listView = findViewById(R.id.list_view);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(LihatData.this, InputData.class);
            startActivity(intent);
        });

        RefreshList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = SQLite.getReadableDatabase();
        cursor = db.rawQuery("SELECT nama FROM data", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0);
        }

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            final String selection = daftar[position];
            final CharSequence[] dialogitem = {"Lihat Data", "Update Data", "Hapus Data"};
            AlertDialog.Builder builder = new AlertDialog.Builder(LihatData.this);
            builder.setTitle("Pilihan");
            builder.setItems(dialogitem, (dialog, item) -> {
                switch (item) {
                    case 0:
                        Intent i = new Intent(getApplicationContext(), DetailData.class);
                        i.putExtra("nama", selection);
                        startActivity(i);
                        break;
                    case 1:
                        Intent in = new Intent(getApplicationContext(), UpdateData.class);
                        in.putExtra("nama", selection);
                        startActivity(in);
                        break;
                    case 2:
                        SQLiteDatabase db1 = SQLite.getWritableDatabase();
                        db1.execSQL("DELETE FROM data WHERE nama = '" + selection + "'");
                        Toast.makeText(LihatData.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        RefreshList();
                        break;
                }
            });
            builder.create().show();
        });
    }
}
