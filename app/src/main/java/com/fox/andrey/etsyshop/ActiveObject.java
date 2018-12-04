package com.fox.andrey.etsyshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActiveObject {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<ActiveResult> results = null;

    public Integer getCount() {
        return count;
    }

    public List<ActiveResult> getResults() {
        return results;
    }

}
