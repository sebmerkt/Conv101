package com.example.sam.convert1012wear;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.Spinner;


public abstract class ConvertUnitsBase extends WearableActivity {

    int stringarray_units;
    int string_timedefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_units_base);


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