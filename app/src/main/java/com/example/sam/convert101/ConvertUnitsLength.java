package com.example.sam.convert101;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertUnitsLength extends ConvertUnitsBase implements AdapterView.OnItemSelectedListener {

    private ArrayAdapter<String> resultArrayAdapter;

    String[] units = {"mm","m","km"};   //vector of available units
    double[][] convMatrix = {{1.,Math.pow(10., -3.),Math.pow(10., -6.)},{Math.pow(10., 3.),1,Math.pow(10., -3.)},{Math.pow(10., 6.),Math.pow(10., 3.),1}};  //conversion matrix
    double inputValue = 1.;
    int selectedUnit = 0;
    String[] outputValuesStrings = {Double.toString(inputValue*convMatrix[selectedUnit][0]) + " " + units[0],
                                    Double.toString(inputValue*convMatrix[selectedUnit][1]) + " " + units[1],
                                    Double.toString(inputValue*convMatrix[selectedUnit][2]) + " " + units[2]};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_units_base);

        ListView resultListView = findViewById(R.id.lv_convert_units_results);

        resultArrayAdapter = new ArrayAdapter<>(ConvertUnitsLength.this,R.layout.convert_units_textview);


        // Set the ArrayAdapter as the ListView's adapter.
        resultListView.setAdapter( resultArrayAdapter );

        resultArrayAdapter.addAll(outputValuesStrings);



        final EditText editText = findViewById(R.id.et_conv_number);
        editText.setText(Double.toString(inputValue));


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText.getText().toString().trim().equals("")) {

                    String[] newIObjects ={"0.0 mm","0.0 m","0.0 km"};
                    resultArrayAdapter.clear();
                    resultArrayAdapter.addAll(newIObjects);
                } else {
                    inputValue = Double.parseDouble(editText.getText().toString());

                    for(int i = 0; i<outputValuesStrings.length; i++){
                        outputValuesStrings[i] = Double.toString(inputValue*convMatrix[selectedUnit][i]) + " " + units[i];
                    }
                    resultArrayAdapter.clear();
                    resultArrayAdapter.addAll(outputValuesStrings);
                }
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
                R.array.length_units, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinneradapter);


        spinner.setSelection(getIndex(spinner, "m"));
        spinner.setOnItemSelectedListener(this);


    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        selectedUnit = pos;
        for(int i = 0; i<outputValuesStrings.length; i++){
            outputValuesStrings[i] = Double.toString(inputValue*convMatrix[selectedUnit][i]) + " " + units[i];
        }
        resultArrayAdapter.clear();
        resultArrayAdapter.addAll(outputValuesStrings);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
