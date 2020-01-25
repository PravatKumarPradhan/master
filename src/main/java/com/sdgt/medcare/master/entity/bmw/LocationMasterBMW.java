package com.sdgt.medcare.master.entity.bmw;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_location_bmw", schema = "biomedical_waste")
public class LocationMasterBMW extends BaseMaster {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_type_id")
    private LocationTypeMaster locationTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    public LocationMasterBMW() {
    }
    public LocationMasterBMW(LocationTypeMaster locationTypeMaster) {
        this.locationTypeMaster = locationTypeMaster;
    }

    public LocationTypeMaster getLocationTypeMaster() {
        return locationTypeMaster;
    }

    public void setLocationTypeMaster(LocationTypeMaster locationTypeMaster) {
        this.locationTypeMaster = locationTypeMaster;
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
