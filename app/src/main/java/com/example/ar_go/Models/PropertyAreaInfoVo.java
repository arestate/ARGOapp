package com.example.ar_go.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyAreaInfoVo {

@SerializedName("result")
@Expose
private List<PropertyAreaResultVo> result = null;

public List<PropertyAreaResultVo> getResult() {
return result;
}

public void setResult(List<PropertyAreaResultVo> result) {
this.result = result;
}

}