package com.gabys.frontend.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.AdminController;
import com.gabys.frontend.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterUsersDialog {
    private final ArrayList<User> usersList;
    private Context context;

    public FilterUsersDialog(ArrayList<User> usersList, LayoutInflater inflater, Context context, AdminController adminController) {
        this.usersList = usersList;
        this.context = context;

        View dialogView = inflater.inflate(R.layout.dialog_filter_users, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        AlertDialog filterDialog = builder.create();

        filterDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        Spinner typeSpinner = dialogView.findViewById(R.id.spinner_type);

        Button yesButton = dialogView.findViewById(R.id.filter_yes);
        Button noButton = dialogView.findViewById(R.id.filter_no);

        ArrayList<String> types = Arrays.stream(dialogView.getContext().getResources().getStringArray(R.array.user_roles)).skip(1).collect(Collectors.toCollection(ArrayList::new));
        types.add(context.getString(R.string.type_all));

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setSelection(0);

        yesButton.setOnClickListener(view -> {
            int type = typeSpinner.getSelectedItemPosition() + 1;
            performFilter(type, adminController);
            filterDialog.dismiss();

        });

        noButton.setOnClickListener(view -> filterDialog.dismiss());

        filterDialog.show();
    }

    private void performFilter(int type, AdminController controller) {
        ArrayList<User> filteredUsers = new ArrayList<>(usersList);

        if(type != 3){
            filteredUsers = usersList.stream()
                    .filter(p -> p.getRole().equals(type))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        usersList.clear();
        usersList.addAll(filteredUsers);
        controller.updateUsers();
    }
}
