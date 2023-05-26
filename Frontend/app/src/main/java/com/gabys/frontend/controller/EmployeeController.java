package com.gabys.frontend.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.commands.CommandDeleteProperty;
import com.gabys.frontend.controller.commands.CommandDeleteUser;
import com.gabys.frontend.controller.commands.CommandFetchClients;
import com.gabys.frontend.controller.commands.CommandFetchProperties;
import com.gabys.frontend.controller.commands.CommandFilterProperties;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.AppLanguage;
import com.gabys.frontend.model.PropertiesList;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.PropertyPersistence;
import com.gabys.frontend.model.persistence.UserPersistence;
import com.gabys.frontend.view.AddClientActivity;
import com.gabys.frontend.view.AddPropertyActivity;
import com.gabys.frontend.view.EmployeeActivity;
import com.gabys.frontend.view.ExportPropertiesActivity;
import com.gabys.frontend.view.IActivity;
import com.gabys.frontend.view.StatisticsActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class EmployeeController implements IController, Observer {

    private Context context;

    private IActivity activity;

    private UserPersistence userPersistence;
    private PropertyPersistence propertyPersistence;

    private ArrayList<Property> propertiesList;

    private ArrayList<User> clientsList;

    private User loggedUser;
    private AppLanguage language;

    private ICommand commandFetchProperties;
    private ICommand commandFetchClients;
    private ICommand commandFilterProperties;
    private ICommand commandDeleteClient;
    private ICommand commandDeleteProperty;

    public EmployeeController(IActivity activity, Context context) {
        this.activity = activity;
        this.context = context;

        this.userPersistence = new UserPersistence();
        this.propertyPersistence = new PropertyPersistence();

        this.loggedUser = new Gson().fromJson(activity.getIntent().getStringExtra("user"), User.class);
        this.language = new AppLanguage(context);

        commandFetchProperties = new CommandFetchProperties();
        commandFetchClients = new CommandFetchClients();

        fetchProperties();
        fetchClients();


        setupLanguageSpinner();

        setFilterButtonListener(this);
        setLogoutButtonListener();
        setAddClientButtonListener();
        setAddPropertyButtonListener();
        setExportButtonListener();
        setStatsButtonListener();

        activity.getPropertyCardAdapter().setUserRole(loggedUser.getRole());

        language.addObserver(this);
        String lang = activity.getIntent().getStringExtra("lang");
        this.language.changeLang(lang);

        commandFilterProperties = new CommandFilterProperties(this, propertiesList, activity.getLayoutInflater(), activity.getContext());
        commandDeleteClient = new CommandDeleteUser();
        commandDeleteProperty = new CommandDeleteProperty();
    }

    private void setAddClientButtonListener() {
        ((EmployeeActivity)activity).getAddClientButton().setOnClickListener(v -> {
            Intent intent = new Intent(context, AddClientActivity.class);
            context.startActivity(intent);
        });
    }

    private void setAddPropertyButtonListener(){
        ((EmployeeActivity)activity).getAddPropertyButton().setOnClickListener(v -> {
            Intent intent = new Intent(context, AddPropertyActivity.class);
            context.startActivity(intent);
        });
    }

    private void setExportButtonListener(){
        ((EmployeeActivity)activity).getExportButton().setOnClickListener(v -> {
            Intent intent = new Intent(context, ExportPropertiesActivity.class);
            PropertiesList propertiesList = new PropertiesList(this.propertiesList);
            intent.putExtra("properties", new Gson().toJson(propertiesList));
            context.startActivity(intent);
        });
    }

    private void setStatsButtonListener(){
        ((EmployeeActivity)activity).getStatsButton().setOnClickListener(v -> {
            Intent intent = new Intent(context, StatisticsActivity.class);
            PropertiesList propertiesList = new PropertiesList(this.propertiesList);
            intent.putExtra("properties", new Gson().toJson(propertiesList));
            context.startActivity(intent);
        });
    }


    private void setFilterButtonListener(EmployeeController employeeController){
        activity.getFilterPropertiesButton().setOnClickListener(v -> {

            commandFetchProperties.Execute(new ResponseCallback() {
                @Override
                public void onSuccessfulResponse(Object object) {
                    propertiesList = (ArrayList<Property>) object;
                    ((CommandFilterProperties)commandFilterProperties).setPropertiesList(propertiesList);

                    commandFilterProperties.Execute(new ResponseCallback() {
                        @Override
                        public void onSuccessfulResponse(Object object) {

                        }

                        @Override
                        public void onError() {

                        }
                    });
                }

                @Override
                public void onError() {
                    activity.makeToast("Failed to fetch properties");
                }
            });
        });
    }

    private void setLogoutButtonListener(){
        activity.getLoginButton().setOnClickListener(v -> {
            ((EmployeeActivity)activity).finish();
        });
    }

    public void fetchClients(){
        commandFetchClients.Execute(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                clientsList = (ArrayList<User>) object;
                updateClients();
            }

            @Override
            public void onError() {

            }
        });
    }
    private void updateClients() {
        activity.getUserCardAdapter().setItems(clientsList);
    }

    public void fetchProperties(){
        commandFetchProperties.Execute(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                propertiesList = (ArrayList<Property>) object;
                updateProperties();
            }

            @Override
            public void onError() {

            }
        });
    }

    public void updateProperties() {
        activity.getPropertyCardAdapter().setItems(propertiesList);
    }

    @Override
    public void setupLanguageSpinner() {
        ArrayList<String> langList = Arrays.stream(context.getResources().getStringArray(R.array.languages)).collect(Collectors.toCollection(ArrayList::new));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, langList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activity.getLangSpinner().setAdapter(adapter);
        activity.getLangSpinner().setSelection(language.getIndex());

        activity.getLangSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                language.changeLang((String) parent.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void deleteUser(String username) {
        ((CommandDeleteUser)commandDeleteClient).setUsername(username);
        commandDeleteClient.Execute(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                fetchClients();
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void getLoginUser(String username, ResponseCallback responseCallback) {

    }

    public void deleteProperty(int id) {
        ((CommandDeleteProperty)commandDeleteProperty).setPropertyId(id);
        commandDeleteProperty.Execute(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                fetchProperties();
            }

            @Override
            public void onError() {

            }
        });
    }

    public void onResume(){
        fetchProperties();
        fetchClients();
    }

    @Override
    public void update(Observable o, Object arg) {
        activity.onConfigurationChanged(((AppLanguage) o).getConf());

        activity.getLangSpinner().setSelection(((AppLanguage)o).getIndex());

        activity.getFilterPropertiesButton().setText(R.string.button_filter_prop);
        activity.getLoginButton().setText(R.string.button_logout);
        activity.getPropertyCardAdapter().updateLang();
        activity.getUserCardAdapter().updateLang();
        ((EmployeeActivity)activity).getExportButton().setText(R.string.button_export);
        ((EmployeeActivity)activity).getAddClientButton().setText(R.string.button_addClient);
        ((EmployeeActivity)activity).getAddPropertyButton().setText(R.string.button_addProperty);
        ((EmployeeActivity)activity).getStatsButton().setText(R.string.button_statistics);

        ((EmployeeActivity)activity).getViewPager().updateLang();
    }
}
