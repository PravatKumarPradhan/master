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

@Entity
@Table(name="m_discount_type")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class DiscountTypeMaster extends BaseMaster {

    @Column(name="discount_type_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String discountTypeCode;

    @Column(name="discount_type_status")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private Boolean discountTypeStatus;

    public Boolean getDiscountTypeStatus() {
        return discountTypeStatus;
    }

    public void setDiscountTypeStatus(Boolean discountTypeStatus) {
        this.discountTypeStatus = discountTypeStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    public DiscountTypeMaster() {
    }

    public DiscountTypeMaster(String discountTypeCode, OrganizationMaster organizationMaster) {
        this.discountTypeCode = discountTypeCode;
        this.organizationMaster = organizationMaster;
    }

    public String getDiscountTypeCode() {
        return discountTypeCode;
    }

    public void setDiscountTypeCode(String discountTypeCode) {
        this.discountTypeCode = discountTypeCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public DiscountTypeMaster(String discountTypeCode, Boolean discountTypeStatus, OrganizationMaster organizationMaster) {
        this.discountTypeCode = discountTypeCode;
        this.discountTypeStatus = discountTypeStatus;
        this.organizationMaster = organizationMaster;
    }
}
