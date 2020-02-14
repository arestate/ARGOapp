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


}
