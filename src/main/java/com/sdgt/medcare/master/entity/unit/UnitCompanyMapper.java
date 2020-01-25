package com.sdgt.medcare.master.entity.unit;


import com.sdgt.medcare.master.entity.AbstractBaseEntity;
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

@Table(name="unit_company_mapper")
@Entity
public class UnitCompanyMapper extends AbstractBaseEntity {

    @Column(name="unit_company_mapper_code")
    private String unitCompanyMapperCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="company_master_id")
    private CompanyMaster companyMaster;

    public UnitCompanyMapper() {
    }

    public UnitCompanyMapper(String unitCompanyMapperCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, CompanyMaster companyMaster) {
        this.unitCompanyMapperCode = unitCompanyMapperCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.companyMaster = companyMaster;
    }

    public String getUnitCompanyMapperCode() {
        return unitCompanyMapperCode;
    }

    public void setUnitCompanyMapperCode(String unitCompanyMapperCode) {
        this.unitCompanyMapperCode = unitCompanyMapperCode;
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

}
