package com.example.winecellar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    ImageView backButton;
    EditText editTextName, editTextGrape, editTextPrice, editTextAmount;
    Spinner spinnerYear, spinnerType;
    ImageButton btnAdd, addImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDb = new DatabaseHelper(this);

        editTextName = findViewById(R.id.wineName);
        editTextGrape = findViewById(R.id.grape);
        editTextPrice = findViewById(R.id.price);
        editTextAmount = findViewById(R.id.store);
        spinnerYear = findViewById(R.id.spinnerYear);
        spinnerType = findViewById(R.id.spinnerType);
        btnAdd = findViewById(R.id.addBtn);
        backButton = findViewById(R.id.backbtn);
        addImage = findViewById(R.id.addImage);




        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted =  myDb.insertWine(editTextName.getText().toString(), editTextGrape.getText().toString(),
                                editTextPrice.getText().toString(), editTextAmount.getText().toString(),
                                spinnerYear.getSelectedItem().toString(), spinnerType.getSelectedItem().toString());
                        if (isInserted == true){
                            Toast.makeText(AddActivity.this, "Wine inserted", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(AddActivity.this, "Wine not inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );


    }

}