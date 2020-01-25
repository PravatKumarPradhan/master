package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_company_type")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class CompanyTypeMaster extends BaseMaster {

    @Column(name = "company_type__code", unique = true, length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String companyTypeCode;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "organization_id")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private OrganizationMaster organizationMaster;

   @MastersFieldCustomAnnotation(sequence = 4,nullable = false)
    @Column(name = "is_self_company")
    private Boolean isSelfCompany;

    public String getCompanyTypeCode() {
        return companyTypeCode;
    }

    public void setCompanyTypeCode(String groupCode) {
        this.companyTypeCode = companyTypeCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }
      public Boolean getIsSelfCompany(){
        return isSelfCompany;
      }
      public void setIsSelfCompany(Boolean isSelfCompany){
        this.isSelfCompany=isSelfCompany;
      }

}
