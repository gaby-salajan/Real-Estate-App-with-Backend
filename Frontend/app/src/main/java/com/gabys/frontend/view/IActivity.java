package com.gabys.frontend.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.FragmentManager;

import com.gabys.frontend.view.adapters.PropertyCardAdapter;
import com.gabys.frontend.view.adapters.UserCardAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public interface IActivity {

    Button getFilterPropertiesButton();
    ExtendedFloatingActionButton getLoginButton();
    PropertyCardAdapter getPropertyCardAdapter();
    UserCardAdapter getUserCardAdapter();
    Intent getIntent();
    Spinner getLangSpinner();

    FragmentManager getFragmentManager1();

    void onConfigurationChanged(Configuration conf);

    void makeToast(String text);

    Context getContext();

    LayoutInflater getLayoutInflater();
}
