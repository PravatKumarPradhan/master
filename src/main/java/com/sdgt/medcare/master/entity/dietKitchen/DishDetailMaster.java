package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_dish_detail", schema = "diet_kitchen")
public class DishDetailMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private IngredientsMaster ingredientsMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_unit_id")
    private IngredientUnitMaster ingredientUnitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dish_id")
    private DishMaster dishMaster;

    @Column(name = "unit_quantity")
    private double unitQuantity;

    @Column(name = "required_quantity")
    private int requiredQuantity;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public IngredientsMaster getIngredientsMaster() {
        return ingredientsMaster;
    }

    public IngredientUnitMaster getIngredientUnitMaster() {
        return ingredientUnitMaster;
    }

    public DishMaster getDishMaster() {
        return dishMaster;
    }

    public double getUnitQuantity() {
        return unitQuantity;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setIngredientsMaster(IngredientsMaster ingredientsMaster) {
        this.ingredientsMaster = ingredientsMaster;
    }

    public void setIngredientUnitMaster(IngredientUnitMaster ingredientUnitMaster) {
        this.ingredientUnitMaster = ingredientUnitMaster;
    }

    public void setDishMaster(DishMaster dishMaster) {
        this.dishMaster = dishMaster;
    }

    public void setUnitQuantity(double unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public void setRequiredQuantity(int requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }
}
