package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pravat Kumar Pradhan
 *
 */

    @Entity
    @Table(name="m_admission_purpose",schema = "adt")
    public class AdmissionPurpose extends BaseMaster {

    @Column(name="admission_process_code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)

    private String admissionPurposeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)

    private OrganizationMaster organizationMaster;

    public String getAdmissionPurposeCode() {

        return admissionPurposeCode;
    }

    public void setAdmissionPurposeCode(String admissionPurposeCode) {
        this.admissionPurposeCode = admissionPurposeCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public AdmissionPurpose(String admissionPurposeCode, OrganizationMaster organizationMaster) {
        this.admissionPurposeCode = admissionPurposeCode;
        this.organizationMaster = organizationMaster;
    }

    public AdmissionPurpose() {
    }
}
