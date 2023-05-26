package com.gabys.frontend.view;

import android.content.Context;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.IController;
import com.gabys.frontend.model.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterPropertiesDialog {
    private final ArrayList<Property> propertiesList;
    private Context context;

    public FilterPropertiesDialog(ArrayList<Property> propertiesList, LayoutInflater inflater, Context context, IController clientController) {
        this.propertiesList = propertiesList;
        this.context = context;

        View dialogView = inflater.inflate(R.layout.dialog_filter_properties, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        AlertDialog filterDialog = builder.create();

        filterDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        EditText minPriceEditText = dialogView.findViewById(R.id.price_min);
        EditText maxPriceEditText = dialogView.findViewById(R.id.price_max);
        Spinner locationSpinner = dialogView.findViewById(R.id.spinner_location);
        Spinner typeSpinner = dialogView.findViewById(R.id.spinner_type);
        Spinner roomSpinner = dialogView.findViewById(R.id.spinner_roomsNo);

        Button yesButton = dialogView.findViewById(R.id.filter_yes);
        Button noButton = dialogView.findViewById(R.id.filter_no);

        ArraySet<String> locations = new ArraySet<>();
        locations.add(context.getResources().getString(R.string.type_all));
        ArraySet<String> loc2 = new ArraySet<>();
        for(Property p : propertiesList){
            loc2.add(p.getLocation());
        }
        loc2 = loc2.stream().sorted().collect(Collectors.toCollection(ArraySet::new));
        locations.addAll(loc2);
        ArrayList<String> types = Arrays.stream(dialogView.getContext().getResources().getStringArray(R.array.property_types)).collect(Collectors.toCollection(ArrayList::new));

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(locations));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
        locationSpinner.setSelection(locations.indexOf(context.getResources().getString(R.string.type_all)));

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setSelection(types.indexOf(context.getResources().getString(R.string.type_all)));

        ArrayList<String> roomsNo = Arrays.stream(dialogView.getContext().getResources().getStringArray(R.array.rooms_no)).collect(Collectors.toCollection(ArrayList::new));
        ArrayAdapter<String> roomsAdapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_item, roomsNo);
        roomsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(roomsAdapter);
        roomSpinner.setSelection(roomsNo.indexOf(context.getResources().getString(R.string.rooms_all)));

        yesButton.setOnClickListener(view -> {
            String minPriceS = String.valueOf(minPriceEditText.getText());
            String maxPriceS = String.valueOf(maxPriceEditText.getText());
            String location = (String) locationSpinner.getSelectedItem();
            int type = typeSpinner.getSelectedItemPosition() - 1;
            String roomNo = (String) roomSpinner.getSelectedItem();

            float minPrice = 0f;
            float maxPrice = Float.MAX_VALUE;

            if(!minPriceS.isEmpty())
                minPrice = Float.parseFloat(minPriceS);

            if(!maxPriceS.isEmpty())
                maxPrice = Float.parseFloat(maxPriceS);

            performFilter(minPrice, maxPrice, location, type, roomNo, String.valueOf(typeSpinner.getSelectedItem()), clientController);

            filterDialog.dismiss();

        });

        noButton.setOnClickListener(view -> filterDialog.dismiss());

        filterDialog.show();
    }

    private void performFilter(Float minPrice, Float maxPrice, String location, int type, String roomsNo, String typeString, IController controller) {
        ArrayList<Property> filteredProperties = new ArrayList<>();

        for (Property p : propertiesList) {
            if (roomsNo != null && !roomsNo.isEmpty()) {
                if(roomsNo.equals(context.getResources().getString(R.string.type_all))){
                    filteredProperties.add(p);
                    continue;
                }
                if(roomsNo.equals("4+")){
                    if(p.getRoomsNo() >= 4){
                        filteredProperties.add(p);
                    }
                    continue;
                }

                if(Integer.parseInt(roomsNo) == p.getRoomsNo()){
                    filteredProperties.add(p);
                }

            }
        }

        filteredProperties = filteredProperties.stream()
                .filter(p -> p.getLocation().equals(location) || location.equals(context.getResources().getString(R.string.type_all)))
                .filter(p -> p.getType() == type || typeString.equals(context.getResources().getString(R.string.type_all)))
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toCollection(ArrayList::new));

        propertiesList.clear();
        propertiesList.addAll(filteredProperties);
        controller.updateProperties();
    }
}
