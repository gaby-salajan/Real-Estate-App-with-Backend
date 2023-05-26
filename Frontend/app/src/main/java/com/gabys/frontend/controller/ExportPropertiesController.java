package com.gabys.frontend.controller;

import android.widget.Toast;

import com.gabys.frontend.R;
import com.gabys.frontend.model.PropertiesList;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.export.ExporterFactory;
import com.gabys.frontend.model.export.IExporter;
import com.gabys.frontend.view.ExportPropertiesActivity;
import com.google.gson.Gson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ExportPropertiesController {

    private final ExportPropertiesActivity activity;
    private ArrayList<Property> propertiesList;

    private ExporterFactory exporterFactory;

    public ExportPropertiesController(ExportPropertiesActivity exportPropertiesActivity) {
        this.activity = exportPropertiesActivity;
        this.propertiesList = (new Gson().fromJson(activity.getIntent().getStringExtra("properties"), PropertiesList.class)).getPropertiesList();

        this.exporterFactory = new ExporterFactory(activity.getBaseContext(), propertiesList);

        setCSVButtonListener();
        setJSONButtonListener();
        setXMLButtonListener();
        setTXTButtonListener();
        setBackButtonListener();
    }

    private void setBackButtonListener() {
        activity.getBackButton().setOnClickListener(v -> {
            activity.finish();
        });
    }

    private void setTXTButtonListener() {
        activity.getTxtButton().setOnClickListener(v -> {
            exporterFactory.createExporter("txt").exportProperties();

            Toast.makeText(v.getContext(), "TXT exportat cu succes!", Toast.LENGTH_SHORT).show();
        });
    }

    private void setXMLButtonListener() {
        activity.getXmlButton().setOnClickListener(v -> {
            exporterFactory.createExporter("xml").exportProperties();

            Toast.makeText(v.getContext(), "XML exportat cu succes!", Toast.LENGTH_SHORT).show();
        });
    }

    private void setJSONButtonListener() {
        activity.getJsonButton().setOnClickListener(v -> {
            exporterFactory.createExporter("json").exportProperties();

            Toast.makeText(v.getContext(), "JSON exportat cu succes!", Toast.LENGTH_SHORT).show();
        });
    }

    private void setCSVButtonListener() {
        activity.getCsvButton().setOnClickListener(v -> {
            exporterFactory.createExporter("csv").exportProperties();

            Toast.makeText(v.getContext(), "CSV exportat cu succes!", Toast.LENGTH_SHORT).show();
        });
    }


    public void setProperties(ArrayList<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }
}
