package com.gabys.frontend.model.export;

import android.content.Context;

import com.gabys.frontend.R;
import com.gabys.frontend.model.Property;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ExporterTXT implements IExporter{

    private Context context;
    private ArrayList<Property> propertiesList;

    public ExporterTXT(Context context, ArrayList<Property> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }


    @Override
    public void exportProperties() {
        LocalDateTime now = LocalDateTime.now();
        File path = context.getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write("Proprietati disponibile:");
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Property p : propertiesList){
            try {
                bw.write((propertiesList.indexOf(p) + 1) + ")");
                bw.newLine();
                bw.write("    ID: " + p.getId());
                bw.newLine();
                bw.write("    Titlu: " + p.getTitle());
                bw.newLine();
                bw.write("    Locatie: " + p.getLocation());
                bw.newLine();
                bw.write("    Nr Camere: " + p.getRoomsNo());
                bw.newLine();
                bw.write("    Tip: " + p.getType());
                bw.newLine();
                bw.write("    Pret: " + p.getPrice());
                bw.newLine();
                bw.write("    Disponibilitate: " + (p.isAvailable() ? context.getString(R.string.available) :  context.getString(R.string.unavailable)) );
                bw.newLine();
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}