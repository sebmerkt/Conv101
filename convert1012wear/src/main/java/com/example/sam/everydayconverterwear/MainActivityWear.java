/*
    Copyright 2019 Sebastian Merkt

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.example.io.everydayconverterwear;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.wearable.intent.RemoteIntent;

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

        Button infoButton = findViewById(R.id.button_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mInfoIntent = new Intent(Intent.ACTION_VIEW)
                        .addCategory(Intent.CATEGORY_BROWSABLE)
                        .setData(Uri.parse(getString(R.string.string_blog)));

                RemoteIntent.startRemoteActivity(MainActivityWear.this, mInfoIntent, null);

            }
        });

    }
}


//TODO: Improvement: Button for "Open app on phone" for unit explanation etc.