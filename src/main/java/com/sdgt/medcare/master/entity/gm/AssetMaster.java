package com.sdgt.medcare.master.entity.gm;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.StoreMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_asset", schema = "gm")
public class AssetMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="store_master_id")
    private StoreMaster storeMaster;

    private String serialNo;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public StoreMaster getStoreMaster() {
        return storeMaster;
    }

    public void setStoreMaster(StoreMaster storeMaster) {
        this.storeMaster = storeMaster;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}

