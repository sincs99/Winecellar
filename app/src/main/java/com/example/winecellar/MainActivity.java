package com.example.winecellar;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    ImageButton addBtn, viewBtn, reportBtn, drinkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);
        reportBtn = findViewById(R.id.reportBtn);
        drinkBtn = findViewById(R.id.drinkBtn);



        addBtn.setOnClickListener((View v) -> {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

        });
        viewBtn.setOnClickListener((View v) -> {

                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(intent);

        });
        reportBtn.setOnClickListener((View v) -> {

                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                startActivity(intent);
        });
        drinkBtn.setOnClickListener((View v) -> {

                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);

        });
    }
}