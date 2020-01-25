package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="m_floor")
public class FloorMaster  extends BaseMaster {
    @Column(name="floor_master_code",unique = true,length = 50)
    private String floorMasterCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="block_id")
    private BlockMaster blockMaster;

    @JsonBackReference
    @OneToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = FloorMaster.class)
    private Collection<WardMaster> wardMasters;

   /* public FloorMaster(String floorMasterCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, BlockMaster blockMaster) {
        this.floorMasterCode = floorMasterCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.blockMaster = blockMaster;
    }*/

    public String getFloorMasterCode() {
        return floorMasterCode;
    }

    public void setFloorMasterCode(String floorMasterCode) {
        this.floorMasterCode = floorMasterCode;
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

    public BlockMaster getBlockMaster() {
        return blockMaster;
    }

    public void setBlockMaster(BlockMaster blockMaster) {
        this.blockMaster = blockMaster;
    }

    public Collection<WardMaster> getWardMasters() {
        return wardMasters;
    }

    public void setWardMasters(Collection<WardMaster> wardMasters) {
        this.wardMasters = wardMasters;
    }

    public FloorMaster() {
    }
}
