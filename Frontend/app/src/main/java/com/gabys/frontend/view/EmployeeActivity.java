package com.gabys.frontend.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.EmployeeController;
import com.gabys.frontend.model.User;
import com.gabys.frontend.view.adapters.PropertyCardAdapter;
import com.gabys.frontend.view.adapters.UserCardAdapter;
import com.gabys.frontend.view.adapters.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

public class EmployeeActivity extends AppCompatActivity implements IActivity {

    private Button filterButton;
    private ExtendedFloatingActionButton addClientButton;
    private ExtendedFloatingActionButton addPropertyButton;
    private ExtendedFloatingActionButton logoutButton;
    private ExtendedFloatingActionButton exportButton;
    private Button statsButton;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PropertyCardAdapter propertyCardAdapter;
    private UserCardAdapter clientCardAdapter;

    private ViewPagerAdapter viewPagerAdapter;

    private EmployeeController controller;

    private Spinner langSpinner;

    private void initComponents(){
        setContentView(R.layout.activity_employee);
        logoutButton = findViewById(R.id.extended_fab);
        filterButton = findViewById(R.id.filterButton);
        logoutButton.setBackgroundColor(getColor(R.color.logout_tint));
        addPropertyButton = findViewById(R.id.addProperty_fab);
        addClientButton = findViewById(R.id.addClient_fab);
        exportButton = findViewById(R.id.saveProperties_fab);
        statsButton = findViewById(R.id.statsButton);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);


        propertyCardAdapter = new PropertyCardAdapter(this);
        clientCardAdapter = new UserCardAdapter(this, new Gson().fromJson(getIntent().getStringExtra("user"), User.class));

        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), propertyCardAdapter, clientCardAdapter, new Gson().fromJson(getIntent().getStringExtra("user"), User.class), this);
        viewPager.setAdapter(viewPagerAdapter);

        langSpinner = findViewById(R.id.langSpinner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();

        controller = new EmployeeController(this, this);
        clientCardAdapter.setController(controller);
        propertyCardAdapter.addController(controller);
    }


    @Override
    public Button getFilterPropertiesButton() {
        return filterButton;
    }

    @Override
    public ExtendedFloatingActionButton getLoginButton() {
        return logoutButton;
    }

    public ExtendedFloatingActionButton getExportButton(){
        return exportButton;
    }

    public ExtendedFloatingActionButton getAddPropertyButton(){
        return addPropertyButton;
    }

    public ExtendedFloatingActionButton getAddClientButton(){
        return addClientButton;
    }

    public Button getStatsButton(){
        return statsButton;
    }

    @Override
    public PropertyCardAdapter getPropertyCardAdapter() {
        return propertyCardAdapter;
    }
    @Override
    public UserCardAdapter getUserCardAdapter() {
        return clientCardAdapter;
    }

    @Override
    public Spinner getLangSpinner() {
        return langSpinner;
    }

    @Override
    public FragmentManager getFragmentManager1() {
        return this.getSupportFragmentManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.onResume();
    }

    public ViewPagerAdapter getViewPager() {
        return viewPagerAdapter;
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
