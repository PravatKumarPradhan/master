package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_er_visit_reason",schema = "adt")
@Entity
public class ErVisitReason extends BaseMaster {
    @Column(name="er_visit_reason_code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String erVisitReasonCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    public ErVisitReason(String erVisitReasonCode, OrganizationMaster organizationMaster) {
        this.erVisitReasonCode = erVisitReasonCode;
        this.organizationMaster = organizationMaster;
    }

    public String getErVisitReasonCode() {
        return erVisitReasonCode;
    }

    public void setErVisitReasonCode(String erVisitReasonCode) {
        this.erVisitReasonCode = erVisitReasonCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ErVisitReason() {
    }

}
