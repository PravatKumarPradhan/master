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

@Table(name="m_discount_category")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class DiscountCategoryMaster extends BaseMaster {


    @Column(name="discount_category_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String discountCategoryCode;

    @MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;


    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="discount_type_id")
    @MastersFieldCustomAnnotation(displayName = "Discount Group",sequence = 4,nullable = false)
    private DiscountTypeMaster discountTypeMaster;

    public DiscountCategoryMaster() {
    }

    public DiscountCategoryMaster(String discountCategoryCode, OrganizationMaster organizationMaster, DiscountTypeMaster discountTypeMaster) {
        this.discountCategoryCode = discountCategoryCode;
        this.organizationMaster = organizationMaster;
        this.discountTypeMaster = discountTypeMaster;
    }

    public String getDiscountCategoryCode() {
        return discountCategoryCode;
    }

    public void setDiscountCategoryCode(String discountCategoryCode) {
        this.discountCategoryCode = discountCategoryCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public DiscountTypeMaster getDiscountTypeMaster() {
        return discountTypeMaster;
    }

    public void setDiscountTypeMaster(DiscountTypeMaster discountTypeMaster) {
        this.discountTypeMaster = discountTypeMaster;
    }

}
