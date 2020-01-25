package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_ingredient", schema = "diet_kitchen")
public class IngredientsMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_unit_id")
    private IngredientUnitMaster ingredientUnitMaster;


    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public IngredientUnitMaster getIngredientUnitMaster() {
        return ingredientUnitMaster;
    }

    public void setIngredientUnitMaster(IngredientUnitMaster ingredientUnitMaster) {
        this.ingredientUnitMaster = ingredientUnitMaster;
    }
}
