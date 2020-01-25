package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_priority")
@DynamicUpdate
public class PriorityMaster extends BaseMaster {

    @Column(name="priority_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String priorityCode;

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrganizationMaster_id")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private OrganizationMaster organizationMaster;


        public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }
}
