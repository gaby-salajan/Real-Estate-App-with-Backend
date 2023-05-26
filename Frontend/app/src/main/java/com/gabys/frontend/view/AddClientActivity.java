package com.gabys.frontend.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.AddClientController;

public class AddClientActivity extends AppCompatActivity {
    private EditText usernameEt, phoneEt, emailEt;
    private Button finishB, cancelB;

    private void initComponents(){
        setContentView(R.layout.activity_add_user);
        usernameEt = findViewById(R.id.username_et);
        emailEt = findViewById(R.id.email_et);
        phoneEt = findViewById(R.id.phone_et);

        findViewById(R.id.password_et).setVisibility(View.GONE);
        findViewById(R.id.spinner_role).setVisibility(View.GONE);
        findViewById(R.id.role_tv).setVisibility(View.GONE);

        finishB = findViewById(R.id.finish_button);
        cancelB = findViewById(R.id.cancel_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();

        new AddClientController(this);
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

    public EditText getUserNameEditText() {
        return usernameEt;
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
