package com.example.kampusku.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kampusku.R;
import com.example.kampusku.helper.DBHelper;

public class DetailData extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper dbHelper;
    Button btKembali;
    TextView tvNomor, tvNama, tvTanggalLahir, tvJenisKelamin, tvAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Detail Data");
        setContentView(R.layout.activity_detail_data);

        dbHelper = new DBHelper(this);
        tvNomor=(TextView) findViewById(R.id.tvNomor);
        tvNama=(TextView) findViewById(R.id.tvNama);
        tvTanggalLahir=(TextView) findViewById(R.id.tvTanggalLahir);
        tvJenisKelamin=(TextView) findViewById(R.id.tvJenisKelamin);
        tvAlamat=(TextView) findViewById(R.id.tvAlamat);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data WHERE nama = '" + getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            tvNomor.setText(cursor.getString(0).toString());
            tvNama.setText(cursor.getString(1).toString());
            tvTanggalLahir.setText(cursor.getString(2).toString());
            tvJenisKelamin.setText(cursor.getString(3).toString());
            tvAlamat.setText(cursor.getString(4).toString());
        }
        btKembali = (Button) findViewById(R.id.btKembali);
        btKembali.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            finish();
        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
