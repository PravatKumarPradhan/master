package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_tax")
@Entity
public class TaxMaster extends BaseMaster {

    @Column(name="tax_code")
    private String taxCode;

    @Column(name="percentage",precision = 2)
    private Float percentage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public TaxMaster() {
    }
    public TaxMaster(String taxCode, Float percentage, OrganizationMaster organizationMaster) {
        this.taxCode = taxCode;
        this.percentage = percentage;
        this.organizationMaster = organizationMaster;
    }


}
