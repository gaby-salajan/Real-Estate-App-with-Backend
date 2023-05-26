package com.gabys.frontend.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.AddUserController;

public class AddUserActivity extends AppCompatActivity {

    private EditText usernameEt, passwordEt;
    private Button finishB, cancelB;

    private Spinner roleSpinner;

    private void initComponents(){
        setContentView(R.layout.activity_add_user);
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);

        finishB = findViewById(R.id.finish_button);
        cancelB = findViewById(R.id.cancel_button);

        roleSpinner = findViewById(R.id.spinner_role);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();

        new AddUserController(this);
    }

    public View getYesButton() {
        return finishB;
    }

    public View getNoButton() {
        return cancelB;
    }

    public String getUsername() {
        return usernameEt.getText().toString();
    }

    public String getPassword() {
        return passwordEt.getText().toString();
    }

    public Spinner getRoleSpinner(){
        return roleSpinner;
    }
}
