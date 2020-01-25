package com.sdgt.medcare.master.dto.common;

import java.util.List;

public class StockTakeItemRequest {

    private List<String> itemGroupCodes;

    private String storeCode;

    private Boolean isVital;

    private Boolean isEssential;

    private Boolean isDesirable;

    public List<String> getItemGroupCodes() {
        return itemGroupCodes;
    }

    public void setItemGroupCodes(List<String> itemGroupCodes) {
        this.itemGroupCodes = itemGroupCodes;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public Boolean getIsVital() {
        return isVital;
    }

    public void setIsVital(Boolean vital) {
        isVital = vital;
    }

    public Boolean getIsEssential() {
        return isEssential;
    }

    public void setIsEssential(Boolean essential) {
        isEssential = essential;
    }

    public Boolean getIsDesirable() {
        return isDesirable;
    }

    public void setIsDesirable(Boolean desirable) {
        isDesirable = desirable;
    }
}
