package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_patient_category")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class PatientCategoryMaster extends BaseMaster {
    @Column(name="patient_category__code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String patientCategoryCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;


    public PatientCategoryMaster() {
    }

    public PatientCategoryMaster(String patientCategoryCode, OrganizationMaster organizationMaster) {
        this.patientCategoryCode = patientCategoryCode;
        this.organizationMaster = organizationMaster;
    }

    public String getPatientCategoryCode() {
        return patientCategoryCode;
    }

    public void setPatientCategoryCode(String patientCategoryCode) {
        this.patientCategoryCode = patientCategoryCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

}
