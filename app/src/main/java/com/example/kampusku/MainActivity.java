package com.example.kampusku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kampusku.dashboard.Informasi;
import com.example.kampusku.dashboard.InputData;
import com.example.kampusku.dashboard.LihatData;

public class MainActivity extends AppCompatActivity {
    private Button btLihatData, btInputData, btInformasi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getSupportActionBar().setTitle("Dashboard");
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btLihatData = findViewById(R.id.btLihatData);
        btInputData = findViewById(R.id.btInputData);
        btInformasi = findViewById(R.id.btInformasi);

        btLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LihatData.class);
                startActivity(intent);
            }
        });

        btInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputData.class);
                startActivity(intent);
            }
        });

        btInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Informasi.class);
                startActivity(intent);
            }
        });
    }

    public void equals() {
    }
}