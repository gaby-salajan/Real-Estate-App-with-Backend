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

public class ExporterCSV implements IExporter {

    private Context context;
    private ArrayList<Property> propertiesList;

    public ExporterCSV(Context context, ArrayList<Property> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public void exportProperties() {
        LocalDateTime now = LocalDateTime.now();
        File path = context.getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".csv");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write("ID,Titlu,Locatie,Nr Camere,Tip,Pret,Disponibilitate");
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Property p : propertiesList){
            try {
                bw.write(p.getId() + "," +
                        p.getTitle() + "," +
                        p.getLocation() + "," +
                        p.getRoomsNo() + "," +
                        p.getType() + "," +
                        p.getPrice() + "," +
                        (p.isAvailable() ? context.getResources().getString(R.string.available) : context.getResources().getString(R.string.unavailable)));
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
