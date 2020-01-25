package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_occupancy_unit",schema = "adt")
@Entity
public class OccupancyUnitMaster extends BaseMaster {

    @Column(name = "occupancy_unit_code", unique = true, length = 50)
    private String occupancyUnitCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private OrganizationMaster organizationMaster;

    @Column(name="no_of_hours")
    private String noOfHours;


    public OccupancyUnitMaster(String occupancyUnitCode) {
        this.occupancyUnitCode = occupancyUnitCode;
    }

    public String getOccupancyUnitCode() {
        return occupancyUnitCode;
    }

    public void setOccupancyUnitCode(String occupancyUnitCode) {
        this.occupancyUnitCode = occupancyUnitCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public OccupancyUnitMaster() {
    }

    public String getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(String noOfHours) {
        this.noOfHours = noOfHours;
    }

    public OccupancyUnitMaster(String occupancyUnitCode, OrganizationMaster organizationMaster, String noOfHours) {
        this.occupancyUnitCode = occupancyUnitCode;
        this.organizationMaster = organizationMaster;
        this.noOfHours = noOfHours;
    }
}
