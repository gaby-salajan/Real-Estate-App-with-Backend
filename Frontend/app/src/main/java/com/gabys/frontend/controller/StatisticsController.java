package com.gabys.frontend.controller;

import android.graphics.Color;

import com.gabys.frontend.R;
import com.gabys.frontend.model.PropertiesList;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.view.StatisticsActivity;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.util.ArrayList;

public class StatisticsController {

    private final StatisticsActivity activity;
    private final ArrayList<Property> propertiesList;

    public StatisticsController(StatisticsActivity activity){
        this.activity = activity;
        this.propertiesList = (new Gson().fromJson(activity.getIntent().getStringExtra("properties"), PropertiesList.class)).getPropertiesList();

        setupTypeChart();
        setupBarChart();
    }

    private void setupTypeChart(){

        ArrayList<PieEntry> chartEntries = new ArrayList<>();

        int sumApartment = 0;
        int sumHouse = 0;

        sumApartment = (int) propertiesList.stream().filter(property -> property.getType() == 0).count();
        sumHouse = (int) propertiesList.stream().filter(property -> property.getType() == 1).count();


        chartEntries.add(new PieEntry(sumApartment, activity.getString(R.string.type_apartment)));
        chartEntries.add(new PieEntry(sumHouse, activity.getString(R.string.type_house)));


        PieDataSet chartDataset = new PieDataSet(chartEntries, activity.getString(R.string.property_type));
        chartDataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chartDataset.setValueTextColor(Color.BLACK);
        chartDataset.setValueTextSize(14f);


        PieData chartData = new PieData(chartDataset);

        activity.getTypeChart().setData(chartData);
        activity.getTypeChart().getDescription().setEnabled(false);
        activity.getTypeChart().setCenterText(activity.getString(R.string.property_type));
    }

    private void setupBarChart(){

        ArrayList<BarEntry> mediumPriceAp = new ArrayList<>();
        ArrayList<BarEntry> mediumPriceHouse = new ArrayList<>();

        float apartmentAvg = (float) propertiesList.stream().filter(p -> p.getType() == 0).mapToDouble(Property::getPrice).average().getAsDouble();
        float houseAvg = (float) propertiesList.stream().filter(p -> p.getType() == 1).mapToDouble(Property::getPrice).average().getAsDouble();

        mediumPriceAp.add(new BarEntry(0, apartmentAvg));
        mediumPriceHouse.add(new BarEntry(1, houseAvg));

        BarDataSet dataSetAp = new BarDataSet(mediumPriceAp, activity.getString(R.string.avg_price_ap));
        dataSetAp.setColor(ColorTemplate.COLORFUL_COLORS[1]);
        dataSetAp.setValueTextColor(Color.BLACK);
        dataSetAp.setValueTextSize(14f);

        BarDataSet dataSetHouse = new BarDataSet(mediumPriceHouse, activity.getString(R.string.avg_price_house));
        dataSetHouse.setColor(ColorTemplate.COLORFUL_COLORS[2]);
        dataSetHouse.setValueTextColor(Color.BLACK);
        dataSetHouse.setValueTextSize(14f);


        BarData chartData = new BarData(dataSetAp, dataSetHouse);

        activity.getBarChart().setFitBars(true);
        activity.getBarChart().setData(chartData);
        //activity.getBarChart().groupBars(0.0f, 0.06f, 0.02f);
        activity.getBarChart().getDescription().setEnabled(false);
        activity.getBarChart().invalidate();

    }

}
