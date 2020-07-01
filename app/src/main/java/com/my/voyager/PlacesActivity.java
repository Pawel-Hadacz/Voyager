package com.my.voyager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.my.voyager.Utils.OnUserClick;
import com.my.voyager.Utils.PlacesRVAdapter;
import com.my.voyager.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity implements OnUserClick {

    private List<Place> placeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        recyclerView =  findViewById(R.id.placesList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        if(placeList.isEmpty() && placeList!= null){
                mAdapter = new PlacesRVAdapter(placeList,this);
                recyclerView.setAdapter(mAdapter);
        }
    }
    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this,PlacesManagement.class);
        intent.putExtra("PLACE_TO_EDIT_NAME",placeList.get(position).getName());
        intent.putExtra("PLACE_TO_EDIT_ID",placeList.get(position).getId());
        intent.putExtra("PLACE_TO_EDIT_DIAMETER",placeList.get(position).getDiameter());
        intent.putExtra("PLACE_TO_EDIT_PHOTO",placeList.get(position).getImage());
        finish();
        startActivity(intent);

    }
}