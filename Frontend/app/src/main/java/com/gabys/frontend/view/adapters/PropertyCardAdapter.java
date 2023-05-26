package com.gabys.frontend.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gabys.frontend.R;
import com.gabys.frontend.controller.EmployeeController;
import com.gabys.frontend.controller.IController;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.view.AddRentActivity;
import com.gabys.frontend.view.EditPropertyActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PropertyCardAdapter extends RecyclerView.Adapter<PropertyCardAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<Property> propertiesList;
    private int role;

    private IController controller;

    public PropertyCardAdapter(Context context) {
        this.context = context;
        this.propertiesList = new ArrayList<>();
        this.role = 0;
    }
    public void setUserRole(int userRole){
        this.role = userRole;
    }

    public void setItems(ArrayList<Property> properties) {
        this.propertiesList.clear();
        this.propertiesList.addAll(properties);
        notifyDataSetChanged();
    }

    public void addController(IController controller){
        this.controller = controller;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_property, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Property model = propertiesList.get(position);
        Glide.with(this.context)
                .load(model.getImageURL())
                .centerCrop()
                .into(holder.propertyImage);
        holder.propertyTitle.setText(model.getTitle());

        String location = context.getString(R.string.property_location);
        location += ": " + model.getLocation();

        String roomsNo = context.getString(R.string.property_rooms);
        roomsNo += ": " + String.valueOf(model.getRoomsNo());

        ArrayList<String> types = Arrays.stream(context.getResources().getStringArray(R.array.property_types)).collect(Collectors.toCollection(ArrayList::new));
        types.remove(0);

        String type = context.getString(R.string.property_type);
        type += ": " + types.get(model.getType());

        String price = context.getString(R.string.property_price);
        price += ": " + String.valueOf(model.getPrice());

        holder.propertyLocation.setText(location);
        holder.propertyRoomsNo.setText(roomsNo);
        holder.propertyType.setText(type);
        holder.propertyPrice.setText(price);
        holder.propertyAvailability.setText(model.isAvailable() ? R.string.available : R.string.unavailable);

        holder.deleteButton.setText(context.getString(R.string.button_delete));
        holder.modifyButton.setText(context.getString(R.string.button_modify));
        holder.rentButton.setText(context.getString(R.string.button_rent));


        if (role != 1)
            holder.buttonLayout.setVisibility(View.GONE);


        holder.modifyButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditPropertyActivity.class);

            intent.putExtra("property", new Gson().toJson(model));

            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> ((EmployeeController)controller).deleteProperty(model.getId()));

        holder.rentButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddRentActivity.class);

            intent.putExtra("property", new Gson().toJson(model));

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }

    public void updateLang() {
        notifyDataSetChanged();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //complete with card details

        private final TextView propertyTitle;
        private final TextView propertyLocation;
        private final TextView propertyRoomsNo;
        private final TextView propertyType;
        private final TextView propertyPrice;
        private final TextView propertyAvailability;
        private final ImageView propertyImage;
        private final LinearLayout buttonLayout;
        private final Button modifyButton;
        private final Button deleteButton;
        private final Button rentButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            propertyTitle = itemView.findViewById(R.id.card_title);
            propertyLocation = itemView.findViewById(R.id.card_location);
            propertyRoomsNo = itemView.findViewById(R.id.card_roomsNo);
            propertyType = itemView.findViewById(R.id.card_type);
            propertyPrice = itemView.findViewById(R.id.card_price);
            propertyAvailability = itemView.findViewById(R.id.card_isAvailable);
            propertyImage = itemView.findViewById(R.id.card_image);

            buttonLayout = itemView.findViewById(R.id.button_layout);

            modifyButton = itemView.findViewById(R.id.modify_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
            rentButton = itemView.findViewById(R.id.rent_button);
        }
    }
}
