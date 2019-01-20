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

package com.example.sam.convert1012wear;


import android.content.Context;
import android.preference.PreferenceManager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class UnitData {
    //    private long id;
    String unitType;
    String unit;
    double unitValue;
    int precisionValue;

    UnitData (String inputunit, double value, int prec) {
        this.unit = inputunit;
        this.unitValue = value;
        this.precisionValue = prec;
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

    public void setPrecisionValue(int value){
        this.precisionValue = value;
    }

    public int getPrecisionValue(){
        return this.precisionValue;
    }

    public String roundUnitValue(double value, int precision){
        BigDecimal bigValue = new BigDecimal(value);
        if( ( bigValue.compareTo(new BigDecimal(String.valueOf(Math.pow(10,4))))==1 ) || ( bigValue.compareTo(new BigDecimal(String.valueOf(Math.pow(10,-(precision-2)))))==-1 ) ){
            NumberFormat numberFormat  = new DecimalFormat("##");
            numberFormat = new DecimalFormat("0.0##E0");
            String returnVal = numberFormat.format(bigValue);

            return returnVal;
        }
        else{
            NumberFormat numberFormat  = new DecimalFormat("##");
            numberFormat = new DecimalFormat("0.0##");
            String returnVal = numberFormat.format(bigValue);

            return removeZeros(returnVal);
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
