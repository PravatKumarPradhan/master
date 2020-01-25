package com.sdgt.medcare.master.entity.housekeeping;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "m_schedule", schema = "housekeeping")
public class ScheduleMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @Column(name = "from_time")
    private Time fromTime;

    @Column(name = "to_time")
    private Time toTime;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public Time getFromTime() { return fromTime; }

    public Time getToTime() { return toTime; }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }
}
