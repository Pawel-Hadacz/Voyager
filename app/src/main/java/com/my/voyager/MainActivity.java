package com.my.voyager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.placesButton).setOnClickListener(v -> {
            Intent displayList = new Intent(this,PlacesActivity.class);
            finish();
            startActivity(displayList);
        });
        findViewById(R.id.mapButton).setOnClickListener(v -> {
            Intent displayList = new Intent(this,MapsActivity.class);
            finish();
            startActivity(displayList);
        });
    }
}
