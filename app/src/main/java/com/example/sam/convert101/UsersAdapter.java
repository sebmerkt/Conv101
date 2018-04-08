package com.example.sam.convert101;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.math.BigDecimal;
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
        //TODO: better rounding
        BigDecimal outVal = new BigDecimal(unit.getUnitValue());
        BigDecimal outVal2 = outVal.setScale(6, BigDecimal.ROUND_HALF_UP);
//        tvUnitValue.setText(outVal2.toString());
          tvUnitValue.setText(unit.roundUnitValue(unit.getUnitValue()));
//        tvUnitValue.setText((unit.getUnitValue()).setScale(4, BigDecimal.ROUND_HALF_UP).toString());
        tvUnitType.setText(unit.getUnit());


        // Return the completed view to render on screen
        return convertView;
    }




}

