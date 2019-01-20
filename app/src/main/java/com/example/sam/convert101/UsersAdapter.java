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

package com.example.sam.convert101;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

class UsersAdapter extends ArrayAdapter<UnitData> {

    UsersAdapter(Context context, ArrayList<UnitData> items ) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView( final int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        UnitData unit = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.conversion_results, parent, false);
        }
        // Lookup view for data population
        TextView tvUnitValue = convertView.findViewById(R.id.tv_result_value);
        TextView tvUnitType = convertView.findViewById(R.id.tv_result_unit);
        // Populate the data into the template view using the data object
        tvUnitValue.setText(unit.roundUnitValue(unit.getUnitValue(),unit.getPrecisionValue()));
        tvUnitType.setText(unit.getUnit());

        // Return the completed view to render on screen
        return convertView;
    }




}

