package com.gabys.frontend.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.IController;
import com.gabys.frontend.model.User;
import com.gabys.frontend.view.EditUserActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<User> userList;

    private IController controller;

    private final User loggedUser;

    public UserCardAdapter(Context context, User loggedUser) {
        this.context = context;
        this.userList = new ArrayList<>();
        this.loggedUser = loggedUser;
    }

    public void setController(IController controller) {
        this.controller = controller;
    }

    public void setItems(ArrayList<User> users) {
        this.userList.clear();
        this.userList.addAll(users);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User model = userList.get(position);

        holder.userUsername.setText(context.getString(R.string.user_username) + model.getUsername());
        holder.userEmail.setText(context.getString(R.string.user_email) + model.getEmail());
        holder.userPhone.setText(context.getString(R.string.user_phone) + model.getPhone());
        holder.userPassword.setText(context.getString(R.string.user_password) + model.getPassword());

        if(loggedUser.getRole() != 2){
            holder.userDataLayout.setVisibility(View.GONE);
        }

        holder.deleteButton.setText(context.getString(R.string.button_delete));
        holder.modifyButton.setText(context.getString(R.string.button_modify));

        ArrayList<String> types = Arrays.stream(context.getResources().getStringArray(R.array.user_roles)).collect(Collectors.toCollection(ArrayList::new));
        holder.userRole.setText(types.get(model.getRole()));

        holder.modifyButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditUserActivity.class);

            Gson gson = new Gson();
            intent.putExtra("user", gson.toJson(model));
            intent.putExtra("loggedUser", gson.toJson(loggedUser));

            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            controller.deleteUser(model.getUsername());
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateLang() {
        notifyDataSetChanged();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //complete with card details
        private final TextView userUsername;

        private final TextView userEmail;

        private final LinearLayout userDataLayout;
        private final TextView userPhone;
        private final TextView userPassword;
        private final TextView userRole;
        private final Button modifyButton;
        private final Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userUsername = itemView.findViewById(R.id.card_username);
            userEmail = itemView.findViewById(R.id.card_email);
            userPhone = itemView.findViewById(R.id.card_phone);

            userPassword = itemView.findViewById(R.id.card_password);
            userRole = itemView.findViewById(R.id.card_role);

            userDataLayout = itemView.findViewById(R.id.user_data);

            modifyButton = itemView.findViewById(R.id.modify_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
