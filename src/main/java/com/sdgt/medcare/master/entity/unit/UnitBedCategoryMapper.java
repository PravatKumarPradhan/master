package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "adt")
public class UnitBedCategoryMapper extends BaseMaster {

    @Column(length = 50)
    private String unitCodeCatCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private OrganizationMaster organizationMaster;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bed_category_id")
    private BedCategory bedCategory;

    public UnitBedCategoryMapper() {
    }

    public UnitBedCategoryMapper(String unitCodeCatCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, BedCategory bedCategory) {
        this.unitCodeCatCode = unitCodeCatCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.bedCategory = bedCategory;
    }

    public String getUnitCodeCatCode() {
        return unitCodeCatCode;
    }

    public void setUnitCodeCatCode(String unitCodeCatCode) {
        this.unitCodeCatCode = unitCodeCatCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public BedCategory getBedCategory() {
        return bedCategory;
    }

    public void setBedCategory(BedCategory bedCategory) {
        this.bedCategory = bedCategory;
    }
}
