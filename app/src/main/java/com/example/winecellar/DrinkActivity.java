package com.example.winecellar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {

    RecyclerView rvWinesDrink;
    DatabaseHelper myDb;
    ImageView backButton;
    WinesAdapterDrink winesAdapterDrink;
    RecyclerView.LayoutManager layoutManager;
    List<Wine> wineList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myDb = new DatabaseHelper(this);
        wineList = myDb.getAllWines();
        rvWinesDrink = findViewById(R.id.rvWinesDrink);
        rvWinesDrink.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvWinesDrink.setLayoutManager(layoutManager);
        winesAdapterDrink = new WinesAdapterDrink(this, wineList, rvWinesDrink);
        rvWinesDrink.setAdapter(winesAdapterDrink);

    }
}