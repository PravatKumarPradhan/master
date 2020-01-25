package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_admission_type",schema = "adt")
public class AdmissionTypeMaster extends BaseMaster {
    @Column(name="admission_type_code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String admissionTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    /*public AdmissionTypeMaster(String admissionTypeCode, OrganizationMaster organizationMaster) {
        this.admissionTypeCode = admissionTypeCode;
        this.organizationMaster = organizationMaster;
    }*/

    public String getAdmissionTypeCode() {
        return admissionTypeCode;
    }

    public void setAdmissionTypeCode(String admissionTypeCode) {
        this.admissionTypeCode = admissionTypeCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public AdmissionTypeMaster(String admissionTypeCode, OrganizationMaster organizationMaster) {
        this.admissionTypeCode = admissionTypeCode;
        this.organizationMaster = organizationMaster;
    }

    public AdmissionTypeMaster() {
    }
}
