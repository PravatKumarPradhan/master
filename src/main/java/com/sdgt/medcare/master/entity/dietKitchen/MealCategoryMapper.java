package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_meal_category_mapper", schema = "diet_kitchen")
public class MealCategoryMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="meal_id")
    private MealMaster mealMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "dish_category_id")
    private DishCategoryMaster dishCategoryMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public MealMaster getMealMaster() {
        return mealMaster;
    }

    public DishCategoryMaster getDishCategoryMaster() {
        return dishCategoryMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setMealMaster(MealMaster mealMaster) {
        this.mealMaster = mealMaster;
    }

    public void setDishCategoryMaster(DishCategoryMaster dishCategoryMaster) {
        this.dishCategoryMaster = dishCategoryMaster;
    }
}

