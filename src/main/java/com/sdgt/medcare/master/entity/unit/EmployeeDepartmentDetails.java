package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="emp_dept_mapper",schema = "employee")
public class EmployeeDepartmentDetails extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_master")
    @JsonIgnoreProperties({"employeeMasterDetails","subDepartmentMasters","organizationMaster"})
    private EmployeeMasterDetails employeeMasterDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_master")
    private DepartmentMaster departmentMaster;


    @Column(name="is_default")
    private Boolean isDefault;


    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public EmployeeMasterDetails getEmployeeMasterDetails() {
        return employeeMasterDetails;
    }

    public void setEmployeeMasterDetails(EmployeeMasterDetails employeeMasterDetails) {
        this.employeeMasterDetails = employeeMasterDetails;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }
}
