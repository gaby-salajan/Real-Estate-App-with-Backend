package com.gabys.frontend.model.export;

import android.content.Context;

import com.gabys.frontend.model.Property;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ExporterJSON implements IExporter{

    private Context context;
    private ArrayList<Property> propertiesList;

    public ExporterJSON(Context context, ArrayList<Property> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public void exportProperties() {
        LocalDateTime now = LocalDateTime.now();
        File path = context.getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".json");

        String json = new Gson().toJson(propertiesList);

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
