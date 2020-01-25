package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_meal_service")
public class MealServiceMaster extends BaseMaster {

    @Column(name="mel_service_code",unique = true,length = 50)
    private String mealServiceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    public MealServiceMaster(String mealServiceCode, OrganizationMaster organizationMaster, UnitMaster unitMaster) {
        this.mealServiceCode = mealServiceCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
    }

    public String getMealServiceCode() {
        return mealServiceCode;
    }

    public void setMealServiceCode(String mealServiceCode) {
        this.mealServiceCode = mealServiceCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public MealServiceMaster() {
    }

}
