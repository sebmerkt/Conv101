package com.example.sam.convert101;


public class UnitData {
    private long id;
    String unitType;
    String unit;
    double unitValue;

    UnitData (String inputunit, double value ) {
        unit = inputunit;
        unitValue = value;
    }

    UnitData () {
        this.unit = "";
        this.unitValue = 0.0;
    }

    public void setUnitType(String inptunit){
            this.unitType = inptunit;
    }

    public String getUnitType(){
        return this.unitType;
    }

    public void setUnit(String inptunit){
            this.unit = inptunit;
    }

    public String getUnit(int pos){
        return this.unit;
    }

    public void setUnitValue(double value){
            this.unitValue = value;
    }

    public double getUnitValue(int pos){
        return this.unitValue;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
