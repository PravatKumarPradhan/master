package com.sdgt.medcare.master.model;

public class ItemResponse {

    private Long id;
    private String itemCode;
    private String itemName;
    private String itemGroup;
    private String itemCategory;
    private Integer minQty;
    private Integer maxQty;
    private Integer reorderQty;
    private Integer reorderLevel;
    private String storeCode;
    private String storeName;
    private String unitDesc;
    private String unitCode;
    private String itemGroupDesc;

    public ItemResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getMinQty() {
        return minQty;
    }

    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }

    public Integer getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(Integer maxQty) {
        this.maxQty = maxQty;
    }

    public Integer getReorderQty() {
        return reorderQty;
    }

    public void setReorderQty(Integer reorderQty) {
        this.reorderQty = reorderQty;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getItemGroupDesc() {
        return itemGroupDesc;
    }

    public void setItemGroupDesc(String itemGroupDesc) {
        this.itemGroupDesc = itemGroupDesc;
    }

    public ItemResponse(Long id, String itemCode, String itemName, String itemGroup, String itemCategory, Integer minQty, Integer maxQty, Integer reorderQty, Integer reorderLevel, String storeCode, String storeName, String unitDesc, String unitCode) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemGroup = itemGroup;
        this.itemCategory = itemCategory;
        this.minQty = minQty;
        this.maxQty = maxQty;
        this.reorderQty = reorderQty;
        this.reorderLevel = reorderLevel;
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.unitDesc = unitDesc;
        this.unitCode = unitCode;
    }

    public ItemResponse(Long id, String itemCode, String itemName, String itemGroup, String itemGroupDesc) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemGroup = itemGroup;
        this.itemGroupDesc = itemGroupDesc;
    }

    public ItemResponse(Long id, String itemCode, String itemName, String itemGroup, String itemCategory, Integer minQty, Integer maxQty, Integer reorderQty, Integer reorderLevel) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemGroup = itemGroup;
        this.itemCategory = itemCategory;
        this.minQty = minQty;
        this.maxQty = maxQty;
        this.reorderQty = reorderQty;
        this.reorderLevel = reorderLevel;
    }
}
