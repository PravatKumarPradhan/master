package com.sdgt.medcare.master.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ItemListRequest {
    private String itemTypeCode;

    private String requestingStoreCode;

    private String issuingStoreCode;

    private String storeId;

    private String organizationMasterCode;

    private String unitMasterCode;

    private Boolean isConsignment;

    public String getItemTypeCode() {
        return itemTypeCode;
    }

    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    public String getRequestingStoreCode() {
        return requestingStoreCode;
    }

    public void setRequestingStoreCode(String requestingStoreCode) {
        this.requestingStoreCode = requestingStoreCode;
    }

    public String getIssuingStoreCode() {
        return issuingStoreCode;
    }

    public void setIssuingStoreCode(String issuingStoreCode) {
        this.issuingStoreCode = issuingStoreCode;
    }

    public String getOrganizationMasterCode() {
        return organizationMasterCode;
    }

    public void setOrganizationMasterCode(String organizationMasterCode) {
        this.organizationMasterCode = organizationMasterCode;
    }

    public String getUnitMasterCode() {
        return unitMasterCode;
    }

    public void setUnitMasterCode(String unitMasterCode) {
        this.unitMasterCode = unitMasterCode;
    }

    public Boolean getConsignment() {
        return isConsignment;
    }

    public void setConsignment(Boolean consignment) {
        isConsignment = consignment;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
