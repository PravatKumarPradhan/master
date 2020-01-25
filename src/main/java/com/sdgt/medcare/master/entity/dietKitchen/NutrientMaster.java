package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_nutrient", schema = "diet_kitchen")
public class NutrientMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_unit_id")
    private IngredientsMaster ingredientsMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public IngredientsMaster getIngredientsMaster() {
        return ingredientsMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setIngredientsMaster(IngredientsMaster ingredientsMaster) {
        this.ingredientsMaster = ingredientsMaster;
    }
}
