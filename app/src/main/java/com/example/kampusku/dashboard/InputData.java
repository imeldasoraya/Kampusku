package com.example.kampusku.dashboard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kampusku.R;
import com.example.kampusku.helper.DBHelper;

public class InputData extends AppCompatActivity {

    DBHelper SQLite = new DBHelper(this);
    EditText etNomor, etNama, etTanggalLahir, etJenisKelamin, etAlamat;
    Button btSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Input Data");
        setContentView(R.layout.activity_input_data);

        etNomor = findViewById(R.id.etNomor);
        etNama = findViewById(R.id.etNama);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        etJenisKelamin = findViewById(R.id.etJenisKelamin);
        etAlamat = findViewById(R.id.etAlamat);
        btSimpan = findViewById(R.id.btSimpan);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
                Intent intent = new Intent(InputData.this, LihatData.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void simpanData() {
        SQLiteDatabase db = SQLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nomor", etNomor.getText().toString());
        values.put("nama", etNama.getText().toString());
        values.put("tanggallahir", etTanggalLahir.getText().toString());
        values.put("jeniskelamin", etJenisKelamin.getText().toString());
        values.put("alamat", etAlamat.getText().toString());

        db.insert("data", null, values);
        Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
    }
}
