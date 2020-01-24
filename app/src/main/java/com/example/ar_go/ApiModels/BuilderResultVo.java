package com.example.ar_go.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuilderResultVo {
    @SerializedName("b_id")
    @Expose
    private String bId;
    @SerializedName("b_licenseno")
    @Expose
    private String bLicenseno;
    @SerializedName("b_name")
    @Expose
    private String bName;
    @SerializedName("b_email")
    @Expose
    private String bEmail;
    @SerializedName("b_contactno")
    @Expose
    private String bContactno;
    @SerializedName("b_address")
    @Expose
    private String bAddress;
    @SerializedName("b_password")
    @Expose
    private String bPassword;
    @SerializedName("b_website")
    @Expose
    private String bWebsite;
    @SerializedName("b_logo")
    @Expose
    private String bLogo;
    @SerializedName("b_status")
    @Expose
    private String bStatus;

    public String getBId() {
        return bId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public String getBLicenseno() {
        return bLicenseno;
    }

    public void setBLicenseno(String bLicenseno) {
        this.bLicenseno = bLicenseno;
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public String getBEmail() {
        return bEmail;
    }

    public void setBEmail(String bEmail) {
        this.bEmail = bEmail;
    }

    public String getBContactno() {
        return bContactno;
    }

    public void setBContactno(String bContactno) {
        this.bContactno = bContactno;
    }

    public String getBAddress() {
        return bAddress;
    }

    public void setBAddress(String bAddress) {
        this.bAddress = bAddress;
    }

    public String getBPassword() {
        return bPassword;
    }

    public void setBPassword(String bPassword) {
        this.bPassword = bPassword;
    }

    public String getBWebsite() {
        return bWebsite;
    }

    public void setBWebsite(String bWebsite) {
        this.bWebsite = bWebsite;
    }

    public String getBLogo() {
        return bLogo;
    }

    public void setBLogo(String bLogo) {
        this.bLogo = bLogo;
    }

    public String getBStatus() {
        return bStatus;
    }

    public void setBStatus(String bStatus) {
        this.bStatus = bStatus;
    }

}
