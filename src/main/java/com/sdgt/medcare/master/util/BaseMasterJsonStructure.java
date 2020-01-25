package com.sdgt.medcare.master.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({ "masterConfiguration"})
public class BaseMasterJsonStructure {


    @JsonProperty("config")
    private Object masterConfiguration;

    @JsonProperty("rows")
    private List<Object> rowList;


    public List<Object> getRowList() {
        return rowList;
    }
    public void setRowList(List<Object> rowList) {
        this.rowList = rowList;
    }
    public Object getMasterConfiguration() {
        return masterConfiguration;
    }
    public void setMasterConfiguration(Object masterConfiguration) {
        this.masterConfiguration = masterConfiguration;
    }
}
