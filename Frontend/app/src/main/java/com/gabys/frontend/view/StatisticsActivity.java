package com.gabys.frontend.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.StatisticsController;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

public class StatisticsActivity extends AppCompatActivity {

    private PieChart typeChart;
    private BarChart barChart;
    private void initComponents(){
        setContentView(R.layout.activity_statistics);

        typeChart = findViewById(R.id.type_chart);
        barChart = findViewById(R.id.bar_chart);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        new StatisticsController(this);
    }

    public PieChart getTypeChart(){
        return typeChart;
    }

    public BarChart getBarChart(){
        return barChart;
    }
}
