package com.sdgt.medcare.master.model;

public class ItemUomResponse {

    private String itemId;

    private String itemCode;

    private String itemName;

    private String uomTypeId;

    private String uomTypeCode;

    private String uomType;

    private String uomUnitId;

    private String uomUnitCode;

    private String uomUnit;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUomTypeCode() {
        return uomTypeCode;
    }

    public void setUomTypeCode(String uomTypeCode) {
        this.uomTypeCode = uomTypeCode;
    }

    public String getUomType() {
        return uomType;
    }

    public void setUomType(String uomType) {
        this.uomType = uomType;
    }

    public String getUomUnitCode() {
        return uomUnitCode;
    }

    public void setUomUnitCode(String uomUnitCode) {
        this.uomUnitCode = uomUnitCode;
    }

    public String getUomUnit() {
        return uomUnit;
    }

    public void setUomUnit(String uomUnit) {
        this.uomUnit = uomUnit;
    }

    public ItemUomResponse(String itemId, String itemCode, String itemName, String uomTypeId, String uomTypeCode, String uomType, String uomUnitId, String uomUnitCode, String uomUnit) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.uomTypeId = uomTypeId;
        this.uomTypeCode = uomTypeCode;
        this.uomType = uomType;
        this.uomUnitId = uomUnitId;
        this.uomUnitCode = uomUnitCode;
        this.uomUnit = uomUnit;
    }

}
