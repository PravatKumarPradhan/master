package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_dish", schema = "diet_kitchen")
public class DishMaster extends BaseMaster {

    @Column(name = "is_veg")
    private boolean isVeg;

    @Column(name = "is_department")
    private boolean isDepartment;

    @Column(name = "is_counter_sale")
    private boolean isCounterSale;

    @Column(name = "is_ala_cart")
    private boolean isAlaCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "dish_category_id")
    private DishCategoryMaster dishCategoryMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_unit_id")
    private IngredientUnitMaster ingredientUnitMaster;

    @Column(name = "values")
    private int values;

    public IngredientUnitMaster getIngredientUnitMaster() {
        return ingredientUnitMaster;
    }

    public int getValues() {
        return values;
    }

    public void setIngredientUnitMaster(IngredientUnitMaster ingredientUnitMaster) {
        this.ingredientUnitMaster = ingredientUnitMaster;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public boolean getVeg() {
        return isVeg;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public boolean isDepartment() {
        return isDepartment;
    }

    public boolean isCounterSale() {
        return isCounterSale;
    }

    public boolean isAlaCart() {
        return isAlaCart;
    }

    public DishCategoryMaster getDishCategoryMaster() {
        return dishCategoryMaster;
    }

    public void setDepartment(boolean department) {
        isDepartment = department;
    }

    public void setCounterSale(boolean counterSale) {
        isCounterSale = counterSale;
    }

    public void setAlaCart(boolean alaCart) {
        isAlaCart = alaCart;
    }

    public void setDishCategoryMaster(DishCategoryMaster dishCategoryMaster) {
        this.dishCategoryMaster = dishCategoryMaster;
    }
}
