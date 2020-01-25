package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="ot_emp_role",schema = "employee")
@Entity
public class OtEmployeeOtRole extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrganizationMaster_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_master_id")
    private EmployeeMasterDetails employeeMasterDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ot_role_id")
    private OtRoleMaster otRoleMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public EmployeeMasterDetails getEmployeeMasterDetails() {
        return employeeMasterDetails;
    }

    public void setEmployeeMasterDetails(EmployeeMasterDetails employeeMasterDetails) {
        this.employeeMasterDetails = employeeMasterDetails;
    }

    public OtRoleMaster getOtRoleMaster() {
        return otRoleMaster;
    }

    public void setOtRoleMaster(OtRoleMaster otRoleMaster) {
        this.otRoleMaster = otRoleMaster;
    }
}
