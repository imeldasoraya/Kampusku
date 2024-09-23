package com.example.kampusku.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kampusku.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS data (" +
                "nomor INTEGER PRIMARY KEY, " +
                "nama TEXT, " +
                "tanggallahir TEXT, " +
                "jeniskelamin TEXT, " +
                "alamat TEXT);";
        db.execSQL(sql);

        sql = "INSERT INTO data (nomor, nama, tanggallahir, jeniskelamin, alamat) " +
                "VALUES (1001, 'Imelda Soraya', '10-01-2006', 'Perempuan', 'Banjarmasin');";
        db.execSQL(sql);

        Log.d("DBHelper", "Database dan tabel 'data' berhasil dibuat, dan data awal telah ditambahkan.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS data");
        onCreate(db);
    }
}
