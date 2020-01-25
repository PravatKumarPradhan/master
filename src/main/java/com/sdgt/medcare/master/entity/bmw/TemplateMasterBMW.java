package com.sdgt.medcare.master.entity.bmw;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_template_bmw", schema = "biomedical_waste")
public class TemplateMasterBMW extends BaseMaster {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationMasterBMW locationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_type_id")
    private BinTypeMaster binTypeMaster;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    public LocationMasterBMW getLocationMaster() {
        return locationMaster;
    }

    public void setLocationMaster(LocationMasterBMW locationMaster) {
        this.locationMaster = locationMaster;
    }

    public BinTypeMaster getBinTypeMaster() {
        return binTypeMaster;
    }

    public void setBinTypeMaster(BinTypeMaster binTypeMaster) {
        this.binTypeMaster = binTypeMaster;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
}
