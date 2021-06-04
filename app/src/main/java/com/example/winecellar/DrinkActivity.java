package com.example.winecellar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {

    RecyclerView rvWinesDrink;
    DatabaseHelper myDb;
    ImageView backButton;
    WinesAdapterDrink winesAdapterDrink;
    RecyclerView.LayoutManager layoutManager;
    List<Wine> wineList = new ArrayList<>();
    SearchView searchView;



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

        searchView = findViewById(R.id.drink_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                winesAdapterDrink.getDrinkFilter().filter(newText);


                return false;
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