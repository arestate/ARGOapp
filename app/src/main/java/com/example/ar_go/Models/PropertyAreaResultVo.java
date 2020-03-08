package com.example.ar_go.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyAreaResultVo {

@SerializedName("pa_id")
@Expose
private String paId;
@SerializedName("p_id")
@Expose
private String pId;
@SerializedName("pa_type")
@Expose
private String paType;
@SerializedName("pa_roomtype")
@Expose
private String paRoomtype;
@SerializedName("pa_image")
@Expose
private String paImage;

public String getPaId() {
return paId;
}

public void setPaId(String paId) {
this.paId = paId;
}

public String getPId() {
return pId;
}

public void setPId(String pId) {
this.pId = pId;
}

public String getPaType() {
return paType;
}

public void setPaType(String paType) {
this.paType = paType;
}

public String getPaRoomtype() {
return paRoomtype;
}

public void setPaRoomtype(String paRoomtype) {
this.paRoomtype = paRoomtype;
}

public String getPaImage() {
return paImage;
}

public void setPaImage(String paImage) {
this.paImage = paImage;
}

}
