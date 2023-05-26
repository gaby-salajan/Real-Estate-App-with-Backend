package com.gabys.frontend.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.EditPropertyController;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class EditPropertyActivity extends AppCompatActivity {

    private TextView idTv;

    private ImageView imageView;
    private EditText titleEt, locationEt, roomsNoEt, priceEt, imageURLEt;
    private Spinner typeSpinner;

    private SwitchMaterial isAvailableSwitch;

    private Button finishB, cancelB;

    private void initComponents(){
        idTv = findViewById(R.id.id_tv);
        titleEt = findViewById(R.id.title_et);
        locationEt = findViewById(R.id.location_et);
        roomsNoEt = findViewById(R.id.roomsNo_et);
        priceEt = findViewById(R.id.price_et);
        imageURLEt = findViewById(R.id.imageURL_et);

        imageView = findViewById(R.id.property_image);

        typeSpinner = findViewById(R.id.spinner_type);

        isAvailableSwitch = findViewById(R.id.isAvailable_switch);
        isAvailableSwitch.setChecked(true);

        finishB = findViewById(R.id.finish_button);
        cancelB = findViewById(R.id.cancel_button);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_property);
        initComponents();

        new EditPropertyController(this);
    }

    public Spinner getTypeSpinner() {
        return typeSpinner;
    }


    public View getYesButton() {
        return finishB;
    }

    public View getNoButton() {
        return cancelB;
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

    public SwitchMaterial getIsAvailableSwitch() {
        return isAvailableSwitch;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getIdTv(){
        return idTv;
    }
}
