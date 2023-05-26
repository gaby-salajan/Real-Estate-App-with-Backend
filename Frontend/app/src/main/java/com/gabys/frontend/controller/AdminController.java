package com.gabys.frontend.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.commands.CommandDeleteUser;
import com.gabys.frontend.controller.commands.CommandFetchProperties;
import com.gabys.frontend.controller.commands.CommandFetchUsers;
import com.gabys.frontend.controller.commands.CommandFilterProperties;
import com.gabys.frontend.controller.commands.CommandFilterUsers;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.AppLanguage;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.PropertyPersistence;
import com.gabys.frontend.model.persistence.UserPersistence;
import com.gabys.frontend.view.AddUserActivity;
import com.gabys.frontend.view.AdminActivity;
import com.gabys.frontend.view.IActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class AdminController implements IController, Observer {

    private Context context;

    private IActivity activity;

    private UserPersistence userPersistence;
    private PropertyPersistence propertyPersistence;

    private ArrayList<Property> propertiesList;

    private ArrayList<User> usersList;

    private final AppLanguage language;

    private final ICommand commandFilterProperties;
    private final ICommand commandFilterUsers;
    private final ICommand commandFetchProperties;
    private final ICommand commandFetchUsers;
    private final ICommand commandDeleteUser;

    public AdminController(IActivity activity, Context context) {
        this.activity = activity;
        this.context = context;

        this.userPersistence = new UserPersistence();
        this.propertyPersistence = new PropertyPersistence();

        this.language = new AppLanguage(context);

        commandFetchProperties = new CommandFetchProperties();
        commandFetchUsers = new CommandFetchUsers();

        fetchProperties();
        fetchUsers();

        setFilterButtonListener();
        setFilterUsersButtonListener();

        setLogoutButtonListener();
        setAddUserButtonListener();

        setupLanguageSpinner();

        this.language.addObserver(this);
        String lang = activity.getIntent().getStringExtra("lang");
        this.language.changeLang(lang);


        commandFilterProperties = new CommandFilterProperties(this, propertiesList, activity.getLayoutInflater(), activity.getContext());
        commandFilterUsers = new CommandFilterUsers(this, usersList, activity.getLayoutInflater(), activity.getContext());

        commandDeleteUser = new CommandDeleteUser();
    }

    private void setAddUserButtonListener() {
        ((AdminActivity)activity).getAddUserButton().setOnClickListener(v -> {
            Intent intent = new Intent(context, AddUserActivity.class);
            context.startActivity(intent);
        });
    }


    private void setFilterButtonListener(){
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

    private void setFilterUsersButtonListener(){
        ((AdminActivity)activity).getFilterUsersButton().setOnClickListener(v -> {
            commandFetchUsers.Execute(new ResponseCallback() {
                @Override
                public void onSuccessfulResponse(Object object) {
                    usersList = (ArrayList<User>) object;
                    ((CommandFilterUsers) commandFilterUsers).setUsersList(usersList);

                    commandFilterUsers.Execute(new ResponseCallback() {
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

                }
            });
        });
    }

    private void setLogoutButtonListener(){
        activity.getLoginButton().setOnClickListener(v -> {
            ((AdminActivity)activity).finish();
        });
    }

    public void fetchUsers(){
        commandFetchUsers.Execute(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                usersList = (ArrayList<User>) object;
                updateUsers();
            }

            @Override
            public void onError() {

            }
        });
    }
    public void updateUsers() {
        activity.getUserCardAdapter().setItems(usersList);
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
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void deleteUser(String username) {
        ((CommandDeleteUser)commandDeleteUser).setUsername(username);

        commandDeleteUser.Execute(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                fetchUsers();
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void getLoginUser(String username, ResponseCallback responseCallback) {}

    public void onResume(){
        fetchProperties();
        fetchUsers();
    }


    @Override
    public void update(Observable o, Object arg) {
        activity.onConfigurationChanged(((AppLanguage) o).getConf());

        activity.getLangSpinner().setSelection(((AppLanguage)o).getIndex());

        activity.getFilterPropertiesButton().setText(R.string.button_filter_prop);
        activity.getLoginButton().setText(R.string.button_logout);
        activity.getPropertyCardAdapter().updateLang();
        activity.getUserCardAdapter().updateLang();
        ((AdminActivity)activity).getAddUserButton().setText(R.string.button_addUser);

        ((AdminActivity)activity).getViewPager().updateLang();
    }
}
