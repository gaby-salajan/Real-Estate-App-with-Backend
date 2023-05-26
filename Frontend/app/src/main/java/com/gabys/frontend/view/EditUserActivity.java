package com.gabys.frontend.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.EditUserController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EditUserActivity extends AppCompatActivity {
    private EditText usernameEt, passwordEt, phoneEt, emailEt;
    private Spinner roleSpinner;
    private Button finishB, cancelB;

    private void initComponents(){
        setContentView(R.layout.activity_edit_user);
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        emailEt = findViewById(R.id.email_et);
        phoneEt = findViewById(R.id.phone_et);
        roleSpinner = findViewById(R.id.spinner_role);

        finishB = findViewById(R.id.finish_button);
        cancelB = findViewById(R.id.cancel_button);

        ArrayList<String> types = Arrays.stream(this.getResources().getStringArray(R.array.user_roles)).collect(Collectors.toCollection(ArrayList::new));
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roleSpinner.setAdapter(typeAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();

        new EditUserController(this);
    }

    public View getYesButton() {
        return finishB;
    }

    public View getNoButton() {
        return cancelB;
    }

    public String getUserName() {
        return usernameEt.getText().toString();
    }

    public String getPassword() {
        return passwordEt.getText().toString();
    }

    public int getRole() {
        return roleSpinner.getSelectedItemPosition();
    }

    public EditText getUserNameEditText() {
        return usernameEt;
    }

    public EditText getPasswordEditText() {
        return passwordEt;
    }

    public Spinner getRoleSpinner() {
        return roleSpinner;
    }

    public TextView getRoleTextView() {
        return (TextView)findViewById(R.id.role_tv);
    }

    public String getEmail(){
        return emailEt.getText().toString();
    }

    public String getPhone(){
        return phoneEt.getText().toString();
    }

    public EditText getPhoneEditText() {
        return phoneEt;
    }

    public EditText getEmailEditText() {
        return emailEt;
    }

    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
