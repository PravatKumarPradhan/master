package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.BedCategory;

import javax.persistence.*;

@Entity
@Table(name = "m_meal_bed_mapper", schema = "diet_kitchen")
public class MealBedMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "meal_id")
    private MealMaster mealMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "bed_category_id")
    private BedCategory bedCategory;

    @Column(name = "is_available")
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public MealMaster getMealMaster() {
        return mealMaster;
    }

    public BedCategory getBedCategory() {
        return bedCategory;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setMealMaster(MealMaster mealMaster) {
        this.mealMaster = mealMaster;
    }

    public void setBedCategory(BedCategory bedCategory) {
        this.bedCategory = bedCategory;
    }
}
