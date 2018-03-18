package com.example.sam.convert101;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class ConvertUnitsTime extends ConvertUnitsBase implements AdapterView.OnItemSelectedListener {

    UsersAdapter adapter;
    ListView listView;


    String[] units = {"ms","s","min"};   //vector of available units
    double[][] convMatrix = {{1.,Math.pow(10., -3.),1.66667*Math.pow(10.,-5)},{Math.pow(10., 3.),1,0.0166667},{60000.,60.,1}};  //conversion matrix
    double inputValue = 1.;
    int selectedUnit = 0;
    double[] outputValues = {inputValue*convMatrix[selectedUnit][0],
           inputValue*convMatrix[selectedUnit][1],
            inputValue*convMatrix[selectedUnit][2]};

    UnitData timeData0 = new UnitData(units[0], outputValues[0]);
    UnitData timeData1 = new UnitData(units[1], outputValues[1]);
    UnitData timeData2 = new UnitData(units[2], outputValues[2]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_units_base);



        // Construct the data source
        ArrayList<UnitData> arrayOfItems = new ArrayList<>();
        // Create the adapter to convert the array to views
        adapter = new UsersAdapter(this, arrayOfItems);
        // Attach the adapter to a ListView
        listView = findViewById(R.id.lv_convert_units_results);
        adapter.addAll(timeData0);
        adapter.addAll(timeData1);
        adapter.addAll(timeData2);
        listView.setAdapter(adapter);



        final EditText editText = findViewById(R.id.et_conv_number);
        editText.setText(Double.toString(inputValue));


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.clear();
                if(editText.getText().toString().trim().equals("")) {

                    outputValues = new double[]{0., 0., 0.};
                    timeData0.setUnitValue(0.);
                    timeData1.setUnitValue(0.);
                    timeData2.setUnitValue(0.);
                    adapter.addAll(timeData0);
                    adapter.addAll(timeData1);
                    adapter.addAll(timeData2);

                } else {
                    inputValue = Double.parseDouble(editText.getText().toString());


                    for(int i = 0; i<outputValues.length; i++){
                        outputValues[i] = inputValue*convMatrix[selectedUnit][i];
                    }
                    timeData0.setUnitValue(outputValues[0]);
                    timeData1.setUnitValue(outputValues[1]);
                    timeData2.setUnitValue(outputValues[2]);
                    adapter.addAll(timeData0);
                    adapter.addAll(timeData1);
                    adapter.addAll(timeData2);
                }

//                adapter.addAll(timeData1);
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
                R.array.time_units, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinneradapter);


        spinner.setSelection(getIndex(spinner, "s"));
        spinner.setOnItemSelectedListener(this);


    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        adapter.clear();
        selectedUnit = pos;
        for(int i = 0; i<outputValues.length; i++){
            outputValues[i] = inputValue*convMatrix[selectedUnit][i];
        }
        timeData0.setUnitValue(outputValues[0]);
        timeData1.setUnitValue(outputValues[1]);
        timeData2.setUnitValue(outputValues[2]);
        adapter.addAll(timeData0);
        adapter.addAll(timeData1);
        adapter.addAll(timeData2);
        listView.setAdapter(adapter);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}

