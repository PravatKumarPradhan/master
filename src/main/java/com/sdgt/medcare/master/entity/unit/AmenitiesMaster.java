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

@Table(name="m_amenities",schema = "adt")
@Entity
public class AmenitiesMaster extends BaseMaster {
    @Column(name="amenities_code",length = 50)
    private String  amenitiesCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    public AmenitiesMaster() {
    }

    public AmenitiesMaster(String amenitiesCode, OrganizationMaster organizationMaster, UnitMaster unitMaster) {
        this.amenitiesCode = amenitiesCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
    }

    public String getAmenitiesCode() {
        return amenitiesCode;
    }

    public void setAmenitiesCode(String amenitiesCode) {
        this.amenitiesCode = amenitiesCode;
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
