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


public class ConvertUnitsSpeed extends ConvertUnitsBase implements AdapterView.OnItemSelectedListener {

    UsersAdapter adapter;
    ListView listView;

//    Conversion factors to go from {}
    double[] convFactors = {0.277778, 1.09728, 	1.466667, 1.150779};

    //Size of conversion matrix
    int convMatrixDim = convFactors.length+1;
    double[][] convMatrix = new double[convMatrixDim][convMatrixDim];

    //Default EditText value
    double inputValue = 1.0;
    //Default output unit
    int selectedUnit = 0;
    //Results to be displayed
    double[] outputValues = new double[convMatrixDim];

    //Initialize unit data
    UnitData[] speedData;
    String[] units = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_units_base);

        Resources res = getResources();
        units = res.getStringArray(R.array.speed_units);

        //Initialize speed units
        speedData = new UnitData[units.length];
        //Initialize conversion
        updateOutputValues(outputValues, inputValue, convMatrix, selectedUnit);
        fillMatrix (convMatrix, convFactors);


        // Construct the data source
        ArrayList<UnitData> arrayOfItems = new ArrayList<>();
        // Create the adapter to convert the array to views
        adapter = new UsersAdapter(this, arrayOfItems);
        // Attach the adapter to a ListView
        listView = findViewById(R.id.lv_convert_units_results);
        updateUnitData(units, speedData, adapter, outputValues);
        listView.setAdapter(adapter);

        //Initialize EditText; to input values
        final EditText editText = findViewById(R.id.et_conv_number);
        editText.setText(String.valueOf(inputValue));
        editText.setSelection(editText.getText().length());


        // EditText: Listen for user input of the EditText and update the results list
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // clear the current view
                adapter.clear();
                // If EditText is empty, display zero
                // If EditText is not empty, calculate new result and fill list of results
                if(editText.getText().toString().trim().equals("") || editText.getText().toString().trim().equals(".") || editText.getText().toString().trim().equals(",") || editText.getText().toString().trim().equals("-")) {
                    outputValues = resetOutputValues(outputValues, convMatrixDim);
                } else {
                    // Get value of EditText
                    inputValue = Double.valueOf(editText.getText().toString());
                    // Update all result values
                    updateOutputValues(outputValues, inputValue, convMatrix, selectedUnit);
                }
                updateUnitData(units, speedData, adapter, outputValues);
                listView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

        });

        int stringArrayUnits = R.array.speed_units;
        int stringSpeedDefault = R.string.string_kmph_abbrev;


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


        spinner.setSelection(getIndex(spinner, getString(stringSpeedDefault)));
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
        updateUnitData(units, speedData, adapter, outputValues);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }



}

