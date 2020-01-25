package com.sdgt.medcare.master.entity.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "m_service", schema = "service")
public class ServiceMaster extends BaseMaster {

    @Column(name = "service_codification_code",length = 50)
    @MastersFieldCustomAnnotation(displayName = "Codification Code",sequence = 3)
    private String serviceCodificationCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    @JsonIgnoreProperties("organizationMaster")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id")
    @MastersFieldCustomAnnotation(displayName = "Group",sequence = 4)
    private GroupMaster groupMaster;

    @JsonIgnoreProperties({"groupMaster","organizationMaster"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sub_group_id")
    @MastersFieldCustomAnnotation(displayName = "Sub Group",sequence = 5)
    private SubGroupMaster subGroupMaster;

    @Column(name = "validity_from")
    @MastersFieldCustomAnnotation(displayName = "Validity From",sequence = 6)
    private Date validityFrom;

    @Column(name = "validity_to")
    @MastersFieldCustomAnnotation(displayName = "Validity To",sequence = 7)
    private Date validityTo;

    @Column(name = "is_doctorshare")
    @MastersFieldCustomAnnotation(displayName = "Is Doctor Share",sequence = 8)
    private Boolean isDoctorShare = false;


    public String getServiceCodificationCode() {
        return serviceCodificationCode;
    }

    public void setServiceCodificationCode(String serviceCodificationCode) {
        this.serviceCodificationCode = serviceCodificationCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public GroupMaster getGroupMaster() {
        return groupMaster;
    }

    public void setGroupMaster(GroupMaster groupMaster) {
        this.groupMaster = groupMaster;
    }

    public SubGroupMaster getSubGroupMaster() {
        return subGroupMaster;
    }

    public void setSubGroupMaster(SubGroupMaster subGroupMaster) {
        this.subGroupMaster = subGroupMaster;
    }

    public Date getValidityFrom() {
        return validityFrom;
    }

    public void setValidityFrom(Date validityFrom) {
        this.validityFrom = validityFrom;
    }

    public Date getValidityTo() {
        return validityTo;
    }

    public void setValidityTo(Date validityTo) {
        this.validityTo = validityTo;
    }

    public Boolean isIsdoctorshare() {
        return isDoctorShare;
    }

    public void setIsDoctorShare(Boolean isDoctorShare) {
        this.isDoctorShare = isDoctorShare;
    }
}

