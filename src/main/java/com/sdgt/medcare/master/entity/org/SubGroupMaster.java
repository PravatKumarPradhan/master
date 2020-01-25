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
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
@Table(name="m_sub_group")
public class SubGroupMaster extends BaseMaster {

    @Column(name="sub_group__code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private String sunGroupCode;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="group_id")
    @MastersFieldCustomAnnotation(displayName = "Group",sequence = 3,nullable = false)
    private GroupMaster groupMaster;
    public SubGroupMaster() {
    }

    public String getSunGroupCode() {
        return sunGroupCode;
    }

    public void setSunGroupCode(String sunGroupCode) {
        this.sunGroupCode = sunGroupCode;
    }

    public GroupMaster getGroupMaster() {
        return groupMaster;
    }

    public void setGroupMaster(GroupMaster groupMaster) {
        this.groupMaster = groupMaster;
    }

    public SubGroupMaster(String sunGroupCode, OrganizationMaster organizationMaster, GroupMaster groupMaster) {
        this.sunGroupCode = sunGroupCode;
        this.organizationMaster = organizationMaster;
        this.groupMaster = groupMaster;
    }

    public String getGroupCode() {
        return sunGroupCode;
    }

    public void setGroupCode(String sunGroupCode) {
        this.sunGroupCode = sunGroupCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

}
