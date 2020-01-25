package com.sdgt.medcare.master.entity.dietKitchen;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "m_dish_serving_time_mapper", schema = "diet_kitchen")
public class DishServingTimeMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dish_id")
    private DishMaster dishMaster;

    @Column(name = "from_time")
    private Time fromTime;

    @Column(name = "to_time")
    private Time toTime;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public DishMaster getDishMaster() {
        return dishMaster;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setDishMaster(DishMaster dishMaster) {
        this.dishMaster = dishMaster;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }
}
