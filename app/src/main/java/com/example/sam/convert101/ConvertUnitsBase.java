package com.example.sam.convert101;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public abstract class ConvertUnitsBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String colorSelector =
                PreferenceManager.getDefaultSharedPreferences(ConvertUnitsBase.this)
                        .getString("pref_color_scheme", "Blue");

        if (colorSelector.equals("Blue")) {
            setTheme(R.style.AppThemeBase);
        }
        else if (colorSelector.equals("Yellow")) {
            setTheme(R.style.AppThemeYellowBase);
        }
        else if (colorSelector.equals("Dark")) {
            setTheme(R.style.AppThemeDarkBase);
        }
        else {
            setTheme(R.style.AppThemeBase);
        }

        setContentView(R.layout.convert_units_base);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        EditText editText = findViewById(R.id.et_conv_number);
        String unitValue = editText.getText().toString();
        savedInstanceState.putString("unitValue", unitValue);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        String unitValue = savedInstanceState.getString("unitValue");

        EditText editText = findViewById(R.id.et_conv_number);
        editText.setText(unitValue);
        editText.setSelection(editText.getText().length());

    }

    public int getIndex(Spinner spinner, String defaultPos) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(defaultPos)) {
                return i;
            }
        }
        return 0;
    }

    public void updateOutputValues(double[] out, double in, double[][] mat, int unit){
        for( int i = 0; i < mat.length; i++ ){
            out[i]=in*mat[unit][i];
        }
    }


    public void fillMatrix (double[][] mat, double[] fac) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                int chk = j-i;
                if(chk>0){
                    double tmp = 1.;
                    for(int k = 1; k<=chk; k++){
                        tmp *= fac[j-k];
                    }
                    mat[i][j] = 1/tmp;
                }
                else if (chk<0){
                    double tmp = 1.;
                    for(int k = 1; k<=Math.abs(chk); k++){
                        tmp *= fac[i-k];
                    }
                    mat[i][j] = tmp;
                }
                else{
                    mat[i][j] = 1.0;
                }
            }
        }
    }


    public double[] resetOutputValues(double[] out, int dim){
        for(int i = 0; i<dim; i++) {
            out[i] = 0.0;
        }
        return out;
    }

    public void updateUnitData(String[] unitNames, UnitData[] data, UsersAdapter adapter, double[] out){
        for (int i = 0; i<unitNames.length; i++) {
            data[i] = new UnitData(unitNames[i], out[i]);
            adapter.addAll(data[i]);
        }
    }


}
