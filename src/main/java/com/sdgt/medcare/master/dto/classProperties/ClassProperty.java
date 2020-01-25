package com.sdgt.medcare.master.dto.classProperties;

public class ClassProperty {

    private String name;

    private String type;

    private String className;

    private Boolean showToUser=true;

    private Boolean editable=true;

    private String displayName;

    private Integer sequence;

    private Boolean nullable=true;

    private String validationRagex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getShowToUser() {
        return showToUser;
    }

    public void setShowToUser(Boolean showToUser) {
        this.showToUser = showToUser;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public String getValidationRagex() {
        return validationRagex;
    }

    public void setValidationRagex(String validationRagex) {
        this.validationRagex = validationRagex;
    }
}
