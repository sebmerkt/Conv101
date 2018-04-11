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
        if(bigValue.compareTo(new BigDecimal("10000.0"))==1){   //TODO: String -> localization!
//            Formatter formatter = new Formatter();
            double tmp = value;
            int counter = 0;
            while (tmp>=10){
                tmp= tmp / 10;
                counter++;
            }

            BigDecimal output = new BigDecimal(tmp).setScale(4, BigDecimal.ROUND_HALF_UP);
            String returnVal = removeZeros(output.toString()) + "E" + String.valueOf(counter);

            return returnVal;
        }
        else if(bigValue.compareTo(new BigDecimal("0.0001"))==-1){
            double tmp = value;
            int counter = 0;
            while (tmp<1){
                tmp= tmp * 10;
                counter++;
                if (counter>50){
                    return "0";
                }
            }

            BigDecimal output = new BigDecimal(tmp).setScale(4, BigDecimal.ROUND_HALF_UP);
            String returnVal = removeZeros(output.toString()) + "E-" + String.valueOf(counter);

            return returnVal;
        }
        else{
//            return "0.0";
            BigDecimal output = new BigDecimal(value).setScale(4, BigDecimal.ROUND_HALF_UP);
            return removeZeros(output.toString());
        }
    }


    public String removeZeros( String str ){
        if (str == null){
            return null;
        }
        char[] chars = str.toCharArray();
        int index  = str.length() -1;
        for (; index >=0;index--) {
            if (chars[index] != '0'){
                break;
            }
        }
        return (index == str.length()-1) ? str : str.substring(0,index+1);
    }


}
