
package com.example.caroline.foodme.EdamamObjects.PojoObjectRecipe;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SUGARAdded {

    @SerializedName("label")
    private String mLabel;
    @SerializedName("quantity")
    private Double mQuantity;
    @SerializedName("unit")
    private String mUnit;

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public Double getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Double quantity) {
        mQuantity = quantity;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

}
