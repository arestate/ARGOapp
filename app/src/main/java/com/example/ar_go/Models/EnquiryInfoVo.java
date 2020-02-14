package com.example.ar_go.Models;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnquiryInfoVo {
    @SerializedName("result")
    @Expose
    private List<EnquiryResultVo> result = null;

    public List<EnquiryResultVo> getResult() {
        return result;
    }

    public void setResult(List<EnquiryResultVo> result) {
        this.result = result;
    }
}
