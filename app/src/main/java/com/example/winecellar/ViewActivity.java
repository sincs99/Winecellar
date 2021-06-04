package com.example.winecellar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    RecyclerView rvWines;
    DatabaseHelper myDb;
    ImageView backButton;
    WinesAdapter winesAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Wine> wineList = new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        backButton = findViewById(R.id.backbtn);

        searchView = findViewById(R.id.action_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                winesAdapter.getFilter().filter(newText);


                return false;
            }
        });

        backButton.setOnClickListener((View v) -> {
                finish();
        });





        myDb = new DatabaseHelper(this);
        wineList = myDb.getAllWines();
        rvWines = findViewById(R.id.rvWines);
        rvWines.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvWines.setLayoutManager(layoutManager);
        winesAdapter = new WinesAdapter(this, wineList, rvWines);
        rvWines.setAdapter(winesAdapter);


    }




}