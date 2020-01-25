package com.sdgt.medcare.master.entity.unit;


import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.CostCenterMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "unit_service_cost_center_mapper", schema = "service")
@Entity
public class UnitServiceCostCenterMapper extends BaseMaster {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private EmployeeMasterDetails employeeMasterDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")

    private DepartmentMaster departmentMaster;
    @Column(name = "unit_service_cost_center_code")
    private String unitServiceCostCenterCode;

    /*Unit specific*/

    @Column(name = "is_isDefault")
    private Boolean isDefaultCostCenter;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_service_mapper_id")
    private UnitServiceMapper unitServiceMapper;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cost_center_id")
    private CostCenterMaster costCenterMaster;
    public UnitServiceCostCenterMapper() {
    }

    public UnitServiceCostCenterMapper(String unitServiceCostCenterCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, CostCenterMaster costCenterMaster) {
        this.unitServiceCostCenterCode = unitServiceCostCenterCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.costCenterMaster = costCenterMaster;
    }


    public Boolean getDefaultCostCenter() {
        return isDefaultCostCenter;
    }

    public void setDefaultCostCenter(Boolean defaultCostCenter) {
        isDefaultCostCenter = defaultCostCenter;
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

    public UnitServiceMapper getUnitServiceMapper() {
        return unitServiceMapper;
    }

    public void setUnitServiceMapper(UnitServiceMapper unitServiceMapper) {
        this.unitServiceMapper = unitServiceMapper;
    }

    public String getUnitServiceCostCenterCode() {
        return unitServiceCostCenterCode;
    }

    public void setUnitServiceCostCenterCode(String unitServiceCostCenterCode) {
        this.unitServiceCostCenterCode = unitServiceCostCenterCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public CostCenterMaster getCostCenterMaster() {
        return costCenterMaster;
    }

    public void setCostCenterMaster(CostCenterMaster costCenterMaster) {
        this.costCenterMaster = costCenterMaster;
    }

}
