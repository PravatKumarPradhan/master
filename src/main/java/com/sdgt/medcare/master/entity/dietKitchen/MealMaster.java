package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.BedCategory;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "m_meal", schema = "diet_kitchen")
public class MealMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @Column(name = "serving_time")
    private Time servingTime;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public Time getServingTime() {
        return servingTime;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }


    public void setServingTime(Time servingTime) {
        this.servingTime = servingTime;
    }
}
