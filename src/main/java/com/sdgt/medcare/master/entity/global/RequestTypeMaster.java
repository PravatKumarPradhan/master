package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_request_type", schema = "inventory")
public class RequestTypeMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="org_id")
    private OrganizationMaster organizationMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }
}