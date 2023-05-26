package com.gabys.frontend.model.export;

import android.content.Context;

import com.gabys.frontend.model.Property;

import java.util.ArrayList;

public class ExporterFactory {

    private Context context;
    private ArrayList<Property> propertiesList;

    public ExporterFactory(Context context, ArrayList<Property> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    public IExporter createExporter(String type){
        switch (type){
            case "csv":
                return new ExporterCSV(context, propertiesList);

            case "xml":
                return new ExporterXML(context, propertiesList);

            case "txt":
                return new ExporterTXT(context, propertiesList);

            case "json":
                return new ExporterJSON(context, propertiesList);

            default:
                return null;
        }
    }
}
