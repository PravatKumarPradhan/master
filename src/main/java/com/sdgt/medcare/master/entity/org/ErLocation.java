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
@Table(name="m_er_location",schema = "adt")
public class ErLocation extends BaseMaster {
    @Column(name="er_location_code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String erLocationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    public ErLocation(String erLocationCode, OrganizationMaster organizationMaster) {
        this.erLocationCode = erLocationCode;
        this.organizationMaster = organizationMaster;
    }

    public String getErLocationCode() {
        return erLocationCode;
    }

    public void setErLocationCode(String erLocationCode) {
        this.erLocationCode = erLocationCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ErLocation() {
    }
}
