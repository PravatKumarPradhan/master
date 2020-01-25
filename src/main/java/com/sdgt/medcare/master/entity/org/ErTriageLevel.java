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
@Table(name="m_er_triage_level",schema = "adt")
public class ErTriageLevel extends BaseMaster {
    @Column(name="triage_level_code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String triageLevelCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    public ErTriageLevel(String triageLevelCode, OrganizationMaster organizationMaster) {
        this.triageLevelCode = triageLevelCode;
        this.organizationMaster = organizationMaster;
    }

    public String getTriageLevelCode() {
        return triageLevelCode;
    }

    public void setTriageLevelCode(String triageLevelCode) {
        this.triageLevelCode = triageLevelCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ErTriageLevel() {
    }
}
