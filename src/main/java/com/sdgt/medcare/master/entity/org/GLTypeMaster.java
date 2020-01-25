package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_gl_type")
@Entity
public class GLTypeMaster extends BaseMaster {

    @Column(name = "gl_type_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String glTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    public GLTypeMaster() {
    }

    public GLTypeMaster(String glTypeCode, OrganizationMaster organizationMaster) {
        this.glTypeCode = glTypeCode;
        this.organizationMaster = organizationMaster;
    }

    public String getGlTypeCode() {
        return glTypeCode;
    }

    public void setGlTypeCode(String glTypeCode) {
        this.glTypeCode = glTypeCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

}
