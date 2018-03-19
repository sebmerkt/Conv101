package com.example.sam.convert101;


import java.math.BigDecimal;

public class UnitData {
//    private long id;
    String unitType;
    String unit;
    BigDecimal unitValue;

    UnitData (String inputunit, BigDecimal value ) {
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

    public void setUnitValue(BigDecimal value){
            this.unitValue = value;
    }

    public BigDecimal getUnitValue(){
        return this.unitValue;
    }

//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getId() {
//        return id;
//    }

}
