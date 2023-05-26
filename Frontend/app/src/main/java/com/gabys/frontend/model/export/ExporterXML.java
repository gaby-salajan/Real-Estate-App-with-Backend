package com.gabys.frontend.model.export;

import android.content.Context;

import com.gabys.frontend.R;
import com.gabys.frontend.model.Property;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

public class ExporterXML implements IExporter{

    private Context context;
    private ArrayList<Property> propertiesList;

    public ExporterXML(Context context, ArrayList<Property> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public void exportProperties() {
        LocalDateTime now = LocalDateTime.now();
        File path = context.getFilesDir();

        File file = new File(path, "exported_properties_"+ now +".xml");

        try {

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Proprietati");
            doc.appendChild(root);

            for (Property p : propertiesList) {

                Element name = doc.createElement("ID");
                name.appendChild(doc.createTextNode(String.valueOf(p.getId())));
                root.appendChild(name);

                Element title = doc.createElement("Titlu");
                title.appendChild(doc.createTextNode(p.getTitle()));
                root.appendChild(title);

                Element location = doc.createElement("Locatie");
                location.appendChild(doc.createTextNode(p.getLocation()));
                root.appendChild(location);

                Element roomsNo = doc.createElement("Nr_Camere");
                roomsNo.appendChild(doc.createTextNode(String.valueOf(p.getRoomsNo())));
                root.appendChild(roomsNo);

                Element type = doc.createElement("Tip");

                ArrayList<String> types = Arrays.stream(context.getResources().getStringArray(R.array.property_types)).collect(Collectors.toCollection(ArrayList::new));
                types.remove(0);

                type.appendChild(doc.createTextNode(types.get(p.getType())));
                root.appendChild(type);

                Element price = doc.createElement("Pret");
                price.appendChild(doc.createTextNode(String.valueOf(p.getPrice())));
                root.appendChild(price);

                Element available = doc.createElement("Disponibilitate");
                available.appendChild(doc.createTextNode(p.isAvailable() ? context.getResources().getString(R.string.available) : context.getResources().getString(R.string.unavailable)));
                root.appendChild(available);
            }

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter(file);
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }
}
