package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_storage_unit", schema = "inventory")
@SequenceGenerator(name = "storage_unit_seq", sequenceName = "inventory.storage_unit_seq", allocationSize = 1)
@DynamicUpdate
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class StorageUnitMaster extends BaseMaster {

    @MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    public StorageUnitMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }
}
