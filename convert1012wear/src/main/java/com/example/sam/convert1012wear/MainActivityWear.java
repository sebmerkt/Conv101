package com.example.sam.convert1012wear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityWear extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);

        // Enables Always-on
        setAmbientEnabled();

        Button lengthButton = findViewById(R.id.button_length);
        lengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mLengthIntent = new Intent(MainActivityWear.this, ConvertUnitsLength.class);
                MainActivityWear.this.startActivity(mLengthIntent);
            }
        });

        Button areaButton = findViewById(R.id.button_area);
        areaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mAreaIntent = new Intent(MainActivityWear.this, ConvertUnitsArea.class);
                MainActivityWear.this.startActivity(mAreaIntent);
            }
        });

        Button speedButton = findViewById(R.id.button_speed);
        speedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mSpeedIntent = new Intent(MainActivityWear.this, ConvertUnitsSpeed.class);
                MainActivityWear.this.startActivity(mSpeedIntent);
            }
        });

        Button storageButton = findViewById(R.id.button_storage);
        storageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mStorageIntent = new Intent(MainActivityWear.this, ConvertUnitsStorage.class);
                MainActivityWear.this.startActivity(mStorageIntent);
            }
        });

        Button tempButton = findViewById(R.id.button_temperature);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mTempIntent = new Intent(MainActivityWear.this, ConvertUnitsTemp.class);
                MainActivityWear.this.startActivity(mTempIntent);
            }
        });

        Button volumeButton = findViewById(R.id.button_volume);
        volumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mVolumeIntent = new Intent(MainActivityWear.this, ConvertUnitsVolume.class);
                MainActivityWear.this.startActivity(mVolumeIntent);
            }
        });

        Button timeButton = findViewById(R.id.button_time);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mTimeIntent = new Intent(MainActivityWear.this, ConvertUnitsTime.class);
                MainActivityWear.this.startActivity(mTimeIntent);
            }
        });

        Button weightButton = findViewById(R.id.button_weight);
        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mWeightIntent = new Intent(MainActivityWear.this, ConvertUnitsWeight.class);
                MainActivityWear.this.startActivity(mWeightIntent);
            }
        });

    }
}


//TODO: Improvement: Button for "Open app on phone" for unit explanation etc.