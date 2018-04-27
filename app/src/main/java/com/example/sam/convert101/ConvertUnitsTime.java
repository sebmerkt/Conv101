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

    int convMatrixDim = 10;
    double[][] convMatrix = new double[convMatrixDim][convMatrixDim];


    //TODO: Locale requires different floating point!
    //TODO: Write formatter class for output:
    //      - numbers > 10^6? will be displayed as x.xEx
    //      - numbers < 10^(-6)? will be displayed as x.xE-x
    //      - keep 6? decimals
    double inputValue = 1.0;
    int selectedUnit = 0;
    double[] outputValues = {inputValue*convMatrix[selectedUnit][0],
                             inputValue*convMatrix[selectedUnit][1],
                             inputValue*convMatrix[selectedUnit][2],
                             inputValue*convMatrix[selectedUnit][3],
                             inputValue*convMatrix[selectedUnit][4],
                             inputValue*convMatrix[selectedUnit][5],
                             inputValue*convMatrix[selectedUnit][6],
                             inputValue*convMatrix[selectedUnit][7],
                             inputValue*convMatrix[selectedUnit][8],
                             inputValue*convMatrix[selectedUnit][9]};

    UnitData[] timeData;
    String[] units = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_units_base);

        Resources res = getResources();
        units = res.getStringArray(R.array.time_units);
        timeData = new UnitData[units.length];
        fillMatrix (convMatrix, convFactors);

        // Construct the data source
        ArrayList<UnitData> arrayOfItems = new ArrayList<>();
        // Create the adapter to convert the array to views
        adapter = new UsersAdapter(this, arrayOfItems);
        // Attach the adapter to a ListView
        listView = findViewById(R.id.lv_convert_units_results);

        for (int i = 0; i<units.length; i++) {
            timeData[i] = new UnitData(units[i], outputValues[i]);
            adapter.addAll(timeData[i]);
        }
        listView.setAdapter(adapter);



        final EditText editText = findViewById(R.id.et_conv_number);
        editText.setText(String.valueOf(inputValue));


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.clear();
                if(editText.getText().toString().trim().equals("") || editText.getText().toString().trim().equals(".") || editText.getText().toString().trim().equals(",")) {
                    outputValues = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
                } else {
                    inputValue = Double.valueOf(editText.getText().toString());
                    //Number of decimal points of the input
                    double scale = Double.valueOf(new BigDecimal(editText.getText().toString()).toString());
                    for(int i = 0; i<outputValues.length; i++){
                        outputValues[i] = inputValue*convMatrix[selectedUnit][i];
                    }
                }

                for (int i = 0; i<units.length; i++) {
                    timeData[i] = new UnitData(units[i], outputValues[i]);
                    adapter.addAll(timeData[i]);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

        });

        // Spinner for base unit selection
        Spinner spinner = findViewById(R.id.spinner_select_unit);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinneradapter = ArrayAdapter.createFromResource(this,
                R.array.time_units, R.layout.unit_spinner_item);
        // Specify the layout to use when the list of choices appears
//        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneradapter.setDropDownViewResource(R.layout.unit_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinneradapter);


        spinner.setSelection(getIndex(spinner, getString(R.string.string_second)));
        spinner.setOnItemSelectedListener(this);


    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        adapter.clear();
        selectedUnit = pos;
        for(int i = 0; i<outputValues.length; i++){
            outputValues[i] = inputValue*convMatrix[selectedUnit][i];
        }
        for (int i = 0; i<units.length; i++) {
            timeData[i] = new UnitData(units[i], outputValues[i]);
            adapter.addAll(timeData[i]);
        }
    }


    public void onNothingSelected(AdapterView<?> parent) {
    }
}

