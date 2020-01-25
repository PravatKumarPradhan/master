package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_tax_details")
@Entity
public class TaxDetailsMaster extends BaseMaster {

    @Column(name="tax_details_code")
    private String taxDetailsCode;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="tax_id")
    private TaxMaster taxMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    public TaxDetailsMaster(String taxDetailsCode, TaxMaster taxMaster, OrganizationMaster organizationMaster) {
        this.taxDetailsCode = taxDetailsCode;
        this.taxMaster = taxMaster;
        this.organizationMaster = organizationMaster;
    }

    public String getTaxDetailsCode() {
        return taxDetailsCode;
    }

    public void setTaxDetailsCode(String taxDetailsCode) {
        this.taxDetailsCode = taxDetailsCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public TaxDetailsMaster() {
    }

}
