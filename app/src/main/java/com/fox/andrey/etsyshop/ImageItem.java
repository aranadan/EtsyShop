package com.fox.andrey.etsyshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageItem {
    @SerializedName("listing_image_id")
    @Expose
    private Integer listingImageId;
    @SerializedName("hex_code")
    @Expose
    private String hexCode;
    @SerializedName("red")
    @Expose
    private Integer red;
    @SerializedName("green")
    @Expose
    private Integer green;
    @SerializedName("blue")
    @Expose
    private Integer blue;
    @SerializedName("hue")
    @Expose
    private Integer hue;
    @SerializedName("saturation")
    @Expose
    private Integer saturation;
    @SerializedName("brightness")
    @Expose
    private Integer brightness;
    @SerializedName("is_black_and_white")
    @Expose
    private Boolean isBlackAndWhite;
    @SerializedName("creation_tsz")
    @Expose
    private Integer creationTsz;
    @SerializedName("listing_id")
    @Expose
    private Integer listingId;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("url_75x75")
    @Expose
    private String url75x75;
    @SerializedName("url_170x135")
    @Expose
    private String url170x135;
    @SerializedName("url_570xN")
    @Expose
    private String url570xN;
    @SerializedName("url_fullxfull")
    @Expose
    private String urlFullxfull;
    @SerializedName("full_height")
    @Expose
    private Integer fullHeight;
    @SerializedName("full_width")
    @Expose
    private Integer fullWidth;

    public Integer getListingImageId() {
        return listingImageId;
    }

    public void setListingImageId(Integer listingImageId) {
        this.listingImageId = listingImageId;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public Integer getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    public Integer getHue() {
        return hue;
    }

    public void setHue(Integer hue) {
        this.hue = hue;
    }

    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Boolean getIsBlackAndWhite() {
        return isBlackAndWhite;
    }

    public void setIsBlackAndWhite(Boolean isBlackAndWhite) {
        this.isBlackAndWhite = isBlackAndWhite;
    }

    public Integer getCreationTsz() {
        return creationTsz;
    }

    public void setCreationTsz(Integer creationTsz) {
        this.creationTsz = creationTsz;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getUrl75x75() {
        return url75x75;
    }

    public void setUrl75x75(String url75x75) {
        this.url75x75 = url75x75;
    }

    public String getUrl170x135() {
        return url170x135;
    }

    public void setUrl170x135(String url170x135) {
        this.url170x135 = url170x135;
    }

    public String getUrl570xN() {
        return url570xN;
    }

    public void setUrl570xN(String url570xN) {
        this.url570xN = url570xN;
    }

    public String getUrlFullxfull() {
        return urlFullxfull;
    }

    public void setUrlFullxfull(String urlFullxfull) {
        this.urlFullxfull = urlFullxfull;
    }

    public Integer getFullHeight() {
        return fullHeight;
    }

    public void setFullHeight(Integer fullHeight) {
        this.fullHeight = fullHeight;
    }

    public Integer getFullWidth() {
        return fullWidth;
    }

    public void setFullWidth(Integer fullWidth) {
        this.fullWidth = fullWidth;
    }
}
