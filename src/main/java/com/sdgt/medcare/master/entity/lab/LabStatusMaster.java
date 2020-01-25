package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.SubDepartmentMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_lab_status",schema = "lab")
public class LabStatusMaster extends BaseMaster {

    @Column(name = "type")
    private String type;

    @JsonIgnoreProperties("organizationMaster")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subdepartment_id")
    private SubDepartmentMaster subDepartmentMaster;

    @Column(name = "seq")
    private Integer sequence;

    @Column(name = "color_code")
    private String colorCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SubDepartmentMaster getSubDepartmentMaster() {
        return subDepartmentMaster;
    }

    public void setSubDepartmentMaster(SubDepartmentMaster subDepartmentMaster) {
        this.subDepartmentMaster = subDepartmentMaster;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
