package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "m_tray", schema = "inventory")
@SequenceGenerator(name = "tray_seq", sequenceName = "inventory.tray_seq", allocationSize = 1)
@DynamicUpdate
public class TrayMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    @MastersFieldCustomAnnotation(displayName = "is_allocated",sequence = 222)
    @NotNull
    private Boolean isAllocated=false;


    public TrayMaster() {
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


    public Boolean getAllocated() {
        return isAllocated;
    }

    public void setAllocated(Boolean allocated) {
        isAllocated = allocated;
    }
}
