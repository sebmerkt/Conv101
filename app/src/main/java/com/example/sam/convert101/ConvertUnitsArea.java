package com.example.sam.convert101;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class ConvertUnitsArea extends ConvertUnitsBase implements AdapterView.OnItemSelectedListener {

    UsersAdapter adapter;
    ListView listView;

//    TODO: Put mm^2 back in
    //Conversion factors to go from {mm^2 -> cm^2, cm^2 -> m^2, m^2 -> are, are -> ha, ha -> km^2, km^2 -> in^2, in^2 -> ft^2, ft^2 -> yd^2, yd^2 -> ac, ac -> mi^2}
//    double[] convFactors = {100.0, 10000.0, 100.0, 100.0, 100.0, 0.00000000064516, 144.0, 9.0, 4840.0, 640.0};

//    Conversion factors to go from {mm^2 -> cm^2, cm^2 -> m^2, m^2 -> are, are -> ha, ha -> km^2, km^2 -> in^2, in^2 -> ft^2, ft^2 -> yd^2, yd^2 -> ac, ac -> mi^2}
    double[] convFactors = {10000.0, 100.0, 100.0, 100.0, 0.00000000064516, 144.0, 9.0, 4840.0, 640.0};

    //Size of conversion matrix
    int convMatrixDim = 10;
    double[][] convMatrix = new double[convMatrixDim][convMatrixDim];

    //Default EditText value
    double inputValue = 1.0;
    //Default output unit
    int selectedUnit = 0;
    //Results to be displayed
    double[] outputValues = new double[convMatrixDim];

    //Initialize unit data
    UnitData[] areaData;
    String[] units = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_units_base);

        Resources res = getResources();
        units = res.getStringArray(R.array.area_units);
        //Initialize area units
        areaData = new UnitData[units.length];
        //Initialize conversion
        updateOutputValues(outputValues, inputValue, convMatrix, selectedUnit);
        fillMatrix (convMatrix, convFactors);


        // Construct the data source
        ArrayList<UnitData> arrayOfItems = new ArrayList<>();
        // Create the adapter to convert the array to views
        adapter = new UsersAdapter(this, arrayOfItems);
        // Attach the adapter to a ListView
        listView = findViewById(R.id.lv_convert_units_results);
        updateUnitData(units, areaData, adapter, outputValues);
        listView.setAdapter(adapter);

        //Initialize EditText; to input values
        final EditText editText = findViewById(R.id.et_conv_number);
        editText.setText(String.valueOf(inputValue));


        // EditText: Listen for user input of the EditText and update the results list
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // clear the current view
                adapter.clear();
                // If EditText is empty, display zero
                // If EditText is not empty, calculate new result and fill list of results
                if(editText.getText().toString().trim().equals("") || editText.getText().toString().trim().equals(".") || editText.getText().toString().trim().equals(",")) {
                    outputValues = resetOutputValues(outputValues, convMatrixDim);
                } else {
                    // Get value of EditText
                    inputValue = Double.valueOf(editText.getText().toString());
                    // Update all result values
                    updateOutputValues(outputValues, inputValue, convMatrix, selectedUnit);
                }
                updateUnitData(units, areaData, adapter, outputValues);
                listView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

        });

        int stringArrayUnits = R.array.area_units;
        int stringTimeDefault = R.string.string_squaremeter;

        // Spinner for base unit selection
        Spinner spinner = findViewById(R.id.spinner_select_unit);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                stringArrayUnits, R.layout.unit_spinner_item);
        // Specify the layout to use when the list of choices appears
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(R.layout.unit_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerAdapter);


        spinner.setSelection(getIndex(spinner, getString(stringTimeDefault)));
        spinner.setOnItemSelectedListener(this);


    }

    //Update spinner information:
    // User selects different unit
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // Clear results list
        adapter.clear();
        // Update selected unit
        selectedUnit = pos;
        // Update results list
        updateOutputValues(outputValues, inputValue, convMatrix, selectedUnit);
        updateUnitData(units, areaData, adapter, outputValues);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }



}

