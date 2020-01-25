package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "m_consumption_type", schema = "inventory")
@SequenceGenerator(name = "consumption_type_seq", sequenceName = "inventory.consumption_type_seq", allocationSize = 1)
@DynamicUpdate
public class ConsumptionTypeMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationmaster;

    public ConsumptionTypeMaster() {
    }

    public OrganizationMaster getOrganizationmaster() {
        return organizationmaster;
    }

    public void setOrganizationmaster(OrganizationMaster organizationmaster) {
        this.organizationmaster = organizationmaster;
    }


}
