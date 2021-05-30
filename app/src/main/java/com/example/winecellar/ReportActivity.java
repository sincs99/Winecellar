package com.example.winecellar;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    ImageView backButton;
    PieChart pieChart;
    DatabaseHelper myDb;
    TextView totalBottlesTv, totalValueTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        myDb = new DatabaseHelper(this);

        backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pieChart = findViewById(R.id.pieChart);
        setupPieChart();
        loadPieChartData();

        totalBottlesTv = findViewById(R.id.totalBottles);
        totalValueTv = findViewById(R.id.valueText);

        setupTextViews();
    }

    private void setupTextViews() {
        int totalBottles = myDb.getTotalAmountOfBottles();
        totalBottlesTv.setText("Total Bottles: " + totalBottles);
        double totalValue = myDb.getTotalValue();
        totalValueTv.setText("Total Value: CHF " + String.format("%.2f", totalValue));
    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(false);
        pieChart.setEntryLabelTextSize(16);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Bottles per Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(true);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
        pieChart.getLegend().setEnabled(false);
    }

    private void loadPieChartData(){
        int redWines = myDb.getAmountOfRedWines();
        int whiteWines = myDb.getAmountOfWhiteWines();
        int roseWines = myDb.getAmountOfRoseWines();
        int sparklingWines = myDb.getAmountOfSparklingWines();

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(redWines, "Red"));
        entries.add(new PieEntry(whiteWines, "White"));
        entries.add(new PieEntry(roseWines, "Rose"));
        entries.add(new PieEntry(sparklingWines, "Sparkling"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(169,99,103)); //red
        colors.add(Color.rgb(237,233,144)); //yellow
        colors.add(Color.rgb(237,195,243)); //rose
        colors.add(Color.rgb(207,229,203)); //sparkling

        PieDataSet dataSet = new PieDataSet(entries, "Wine Categories");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}