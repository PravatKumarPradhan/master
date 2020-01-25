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
@Table(name="m_meal_preference",schema = "adt")
public class MealPreference extends BaseMaster {
    @Column(name="meal_preference_code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String dischargeTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    public MealPreference(String dischargeTypeCode, OrganizationMaster organizationMaster) {
        this.dischargeTypeCode = dischargeTypeCode;
        this.organizationMaster = organizationMaster;
    }

    public String getDischargeTypeCode() {
        return dischargeTypeCode;
    }

    public void setDischargeTypeCode(String dischargeTypeCode) {
        this.dischargeTypeCode = dischargeTypeCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public MealPreference() {
    }

}
