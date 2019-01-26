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

package io.github.sebmerkt.everydayconverterwear;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.wearable.intent.RemoteIntent;

import java.util.ArrayList;

public class MainActivityWear extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);
        WearableRecyclerView recyclerView = findViewById(R.id.main_menu_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        recyclerView.setLayoutManager(new WearableLinearLayoutManager(this));

        ArrayList<MainMenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MainMenuItem(R.drawable.ic_length_ruler, getResources().getString(R.string.string_length)));
        menuItems.add(new MainMenuItem(R.drawable.ic_area, getResources().getString(R.string.string_area)));
        menuItems.add(new MainMenuItem(R.drawable.ic_volume, getResources().getString(R.string.string_volume)));
        menuItems.add(new MainMenuItem(R.drawable.ic_clock, getResources().getString(R.string.string_time)));
        menuItems.add(new MainMenuItem(R.drawable.ic_speed, getResources().getString(R.string.string_speed)));
        menuItems.add(new MainMenuItem(R.drawable.ic_temp, getResources().getString(R.string.string_temperature)));
        menuItems.add(new MainMenuItem(R.drawable.ic_weight, getResources().getString(R.string.string_weight)));
        menuItems.add(new MainMenuItem(R.drawable.ic_storage, getResources().getString(R.string.string_storage)));

        recyclerView.setAdapter(new MainMenuItems(this, menuItems, new MainMenuItems.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer menuPosition) {
                switch (menuPosition){
                    case 0:
                        Intent mLengthIntent = new Intent(MainActivityWear.this, ConvertUnitsLength.class);
                        MainActivityWear.this.startActivity(mLengthIntent);
                        break;
                    case 1:
                        Intent mAreaIntent = new Intent(MainActivityWear.this, ConvertUnitsArea.class);
                        MainActivityWear.this.startActivity(mAreaIntent);
                        break;
                    case 2:
                        Intent mVolumeIntent = new Intent(MainActivityWear.this, ConvertUnitsVolume.class);
                        MainActivityWear.this.startActivity(mVolumeIntent);
                        break;
                    case 3:
                        Intent mTimeIntent = new Intent(MainActivityWear.this, ConvertUnitsTime.class);
                        MainActivityWear.this.startActivity(mTimeIntent);
                        break;
                    case 4:
                        Intent mSpeedIntent = new Intent(MainActivityWear.this, ConvertUnitsSpeed.class);
                        MainActivityWear.this.startActivity(mSpeedIntent);
                        break;
                    case 5:
                        Intent mTempIntent = new Intent(MainActivityWear.this, ConvertUnitsTemp.class);
                        MainActivityWear.this.startActivity(mTempIntent);
                        break;
                    case 6:
                        Intent mWeigthIntent = new Intent(MainActivityWear.this, ConvertUnitsWeight.class);
                        MainActivityWear.this.startActivity(mWeigthIntent);
                        break;
                    case 7:
                        Intent mStorageIntent = new Intent(MainActivityWear.this, ConvertUnitsStorage.class);
                        MainActivityWear.this.startActivity(mStorageIntent);
                        break;
                }
            }
        }));

        // Enables Always-on
        setAmbientEnabled();
//

    }
}
