package com.example.ar_go.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyResultVo {
    @SerializedName("p_id")
    @Expose
    private String pId;
    @SerializedName("p_name")
    @Expose
    private String pName;
    @SerializedName("b_id")
    @Expose
    private String bId;
    @SerializedName("p_address")
    @Expose
    private String pAddress;
    @SerializedName("p_dimensions")
    @Expose
    private String pDimensions;
    @SerializedName("p_type")
    @Expose
    private String pType;
    @SerializedName("p_category")
    @Expose
    private String pCategory;
    @SerializedName("p_plan_file")
    @Expose
    private String pPlanFile;
    @SerializedName("p_external_photo")
    @Expose
    private String pDetails;
    @SerializedName("p_details")
    @Expose
    private String pExternalPhoto;
    @SerializedName("p_internal_photo")
    @Expose
    private String pInternalPhoto;
    @SerializedName("p_latitude")
    @Expose
    private String pLatitude;
    @SerializedName("p_longitude")
    @Expose
    private String pLongitude;

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getPAddress() {
        return pAddress;
    }

    public void setPAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getPDimensions() {
        return pDimensions;
    }

    public void setPDimensions(String pDimensions) {
        this.pDimensions = pDimensions;
    }

    public String getPType() {
        return pType;
    }

    public void setPType(String pType) {
        this.pType = pType;
    }

    public String getPCategory() {
        return pCategory;
    }

    public void setPCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public String getPPlanFile() {
        return pPlanFile;
    }

    public void setPPlanFile(String pPlanFile) {
        this.pPlanFile = pPlanFile;
    }

    public String getPDetails() { return pDetails;}

    public void setPDetails(String pDetails){ this.pDetails=pDetails;}

    public String getPExternalPhoto() {
        return pExternalPhoto;
    }

    public void setPExternalPhoto(String pExternalPhoto) {
        this.pExternalPhoto = pExternalPhoto;
    }

    public String getPInternalPhoto() {
        return pInternalPhoto;
    }

    public void setPInternalPhoto(String pInternalPhoto) {
        this.pInternalPhoto = pInternalPhoto;
    }

    public String getPLatitude() {
        return pLatitude;
    }

    public void setPLatitude(String pLatitude) {
        this.pLatitude = pLatitude;
    }

    public String getPLongitude() {
        return pLongitude;
    }

    public void setPLongitude(String pLongitude) {
        this.pLongitude = pLongitude;
    }
}
