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

@Table(name="m_block")
@Entity
public class BlockMaster extends BaseMaster {

    @Column(name="block_master_code",unique = true,length = 50)
    private String blockMasterCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    public BlockMaster(String blockMasterCode, OrganizationMaster organizationMaster, UnitMaster unitMaster) {
        this.blockMasterCode = blockMasterCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
    }

    public String getBlockMasterCode() {
        return blockMasterCode;
    }

    public void setBlockMasterCode(String blockMasterCode) {
        this.blockMasterCode = blockMasterCode;
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

    public BlockMaster() {
    }

}
