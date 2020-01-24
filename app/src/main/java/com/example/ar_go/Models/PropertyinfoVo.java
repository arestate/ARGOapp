package com.example.ar_go.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyinfoVo {

@SerializedName("result")
@Expose
private List<PropertyResultVo> result = null;

public List<PropertyResultVo> getResult() {
return result;
}

public void setResult(List<PropertyResultVo> result) {
this.result = result;
}}