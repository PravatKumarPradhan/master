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

@Table(name="m_lodger")
@Entity
public class LodgerServiceMaster extends BaseMaster {

    @Column(name="lodge_service_code",unique = true,length = 50)
    private String lodgeServiceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    public LodgerServiceMaster(String lodgeServiceCode, OrganizationMaster organizationMaster, UnitMaster unitMaster) {
        this.lodgeServiceCode = lodgeServiceCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
    }

    public String getLodgeServiceCode() {
        return lodgeServiceCode;
    }

    public void setLodgeServiceCode(String lodgeServiceCode) {
        this.lodgeServiceCode = lodgeServiceCode;
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

    public LodgerServiceMaster() {
    }
}
