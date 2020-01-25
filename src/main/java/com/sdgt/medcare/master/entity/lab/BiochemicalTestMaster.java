package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_biochemical_test", schema = "lab")
public class BiochemicalTestMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    @Column(name = "referance_values")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String referanceValues;

    public String getReferanceValues() {
        return referanceValues;
    }

    public void setReferanceValues(String referanceValues) {
        this.referanceValues = referanceValues;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }
}
