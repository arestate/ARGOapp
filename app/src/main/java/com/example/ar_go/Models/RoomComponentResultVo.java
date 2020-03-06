package com.example.ar_go.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomComponentResultVo {

    @SerializedName("r_id")
    @Expose
    private String rId;
    @SerializedName("r_type")
    @Expose
    private String rType;
    @SerializedName("r_room")
    @Expose
    private String rRoom;
    @SerializedName("r_image")
    @Expose
    private String rImage;

    public String getRId() {
        return rId;
    }

    public void setRId(String rId) {
        this.rId = rId;
    }

    public String getRType() {
        return rType;
    }

    public void setRType(String rType) {
        this.rType = rType;
    }

    public String getRRoom() {
        return rRoom;
    }

    public void setRRoom(String rRoom) {
        this.rRoom = rRoom;
    }

    public String getRImage() {
        return rImage;
    }

    public void setRImage(String rImage) {
        this.rImage = rImage;
    }


}
