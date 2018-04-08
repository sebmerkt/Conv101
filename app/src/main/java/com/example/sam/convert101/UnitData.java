package com.example.sam.convert101;


import android.support.design.widget.Snackbar;

import java.math.BigDecimal;
import java.util.Formatter;

public class UnitData {
//    private long id;
    String unitType;
    String unit;
    double unitValue;

    UnitData (String inputunit, double value ) {
        this.unit = inputunit;
        this.unitValue = value;
    }

    public void setUnitType(String inputunit){
            this.unitType = inputunit;
    }

    public String getUnitType(){
        return this.unitType;
    }

    public void setUnit(String inputunit){
            this.unit = inputunit;
    }

    public String getUnit(){
        return this.unit;
    }

    public void setUnitValue(double value){
            this.unitValue = value;
    }

    public double getUnitValue(){
        return this.unitValue;
    }

//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getId() {
//        return id;
//    }

    public String roundUnitValue(double value){
        BigDecimal bigValue = new BigDecimal(value);
        if(bigValue.compareTo(new BigDecimal("100000.0"))==1){
//            Formatter formatter = new Formatter();

            return String.format("%09.4f", bigValue);
        }
        else if(bigValue.compareTo(new BigDecimal("0.00001"))==-1){

            return String.format("%09.4f", bigValue);
        }
        else{
            return "0.0";
        }
    }

}
