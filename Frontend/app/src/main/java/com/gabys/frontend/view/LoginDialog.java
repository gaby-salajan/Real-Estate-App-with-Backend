package com.gabys.frontend.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.ClientController;
import com.gabys.frontend.model.AppLanguage;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.google.gson.Gson;

public class LoginDialog {

    private AppLanguage language;

    public LoginDialog(LayoutInflater inflater, Context context, ClientController clientController, AppLanguage currentLanguage) {
        this.language = currentLanguage;

        View dialogView = inflater.inflate(R.layout.dialog_login, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        AlertDialog loginDialog = builder.create();

        loginDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_login_bg);

        // Set up views in filter dialog
        EditText usernameEditText = dialogView.findViewById(R.id.usernameField);
        EditText passwordEditText = dialogView.findViewById(R.id.passwordField);

        Button yesButton = dialogView.findViewById(R.id.filter_yes);
        Button noButton = dialogView.findViewById(R.id.filter_no);

        yesButton.setOnClickListener(v -> {
            String username = String.valueOf(usernameEditText.getText());
            String password = String.valueOf(passwordEditText.getText());

            performLogin(context, username, password, clientController);

            loginDialog.dismiss();
        });

        noButton.setOnClickListener(v -> loginDialog.dismiss());

        loginDialog.show();
    }

    private void performLogin(Context context, String username, String password, ClientController clientController){
        clientController.getLoginUser(username, new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                User res = (User) object;
                if(password.equals(res.getPassword())){
                    Intent intent = null;

                    if(res.getRole() == 1)
                        intent = new Intent(context, EmployeeActivity.class);

                    if(res.getRole() == 2)
                        intent = new Intent(context, AdminActivity.class);

                    if(intent != null){
                        intent.putExtra("user", new Gson().toJson(res));
                        intent.putExtra("lang", language.getLang());
                        context.startActivity(intent);
                    }
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}
