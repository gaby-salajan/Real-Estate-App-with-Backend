package com.gabys.frontend.controller;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.commands.CommandFetchProperties;
import com.gabys.frontend.controller.commands.CommandFilterProperties;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.AppLanguage;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.persistence.UserPersistence;
import com.gabys.frontend.view.ClientActivity;
import com.gabys.frontend.view.IActivity;
import com.gabys.frontend.view.LoginDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class ClientController implements IController, Observer {
    private final Context context;

    private final IActivity activity;

    private ArrayList<Property> propertiesList;

    private final AppLanguage language;
    private final ICommand commandFilter;
    private final ICommand commandFetchProperties;

    public ClientController(IActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
        this.language = new AppLanguage(context);
        language.addObserver(this);
        update(language, null);

        commandFilter = new CommandFilterProperties(this, propertiesList, activity.getLayoutInflater(), activity.getContext());
        commandFetchProperties = new CommandFetchProperties();

        fetchProperties();

        setupLanguageSpinner();

        setFilterButtonListener();
        setLoginButtonListener();

    }

    @Override
    public void setupLanguageSpinner() {
        ArrayList<String> langList = Arrays.stream(context.getResources().getStringArray(R.array.languages)).collect(Collectors.toCollection(ArrayList::new));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, langList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activity.getLangSpinner().setAdapter(adapter);
        activity.getLangSpinner().setSelection(0);

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
    public void deleteUser(String username) {}

    private void setFilterButtonListener(){
        activity.getFilterPropertiesButton().setOnClickListener(v -> {

            commandFetchProperties.Execute(new ResponseCallback() {
                @Override
                public void onSuccessfulResponse(Object object) {
                    propertiesList = (ArrayList<Property>) object;
                    ((CommandFilterProperties)commandFilter).setPropertiesList(propertiesList);

                    commandFilter.Execute(new ResponseCallback() {
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

    private void setLoginButtonListener(){
        activity.getLoginButton().setOnClickListener(v -> {
            new LoginDialog(((ClientActivity) activity).getLayoutInflater(), context, this, language);
        });
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
                activity.makeToast("Failed to fetch properties");
            }
        });
    }

    public void updateProperties() {
        activity.getPropertyCardAdapter().setItems(propertiesList);
    }

    @Override
    public void getLoginUser(String username, ResponseCallback responseCallback) {

        new UserPersistence().getUser(username, new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                responseCallback.onSuccessfulResponse(object);
            }

            @Override
            public void onError() {
                responseCallback.onError();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        activity.getLangSpinner().setSelection(((AppLanguage)o).getIndex());

        activity.onConfigurationChanged(((AppLanguage) o).getConf());
        activity.getFilterPropertiesButton().setText(R.string.button_filter_prop);
        activity.getLoginButton().setText(R.string.button_login);
        activity.getPropertyCardAdapter().updateLang();
    }
}
