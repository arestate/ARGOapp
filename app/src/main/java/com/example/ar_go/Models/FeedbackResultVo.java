package com.example.ar_go.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackResultVo {

    @SerializedName("f_id")
    @Expose
    private String fId;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("b_id")
    @Expose
    private String bId;
    @SerializedName("p_id")
    @Expose
    private String pId;
    @SerializedName("f_date")
    @Expose
    private String fDate;
    @SerializedName("f_details")
    @Expose
    private String fDetails;
    @SerializedName("f_ratings")
    @Expose
    private String fRatings;

    public String getFId() {
        return fId;
    }

    public void setFId(String fId) {
        this.fId = fId;
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

    public String getFDate() {
        return fDate;
    }

    public void setFDate(String fDate) {
        this.fDate = fDate;
    }

    public String getFDetails() {
        return fDetails;
    }

    public void setFDetails(String fDetails) {
        this.fDetails = fDetails;
    }

    public String getFRatings() {
        return fRatings;
    }

    public void setFRatings(String fRatings) {
        this.fRatings = fRatings;
    }


}
