package com.example.kampusku.dashboard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kampusku.R;
import com.example.kampusku.helper.DBHelper;

public class UpdateData extends AppCompatActivity {

    DBHelper dbHelper;
    EditText etNomor, etNama, etTanggalLahir, etJenisKelamin, etAlamat;
    Button btSimpan;
    String namaOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Data");
        setContentView(R.layout.activity_update_data);

        dbHelper = new DBHelper(this);
        etNomor = findViewById(R.id.etNomor);
        etNama = findViewById(R.id.etNama);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        etJenisKelamin = findViewById(R.id.etJenisKelamin);
        etAlamat = findViewById(R.id.etAlamat);
        btSimpan = findViewById(R.id.btSimpan);

        Intent intent = getIntent();
        if (intent.hasExtra("nama")) {
            namaOld = intent.getStringExtra("nama");
            ambilDataUntukUpdate(namaOld);
        }

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    private void ambilDataUntukUpdate(String nama) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM data WHERE nama = ?", new String[]{nama});
        if (cursor.moveToFirst()) {
            etNomor.setText(cursor.getString(cursor.getColumnIndexOrThrow("nomor")));
            etNama.setText(cursor.getString(cursor.getColumnIndexOrThrow("nama")));
            etTanggalLahir.setText(cursor.getString(cursor.getColumnIndexOrThrow("tanggallahir")));
            etJenisKelamin.setText(cursor.getString(cursor.getColumnIndexOrThrow("jeniskelamin")));
            etAlamat.setText(cursor.getString(cursor.getColumnIndexOrThrow("alamat")));
        }
        cursor.close();
    }

    private void updateData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String nomor = etNomor.getText().toString().trim();
        String nama = etNama.getText().toString().trim();
        String tanggalLahir = etTanggalLahir.getText().toString().trim();
        String jenisKelamin = etJenisKelamin.getText().toString().trim();
        String alamat = etAlamat.getText().toString().trim();

        if (TextUtils.isEmpty(nomor) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(tanggalLahir)
                || TextUtils.isEmpty(jenisKelamin) || TextUtils.isEmpty(alamat)) {
            Toast.makeText(getApplicationContext(), "Harap isi semua field", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("nomor", nomor);
        values.put("nama", nama);
        values.put("tanggallahir", tanggalLahir);
        values.put("jeniskelamin", jenisKelamin);
        values.put("alamat", alamat);

        int rowsAffected = db.update("data", values, "nama = ?", new String[]{namaOld});

        if (rowsAffected > 0) {
            Toast.makeText(getApplicationContext(), "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
        }
    }
}
