
package com.example.caroline.foodme.EdamamObjects.PojoObjectRecipe;

import java.util.List;
import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Params {

    @SerializedName("app_id")
    private List<String> mAppId;
    @SerializedName("app_key")
    private List<String> mAppKey;
    @SerializedName("from")
    private List<String> mFrom;
    @SerializedName("q")
    private List<String> mQ;
    @SerializedName("sane")
    private List<Object> mSane;
    @SerializedName("to_50")
    private List<String> mTo50;

    public List<String> getAppId() {
        return mAppId;
    }

    public void setAppId(List<String> appId) {
        mAppId = appId;
    }

    public List<String> getAppKey() {
        return mAppKey;
    }

    public void setAppKey(List<String> appKey) {
        mAppKey = appKey;
    }

    public List<String> getFrom() {
        return mFrom;
    }

    public void setFrom(List<String> from) {
        mFrom = from;
    }

    public List<String> getQ() {
        return mQ;
    }

    public void setQ(List<String> q) {
        mQ = q;
    }

    public List<Object> getSane() {
        return mSane;
    }

    public void setSane(List<Object> sane) {
        mSane = sane;
    }

    public List<String> getTo50() {
        return mTo50;
    }

    public void setTo50(List<String> to50) {
        mTo50 = to50;
    }

}
