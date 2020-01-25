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
@Table(name="m_nursing_station",schema = "adt")
public class NursingStationMaster extends BaseMaster {
    @Column(name="nursing_station_code",unique = true,length = 50)
    private String wardMasterCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    public NursingStationMaster() {
    }

    public NursingStationMaster(String wardMasterCode, OrganizationMaster organizationMaster, UnitMaster unitMaster) {
        this.wardMasterCode = wardMasterCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
    }

    public String getWardMasterCode() {
        return wardMasterCode;
    }

    public void setWardMasterCode(String wardMasterCode) {
        this.wardMasterCode = wardMasterCode;
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
