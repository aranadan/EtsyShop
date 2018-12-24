package com.fox.andrey.etsyshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
     @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("short_name")
    @Expose
    private String shortName;

    public String getCategoryName() {
        return categoryName;
    }

    public String getShortName() {
        return shortName;
    }
}
