package com.gabys.frontend.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.ClientController;
import com.gabys.frontend.view.adapters.PropertyCardAdapter;
import com.gabys.frontend.view.adapters.UserCardAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class ClientActivity extends AppCompatActivity implements IActivity {
    private Button filterButton;
    private ExtendedFloatingActionButton loginButton;
    private RecyclerView propertiesRV;
    private PropertyCardAdapter propertyCardAdapter;

    private Spinner langSpinner;


    private void initComponents(){
        setContentView(R.layout.activity_client);
        propertiesRV = findViewById(R.id.property_RecyclerView);
        loginButton = findViewById(R.id.loginButton);
        filterButton = findViewById(R.id.filterButton);

        propertyCardAdapter = new PropertyCardAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        propertiesRV.setLayoutManager(linearLayoutManager);
        propertiesRV.setAdapter(propertyCardAdapter);

        langSpinner = findViewById(R.id.langSpinner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();

        new ClientController(this, this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public PropertyCardAdapter getPropertyCardAdapter() {
        return propertyCardAdapter;
    }

    @Override
    public Button getFilterPropertiesButton() {
        return filterButton;
    }

    @Override
    public ExtendedFloatingActionButton getLoginButton() {
        return loginButton;
    }

    @Override
    public UserCardAdapter getUserCardAdapter() {
        return null;
    }

    @Override
    public Spinner getLangSpinner() {
        return langSpinner;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public FragmentManager getFragmentManager1(){
        return this.getSupportFragmentManager();
    }


}
