package com.example.sam.convert101;

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

//    String[] units = {"ns","us","ms","s","min","h","d","w"};   //vector of available units
    String[] units = {"Nanoseconds","Microseconds","Miliseconds","Seconds","Minutes","Hours","Days","Weeks"};   //vector of available units
//    String[][] convMatrix = {{"1.","0.001","0.000016666666667"},
//                                 {"1000.0","1.0","0.016666666667"},
//                                 {"60000.0","60.0","1.0"}};  //conversion matrix

    String[][] convMatrix = {{"1.0","0.001","1E-06","1E-09","1.66667E-14","2.7777888889E-13","1.15740740740741E-14","1.65343915343915E-15"},
        {"1000.0","1.0","0.001","1E-06","1.66667E-11","2.7777888889E-10","1.15740740740741E-11","1.65343915343915E-12"},
        {"1000000.0","1000.0","1.0","0.001","1.66667E-05","2.7777888889E-07","1.15740740740741E-08","1.65343915343915E-09"},
        {"1000000000.0","1000000.0","1000.0","1.0","0.0166667","0.00027777888889","1.15740740740741E-05","1.65343915343915E-06"},
        {"60000000000.0","60000000.0","60000.0","60.0","1.0","0.0166667","0.000694444444444","9.92063492063492E-05"},
        {"3600000000000.0","3600000000.0","3600000.0","3600.0","60.0","1.0","0.041666666666667","0.005952380952381"},
        {"86400000000000.0","86400000000.0","86400000.0","86400.0","1440.0","24.0","1.0","0.142857142857143"},
        {"604800000000000.0","604800000000.0","604800000.0","604800.0","10080.0","168.0","7.0","1.0"}};


    //TODO: Locale requires different floating point!
    //TODO: Write formatter class for output:
    //      - numbers > 10^6? will be displayed as x.xEx
    //      - numbers < 10^(-6)? will be displayed as x.xE-x
    //      - keep 6? decimals
    String inputValue = "1.0";
    int selectedUnit = 0;
    BigDecimal[] outputValues = {new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][0]) ),
            new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][1]) ),
            new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][2]) ),
            new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][3]) ),
            new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][4]) ),
            new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][5]) ),
            new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][6]) ),
            new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][7]) )};

    UnitData[] timeData = new UnitData[units.length];

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

        for (int i = 0; i<units.length; i++) {
            timeData[i] = new UnitData(units[i], outputValues[i]);
            adapter.addAll(timeData[i]);
        }
        listView.setAdapter(adapter);



        final EditText editText = findViewById(R.id.et_conv_number);
        editText.setText(inputValue);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.clear();
                if(editText.getText().toString().trim().equals("")) {
                    outputValues = new BigDecimal[]{new BigDecimal("0.0"),
                                                    new BigDecimal("0.0"),
                                                    new BigDecimal("0.0"),
                                                    new BigDecimal("0.0"),
                                                    new BigDecimal("0.0"),
                                                    new BigDecimal("0.0"),
                                                    new BigDecimal("0.0"),
                                                    new BigDecimal("0.0")};
                } else {
                    inputValue = editText.getText().toString();
                    for(int i = 0; i<outputValues.length; i++){
                        outputValues[i] = new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][i]) );
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
            outputValues[i] = new BigDecimal(inputValue).multiply( new BigDecimal(convMatrix[selectedUnit][i]) );
        }
        for (int i = 0; i<units.length; i++) {
            timeData[i] = new UnitData(units[i], outputValues[i]);
            adapter.addAll(timeData[i]);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}

