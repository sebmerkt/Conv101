package com.example.sam.convert101;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.math.BigDecimal;
import java.util.ArrayList;


public class ConvertUnitsTime extends ConvertUnitsBase implements AdapterView.OnItemSelectedListener {

    UsersAdapter adapter;
    ListView listView;

    //Conversion factors to go from {ns -> us, us -> ms, ms -> s, s -> min, min -> hours, hours -> days, days -> weeks, weeks -> months}
    double[] convFactors = {1000.0, 1000.0, 1000.0, 60.0, 60.0, 24.0, 7.0, 4.34524, 12};

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
    UnitData[] timeData;
    String[] units = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_units_base);

        Resources res = getResources();
        units = res.getStringArray(R.array.time_units);
        //Initialize time units
        timeData = new UnitData[units.length];
        //Initialize conversion
        updateOutputValues(outputValues, inputValue, convMatrix, selectedUnit);
        fillMatrix (convMatrix, convFactors);


        // Construct the data source
        ArrayList<UnitData> arrayOfItems = new ArrayList<>();
        // Create the adapter to convert the array to views
        adapter = new UsersAdapter(this, arrayOfItems);
        // Attach the adapter to a ListView
        listView = findViewById(R.id.lv_convert_units_results);
        updateUnitData(units, timeData);
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
                updateUnitData(units, timeData);
                listView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

        });

        int stringArrayUnits = R.array.time_units;
        int stringTimeDefault = R.string.string_second;

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

    // User selects different unit
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // Clear results list
        adapter.clear();
        // Update selected unit
        selectedUnit = pos;
        // Update results list
        updateOutputValues(outputValues, inputValue, convMatrix, selectedUnit);
        updateUnitData(units, timeData);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }






    public void updateUnitData(String[] unitNames, UnitData[] data){
        for (int i = 0; i<unitNames.length; i++) {
            data[i] = new UnitData(unitNames[i], outputValues[i]);
            adapter.addAll(data[i]);
        }
    }

}

