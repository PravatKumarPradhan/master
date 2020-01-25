package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "m_discharge_destination",schema = "adt")
@Entity
public class DischargeDestination extends BaseMaster {

    @Column(name = "discharge_destination_code", unique = true, length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String dischargeDestinationCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    public DischargeDestination(String dischargeDestinationCode, OrganizationMaster organizationMaster) {
        this.dischargeDestinationCode = dischargeDestinationCode;
        this.organizationMaster = organizationMaster;
    }

    public String getDischargeDestinationCode() {
        return dischargeDestinationCode;
    }

    public void setDischargeDestinationCode(String dischargeDestinationCode) {
        this.dischargeDestinationCode = dischargeDestinationCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public DischargeDestination() {
    }
}
