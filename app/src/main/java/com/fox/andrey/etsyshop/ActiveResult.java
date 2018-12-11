package com.fox.andrey.etsyshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ActiveResult implements Serializable {
    @SerializedName("listing_id")
    @Expose
    private Integer listingId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("creation_tsz")
    @Expose
    private Integer creationTsz;
    @SerializedName("ending_tsz")
    @Expose
    private Integer endingTsz;
    @SerializedName("original_creation_tsz")
    @Expose
    private Integer originalCreationTsz;
    @SerializedName("last_modified_tsz")
    @Expose
    private Integer lastModifiedTsz;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("sku")
    @Expose
    private List<Object> sku = null;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("category_path")
    @Expose
    private List<String> categoryPath = null;
    @SerializedName("category_path_ids")
    @Expose
    private List<Integer> categoryPathIds = null;
    @SerializedName("materials")
    @Expose
    private List<String> materials = null;
    @SerializedName("shop_section_id")
    @Expose
    private Integer shopSectionId;
    @SerializedName("featured_rank")
    @Expose
    private Object featuredRank;
    @SerializedName("state_tsz")
    @Expose
    private Integer stateTsz;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("num_favorers")
    @Expose
    private Integer numFavorers;
    @SerializedName("processing_min")
    @Expose
    private Integer processingMin;
    @SerializedName("processing_max")
    @Expose
    private Integer processingMax;
    @SerializedName("who_made")
    @Expose
    private String whoMade;
    @SerializedName("is_supply")
    @Expose
    private String isSupply;
    @SerializedName("when_made")
    @Expose
    private String whenMade;
    @SerializedName("item_weight")
    @Expose
    private Object itemWeight;
    @SerializedName("item_weight_unit")
    @Expose
    private String itemWeightUnit;
    @SerializedName("item_length")
    @Expose
    private Object itemLength;
    @SerializedName("item_width")
    @Expose
    private Object itemWidth;
    @SerializedName("item_height")
    @Expose
    private Object itemHeight;
    @SerializedName("item_dimensions_unit")
    @Expose
    private String itemDimensionsUnit;
    @SerializedName("is_private")
    @Expose
    private Boolean isPrivate;
    @SerializedName("recipient")
    @Expose
    private Object recipient;
    @SerializedName("occasion")
    @Expose
    private Object occasion;
    @SerializedName("style")
    @Expose
    private Object style;
    @SerializedName("non_taxable")
    @Expose
    private Boolean nonTaxable;
    @SerializedName("is_customizable")
    @Expose
    private Boolean isCustomizable;
    @SerializedName("is_digital")
    @Expose
    private Boolean isDigital;
    @SerializedName("file_data")
    @Expose
    private String fileData;
    @SerializedName("should_auto_renew")
    @Expose
    private Boolean shouldAutoRenew;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("has_variations")
    @Expose
    private Boolean hasVariations;
    @SerializedName("taxonomy_id")
    @Expose
    private Integer taxonomyId;
    @SerializedName("taxonomy_path")
    @Expose
    private List<String> taxonomyPath = null;
    @SerializedName("used_manufacturer")
    @Expose
    private Boolean usedManufacturer;

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreationTsz() {
        return creationTsz;
    }

    public void setCreationTsz(Integer creationTsz) {
        this.creationTsz = creationTsz;
    }

    public Integer getEndingTsz() {
        return endingTsz;
    }

    public void setEndingTsz(Integer endingTsz) {
        this.endingTsz = endingTsz;
    }

    public Integer getOriginalCreationTsz() {
        return originalCreationTsz;
    }

    public void setOriginalCreationTsz(Integer originalCreationTsz) {
        this.originalCreationTsz = originalCreationTsz;
    }

    public Integer getLastModifiedTsz() {
        return lastModifiedTsz;
    }

    public void setLastModifiedTsz(Integer lastModifiedTsz) {
        this.lastModifiedTsz = lastModifiedTsz;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Object> getSku() {
        return sku;
    }

    public void setSku(List<Object> sku) {
        this.sku = sku;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public List<Integer> getCategoryPathIds() {
        return categoryPathIds;
    }

    public void setCategoryPathIds(List<Integer> categoryPathIds) {
        this.categoryPathIds = categoryPathIds;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public Integer getShopSectionId() {
        return shopSectionId;
    }

    public void setShopSectionId(Integer shopSectionId) {
        this.shopSectionId = shopSectionId;
    }

    public Object getFeaturedRank() {
        return featuredRank;
    }

    public void setFeaturedRank(Object featuredRank) {
        this.featuredRank = featuredRank;
    }

    public Integer getStateTsz() {
        return stateTsz;
    }

    public void setStateTsz(Integer stateTsz) {
        this.stateTsz = stateTsz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getNumFavorers() {
        return numFavorers;
    }

    public void setNumFavorers(Integer numFavorers) {
        this.numFavorers = numFavorers;
    }

    public Integer getProcessingMin() {
        return processingMin;
    }

    public void setProcessingMin(Integer processingMin) {
        this.processingMin = processingMin;
    }

    public Integer getProcessingMax() {
        return processingMax;
    }

    public void setProcessingMax(Integer processingMax) {
        this.processingMax = processingMax;
    }

    public String getWhoMade() {
        return whoMade;
    }

    public void setWhoMade(String whoMade) {
        this.whoMade = whoMade;
    }

    public String getIsSupply() {
        return isSupply;
    }

    public void setIsSupply(String isSupply) {
        this.isSupply = isSupply;
    }

    public String getWhenMade() {
        return whenMade;
    }

    public void setWhenMade(String whenMade) {
        this.whenMade = whenMade;
    }

    public Object getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Object itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemWeightUnit() {
        return itemWeightUnit;
    }

    public void setItemWeightUnit(String itemWeightUnit) {
        this.itemWeightUnit = itemWeightUnit;
    }

    public Object getItemLength() {
        return itemLength;
    }

    public void setItemLength(Object itemLength) {
        this.itemLength = itemLength;
    }

    public Object getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(Object itemWidth) {
        this.itemWidth = itemWidth;
    }

    public Object getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(Object itemHeight) {
        this.itemHeight = itemHeight;
    }

    public String getItemDimensionsUnit() {
        return itemDimensionsUnit;
    }

    public void setItemDimensionsUnit(String itemDimensionsUnit) {
        this.itemDimensionsUnit = itemDimensionsUnit;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Object getRecipient() {
        return recipient;
    }

    public void setRecipient(Object recipient) {
        this.recipient = recipient;
    }

    public Object getOccasion() {
        return occasion;
    }

    public void setOccasion(Object occasion) {
        this.occasion = occasion;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public Boolean getNonTaxable() {
        return nonTaxable;
    }

    public void setNonTaxable(Boolean nonTaxable) {
        this.nonTaxable = nonTaxable;
    }

    public Boolean getIsCustomizable() {
        return isCustomizable;
    }

    public void setIsCustomizable(Boolean isCustomizable) {
        this.isCustomizable = isCustomizable;
    }

    public Boolean getIsDigital() {
        return isDigital;
    }

    public void setIsDigital(Boolean isDigital) {
        this.isDigital = isDigital;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public Boolean getShouldAutoRenew() {
        return shouldAutoRenew;
    }

    public void setShouldAutoRenew(Boolean shouldAutoRenew) {
        this.shouldAutoRenew = shouldAutoRenew;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getHasVariations() {
        return hasVariations;
    }

    public void setHasVariations(Boolean hasVariations) {
        this.hasVariations = hasVariations;
    }

    public Integer getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(Integer taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public List<String> getTaxonomyPath() {
        return taxonomyPath;
    }

    public void setTaxonomyPath(List<String> taxonomyPath) {
        this.taxonomyPath = taxonomyPath;
    }

    public Boolean getUsedManufacturer() {
        return usedManufacturer;
    }

    public void setUsedManufacturer(Boolean usedManufacturer) {
        this.usedManufacturer = usedManufacturer;
    }
}
