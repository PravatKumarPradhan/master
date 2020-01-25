package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_tax_component")
public class TaxComponent  extends BaseMaster {
    @Column(name="tax_component__code",unique = true,length = 50)
    private String taxComponentCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;


    public TaxComponent() {
    }

    public TaxComponent(String taxComponentCode, OrganizationMaster organizationMaster) {
        this.taxComponentCode = taxComponentCode;
        this.organizationMaster = organizationMaster;
    }

    public String getTaxComponentCode() {
        return taxComponentCode;
    }

    public void setTaxComponentCode(String sunGroupCode) {
        this.taxComponentCode = sunGroupCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

}

