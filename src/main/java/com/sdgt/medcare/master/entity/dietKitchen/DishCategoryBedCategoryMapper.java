package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.BedCategory;

import javax.persistence.*;

@Entity
@Table(name = "m_dish_category_bed_category_mapper", schema = "diet_kitchen")
public class DishCategoryBedCategoryMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "bed_category_id")
    private BedCategory bedCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "dish_category_id")
    private DishCategoryMaster dishCategoryMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public BedCategory getBedCategory() {
        return bedCategory;
    }

    public DishCategoryMaster getDishCategoryMaster() {
        return dishCategoryMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public void setBedCategory(BedCategory bedCategory) {
        this.bedCategory = bedCategory;
    }

    public void setDishCategoryMaster(DishCategoryMaster dishCategoryMaster) {
        this.dishCategoryMaster = dishCategoryMaster;
    }
}
