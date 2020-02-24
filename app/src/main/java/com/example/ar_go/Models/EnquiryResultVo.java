package com.example.ar_go.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnquiryResultVo {
    @SerializedName("e_id")
    @Expose
    private String eId;
    @SerializedName("e_date")
    @Expose
    private String eDate;
    @SerializedName("e_details")
    @Expose
    private String eDetails;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("b_id")
    @Expose
    private String bId;
    @SerializedName("p_id")
    @Expose
    private String pId;
    @SerializedName("p_name")
    @Expose
    private String pName;
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
    @SerializedName("p_details")
    @Expose
    private String pDetails;
    @SerializedName("p_amenities")
    @Expose
    private String pAmenities;
    @SerializedName("p_external_photo")
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

    public String getEId() {
        return eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
    }

    public String getEDate() {
        return eDate;
    }

    public void setEDate(String eDate) {
        this.eDate = eDate;
    }

    public String getEDetails() {
        return eDetails;
    }

    public void setEDetails(String eDetails) {
        this.eDetails = eDetails;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

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

    public String getPDetails() {
        return pDetails;
    }

    public void setPDetails(String pDetails) {
        this.pDetails = pDetails;
    }

    public String getPAmenities() {
        return pAmenities;
    }

    public void setPAmenities(String pAmenities) {
        this.pAmenities = pAmenities;
    }

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
