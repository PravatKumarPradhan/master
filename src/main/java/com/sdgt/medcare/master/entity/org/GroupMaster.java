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
@Table(name="m_group")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class GroupMaster extends BaseMaster {


    @Column(name="group__code",length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String groupCode;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;


    public GroupMaster() {
    }

    public GroupMaster(String groupCode, OrganizationMaster organizationMaster) {
        this.groupCode = groupCode;
        this.organizationMaster = organizationMaster;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }


}
