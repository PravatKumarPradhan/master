package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_diet_type", schema = "diet_kitchen")
public class DietTypeMaster extends BaseMaster {

    @Column(name = "is_theruputic_diet")
    private boolean isTheruputicDiet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public boolean getIsTheruputicDiet() {
        return isTheruputicDiet;
    }

    public void setIsTheruputicDiet(boolean istheruputicDiet) {
        this.isTheruputicDiet = istheruputicDiet;
    }
}
