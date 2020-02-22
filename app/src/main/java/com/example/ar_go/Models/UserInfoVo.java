package com.example.ar_go.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoVo {

@SerializedName("u_id")
@Expose
private String uId;
@SerializedName("u_name")
@Expose
private String uName;
@SerializedName("u_email")
@Expose
private String uEmail;
@SerializedName("u_contactno")
@Expose
private String uContactno;
@SerializedName("u_address")
@Expose
private String uAddress;
@SerializedName("u_password")
@Expose
private String uPassword;

public String getUId() {
return uId;
}

public void setUId(String uId) {
this.uId = uId;
}

public String getUName() {
return uName;
}

public void setUName(String uName) {
this.uName = uName;
}

public String getUEmail() {
return uEmail;
}

public void setUEmail(String uEmail) {
this.uEmail = uEmail;
}

public String getUContactno() {
return uContactno;
}

public void setUContactno(String uContactno) {
this.uContactno = uContactno;
}

public String getUAddress() {
return uAddress;
}

public void setUAddress(String uAddress) {
this.uAddress = uAddress;
}

public String getUPassword() {
return uPassword;
}

public void setUPassword(String uPassword) {
this.uPassword = uPassword;
}

}
