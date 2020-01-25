package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_cost_center")
public class CostCenterMaster extends BaseMaster {

    @Column(name = "cost_center_code", unique = true, length = 50)
    private String costCenterCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private OrganizationMaster organizationMaster;


    public CostCenterMaster() {
    }

    public CostCenterMaster(String costCenterCode, OrganizationMaster organizationMaster) {
        this.costCenterCode = costCenterCode;
        this.organizationMaster = organizationMaster;
    }

    public String getCostCenterCode() {
        return costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;

    }
}
