package com.fox.andrey.etsyshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesResult {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<ImageItem> results = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ImageItem> getResults() {
        return results;
    }

    public void setResults(List<ImageItem> results) {
        this.results = results;
    }
}
