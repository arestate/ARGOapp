package com.example.ar_go.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomComponentsInfoVo {

@SerializedName("result")
@Expose
private List<RoomComponentResultVo> result = null;

public List<RoomComponentResultVo> getResult() {
return result;
}

public void setResult(List<RoomComponentResultVo> result) {
this.result = result;
}

}