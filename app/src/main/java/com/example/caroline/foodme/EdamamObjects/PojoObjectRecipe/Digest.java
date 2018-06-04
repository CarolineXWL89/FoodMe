
package com.example.caroline.foodme.EdamamObjects.PojoObjectRecipe;

import java.util.List;
import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Digest {

    @SerializedName("daily")
    private Double mDaily;
    @SerializedName("hasRDI")
    private Boolean mHasRDI;
    @SerializedName("label")
    private String mLabel;
    @SerializedName("schemaOrgTag")
    private String mSchemaOrgTag;
    @SerializedName("sub")
    private List<Sub> mSub;
    @SerializedName("tag")
    private String mTag;
    @SerializedName("total")
    private Double mTotal;
    @SerializedName("unit")
    private String mUnit;

    public Double getDaily() {
        return mDaily;
    }

    public void setDaily(Double daily) {
        mDaily = daily;
    }

    public Boolean getHasRDI() {
        return mHasRDI;
    }

    public void setHasRDI(Boolean hasRDI) {
        mHasRDI = hasRDI;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getSchemaOrgTag() {
        return mSchemaOrgTag;
    }

    public void setSchemaOrgTag(String schemaOrgTag) {
        mSchemaOrgTag = schemaOrgTag;
    }

    public List<Sub> getSub() {
        return mSub;
    }

    public void setSub(List<Sub> sub) {
        mSub = sub;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public Double getTotal() {
        return mTotal;
    }

    public void setTotal(Double total) {
        mTotal = total;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

}
