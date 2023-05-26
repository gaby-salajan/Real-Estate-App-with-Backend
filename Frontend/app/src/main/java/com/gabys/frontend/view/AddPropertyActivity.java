package com.gabys.frontend.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.AddPropertyController;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AddPropertyActivity extends AppCompatActivity {
    private EditText titleEt, locationEt, roomsNoEt, priceEt, imageURLEt;
    private Spinner typeSpinner;

    private SwitchMaterial isAvailableSwitch;

    private Button finishB, cancelB;

    private void initComponents(){
        titleEt = findViewById(R.id.title_et);
        locationEt = findViewById(R.id.location_et);
        roomsNoEt = findViewById(R.id.roomsNo_et);
        priceEt = findViewById(R.id.price_et);
        imageURLEt = findViewById(R.id.imageURL_et);
        typeSpinner = findViewById(R.id.spinner_type);
        finishB = findViewById(R.id.finish_button);
        cancelB = findViewById(R.id.cancel_button);
        isAvailableSwitch = findViewById(R.id.isAvailable_switch);
        isAvailableSwitch.setChecked(true);

        ArrayList<String> types = Arrays.stream(this.getResources().getStringArray(R.array.property_types)).collect(Collectors.toCollection(ArrayList::new));
        types.remove(0);
        types = (ArrayList<String>) types.stream().sorted().collect(Collectors.toList());

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setSelection(types.size()-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);
        initComponents();

        new AddPropertyController(this);
    }

    public EditText getTitleEt() {
        return titleEt;
    }

    public EditText getLocationEt() {
        return locationEt;
    }

    public EditText getRoomsNoEt() {
        return roomsNoEt;
    }

    public EditText getPriceEt() {
        return priceEt;
    }

    public EditText getImageURLEt() {
        return imageURLEt;
    }

    public Spinner getTypeSpinner() {
        return typeSpinner;
    }

    public SwitchMaterial getIsAvailableSwitch() {
        return isAvailableSwitch;
    }

    public Button getYesButton() {
        return finishB;
    }

    public Button getNoButton() {
        return cancelB;
    }
}
