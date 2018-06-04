
package com.example.caroline.foodme.EdamamObjects.PojoObjectRecipe;

import java.util.List;
import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class JSONObject {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("from")
    private Long mFrom;
    @SerializedName("hits")
    private List<Hit> mHits;
    @SerializedName("more")
    private Boolean mMore;
    @SerializedName("params")
    private Params mParams;
    @SerializedName("q")
    private String mQ;
    @SerializedName("to")
    private Long mTo;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Long getFrom() {
        return mFrom;
    }

    public void setFrom(Long from) {
        mFrom = from;
    }

    public List<Hit> getHits() {
        return mHits;
    }

    public void setHits(List<Hit> hits) {
        mHits = hits;
    }

    public Boolean getMore() {
        return mMore;
    }

    public void setMore(Boolean more) {
        mMore = more;
    }

    public Params getParams() {
        return mParams;
    }

    public void setParams(Params params) {
        mParams = params;
    }

    public String getQ() {
        return mQ;
    }

    public void setQ(String q) {
        mQ = q;
    }

    public Long getTo() {
        return mTo;
    }

    public void setTo(Long to) {
        mTo = to;
    }

}
