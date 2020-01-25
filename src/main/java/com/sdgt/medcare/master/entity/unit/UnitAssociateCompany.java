package com.sdgt.medcare.master.entity.unit;


import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.AssociatedCompanyMaster;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="unit_associated_company_mapper")
public class UnitAssociateCompany  extends BaseMaster {

    @Column(name="unit_associate_company_mapper_code")
    private String unitAssociateCompanyMapperCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="company_master_id")
    private CompanyMaster companyMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="associate_company_master_id")
    private AssociatedCompanyMaster associatedCompanyMaster;

    public UnitAssociateCompany() {
    }

    public UnitAssociateCompany(String unitAssociateCompanyMapperCode, OrganizationMaster organizationMaster,UnitMaster  unitMaster,CompanyMaster  companyMaster,AssociatedCompanyMaster  associatedCompanyMaster) {
        this.unitAssociateCompanyMapperCode = unitAssociateCompanyMapperCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.companyMaster = companyMaster;
        this.associatedCompanyMaster = associatedCompanyMaster;
    }

    public String getUnitAssociateCompanyMapperCode() {
        return unitAssociateCompanyMapperCode;
    }

    public void setUnitAssociateCompanyMapperCode(String unitAssociateCompanyMapperCode) {
        this.unitAssociateCompanyMapperCode = unitAssociateCompanyMapperCode;
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

    public CompanyMaster getCompanyMaster() {
        return companyMaster;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public AssociatedCompanyMaster  getAssociatedCompanyMaster() {
        return associatedCompanyMaster;
    }

    public void setAssociatedCompanyMaster(AssociatedCompanyMaster associatedCompanyMaster) {
        this.associatedCompanyMaster = associatedCompanyMaster;
    }


}
