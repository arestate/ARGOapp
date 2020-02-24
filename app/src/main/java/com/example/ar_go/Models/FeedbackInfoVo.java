package com.example.ar_go.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackInfoVo {

@SerializedName("result")
@Expose
private List<FeedbackResultVo> result = null;

public List<FeedbackResultVo> getResult() {
return result;
}

public void setResult(List<FeedbackResultVo> result) {
this.result = result;
}

}
