package com.gabys.frontend.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.ExportPropertiesController;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class ExportPropertiesActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton csvButton;
    private ExtendedFloatingActionButton jsonButton;
    private ExtendedFloatingActionButton xmlButton;
    private ExtendedFloatingActionButton txtButton;
    private ExtendedFloatingActionButton backButton;

    private void initComponents(){
        setContentView(R.layout.activity_export_properties);
        csvButton = findViewById(R.id.csv_fab);
        jsonButton = findViewById(R.id.json_fab);
        xmlButton = findViewById(R.id.xml_fab);
        txtButton = findViewById(R.id.txt_fab);
        backButton = findViewById(R.id.back_fab);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();

        new ExportPropertiesController(this);
    }

    public ExtendedFloatingActionButton getCsvButton() {
        return csvButton;
    }

    public ExtendedFloatingActionButton getJsonButton() {
        return jsonButton;
    }

    public ExtendedFloatingActionButton getXmlButton() {
        return xmlButton;
    }

    public ExtendedFloatingActionButton getTxtButton() {
        return txtButton;
    }

    public ExtendedFloatingActionButton getBackButton() {
        return backButton;
    }
}
