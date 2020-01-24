package com.example.ar_go.ApiModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuilderinfoVo {

@SerializedName("result")
@Expose
private List<BuilderResultVo> result = null;

public List<BuilderResultVo> getResult() {
return result;
}

public void setResult(List<BuilderResultVo> result) {
this.result = result;
}

}