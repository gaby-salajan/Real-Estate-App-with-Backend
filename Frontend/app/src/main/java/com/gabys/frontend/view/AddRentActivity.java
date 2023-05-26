package com.gabys.frontend.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gabys.frontend.R;
import com.gabys.frontend.controller.AddRentController;
import com.gabys.frontend.model.User;

public class AddRentActivity extends AppCompatActivity {

    private Button yesButton, noButton;

    private ImageView propertyImage;

    private Spinner clientSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rent);

        propertyImage = findViewById(R.id.property_image);
        clientSpinner = findViewById(R.id.clientSpinner);
        yesButton = findViewById(R.id.finish_button);
        noButton = findViewById(R.id.cancel_button);



        new AddRentController(this);
    }


    public View getYesButton(){
        return yesButton;
    }

    public View getNoButton() {
        return noButton;
    }

    public User getSelectedClient() {
        return (User) clientSpinner.getSelectedItem();
    }

    public Spinner getClientSpinner(){
        return clientSpinner;
    }

    public void setPropertyImage(String imageURL) {
        Glide.with(this)
                .load(imageURL)
                .centerCrop()
                .into(propertyImage);
    }

}
