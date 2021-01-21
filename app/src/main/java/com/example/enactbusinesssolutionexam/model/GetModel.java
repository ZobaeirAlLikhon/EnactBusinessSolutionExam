package com.example.enactbusinesssolutionexam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetModel {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("medicines")
    @Expose
    private List<Medicine> medicines = null;

    public GetModel(String code, List<Medicine> medicines) {
        this.code = code;
        this.medicines = medicines;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

}
