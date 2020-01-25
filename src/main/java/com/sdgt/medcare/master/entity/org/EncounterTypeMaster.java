
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

@Table(name="m_encounter_type")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class EncounterTypeMaster extends BaseMaster {


    @Column(name="encounter_type__code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String encounterTypeCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;


    @MastersFieldCustomAnnotation(displayName = "Doctor Required",sequence = 5)
    @Column(name = "is_doctor_req")
    private Boolean isDoctorReq=false;

    public EncounterTypeMaster() {
    }


    public EncounterTypeMaster(String encounterTypeCode, OrganizationMaster organizationMaster, Boolean isDoctorReq) {
        this.encounterTypeCode = encounterTypeCode;
        this.organizationMaster = organizationMaster;
        this.isDoctorReq = isDoctorReq;

    }





    public String getEncounterTypeCode() {

        return encounterTypeCode;
    }

    public void setEncounterTypeCode(String encounterTypeCode) {

        this.encounterTypeCode = encounterTypeCode;
    }

    public OrganizationMaster getOrganizationMaster()
    {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public Boolean getIsDoctorReq() {
        return isDoctorReq;
    }

    public void setIsDoctorReq(Boolean isDoctorReq) {
        this.isDoctorReq = isDoctorReq;
    }
}
