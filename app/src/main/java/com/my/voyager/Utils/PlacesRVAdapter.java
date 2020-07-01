package com.my.voyager.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.voyager.R;
import com.my.voyager.model.Place;

import java.util.List;

public class PlacesRVAdapter extends  RecyclerView.Adapter<PlacesRVAdapter.MyViewHolder> {
    private List<Place> places;
    private OnUserClick onUserClick;

    public PlacesRVAdapter(List<Place> places, OnUserClick onUserClick) {
        this.places = places;
        this.onUserClick = onUserClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_places, parent, false);

        MyViewHolder vh = new MyViewHolder(v,onUserClick);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)  {
        Place currentPlace = places.get(position);
        holder.name.setText(currentPlace.getName());
        holder.diameter.setText(String.valueOf(currentPlace.getDiameter()) + " m");
        holder.photo.setImageBitmap(currentPlace.getImage());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        // each data item is just a string in this case
        public TextView name;
        public TextView diameter;
        public ImageView photo;
        OnUserClick onDebtorClick;

        public MyViewHolder(View v, OnUserClick onDebtorClick) {
            super(v);
            name = v.findViewById(R.id.nameText);
            diameter = v.findViewById(R.id.diameterText);
            photo = v.findViewById(R.id.placeImage);
            this.onDebtorClick =onDebtorClick;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDebtorClick.onUserClick(getAdapterPosition());
        }

    }

}