package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.StoreMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="m_store_generic_mapper", schema = "inventory")
@SequenceGenerator(name = "store_generic_mapper_seq", sequenceName = "inventory.store_generic_mapper", allocationSize = 1)
@DynamicUpdate
public class StoreGenericMapperMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generic_id")
    private GenericMaster genericMaster;

    @Column(name = "is_vital")
    private Boolean vital = false;

    @Column(name = "is_essential")
    private Boolean essential = false;

    @Column(name = "is_desirable")
    private Boolean desirable = false;

    public StoreGenericMapperMaster() {
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

    public StoreMaster getStoreMaster() {
        return storeMaster;
    }

    public void setStoreMaster(StoreMaster storeMaster) {
        this.storeMaster = storeMaster;
    }

    public GenericMaster getGenericMaster() {
        return genericMaster;
    }

    public void setGenericMaster(GenericMaster genericMaster) {
        this.genericMaster = genericMaster;
    }

    public Boolean getVital() {
        return vital;
    }

    public void setVital(Boolean vital) {
        this.vital = vital;
    }

    public Boolean getEssential() {
        return essential;
    }

    public void setEssential(Boolean essential) {
        this.essential = essential;
    }

    public Boolean getDesirable() {
        return desirable;
    }

    public void setDesirable(Boolean desirable) {
        this.desirable = desirable;
    }
}
