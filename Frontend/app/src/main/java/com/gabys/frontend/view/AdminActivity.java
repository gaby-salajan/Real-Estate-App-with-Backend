package com.gabys.frontend.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.AdminController;
import com.gabys.frontend.model.User;
import com.gabys.frontend.view.adapters.PropertyCardAdapter;
import com.gabys.frontend.view.adapters.UserCardAdapter;
import com.gabys.frontend.view.adapters.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

public class AdminActivity extends AppCompatActivity implements IActivity {
    private Button filterPropertiesButton;

    private Button filterUsersButton;
    private ExtendedFloatingActionButton addUserButton;
    private ExtendedFloatingActionButton logoutButton;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PropertyCardAdapter propertyCardAdapter;
    private UserCardAdapter userCardAdapter;

    private ViewPagerAdapter viewPagerAdapter;

    private AdminController controller;

    private Spinner langSpinner;

    private void initComponents(){
        setContentView(R.layout.activity_admin);
        logoutButton = findViewById(R.id.extended_fab);
        filterPropertiesButton = findViewById(R.id.filterButton);
        filterUsersButton = findViewById(R.id.filterButton2);

        logoutButton.setBackgroundColor(getColor(R.color.logout_tint));
        addUserButton = findViewById(R.id.addUser_fab);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);


        propertyCardAdapter = new PropertyCardAdapter(this);
        userCardAdapter = new UserCardAdapter(this, new Gson().fromJson(getIntent().getStringExtra("user"), User.class));
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), propertyCardAdapter, userCardAdapter, new Gson().fromJson(getIntent().getStringExtra("user"), User.class), this);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);

        langSpinner = findViewById(R.id.langSpinner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();

        controller = new AdminController(this, this);
        userCardAdapter.setController(controller);
    }


    @Override
    public Button getFilterPropertiesButton() {
        return filterPropertiesButton;
    }

    public Button getFilterUsersButton() {
        return filterUsersButton;
    }

    @Override
    public ExtendedFloatingActionButton getLoginButton() {
        return logoutButton;
    }

    @Override
    public PropertyCardAdapter getPropertyCardAdapter() {
        return propertyCardAdapter;
    }
    @Override
    public UserCardAdapter getUserCardAdapter() {
        return userCardAdapter;
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
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public ExtendedFloatingActionButton getAddUserButton() {
        return addUserButton;
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.onResume();
    }

    public ViewPagerAdapter getViewPager() {
        return viewPagerAdapter;
    }
}
