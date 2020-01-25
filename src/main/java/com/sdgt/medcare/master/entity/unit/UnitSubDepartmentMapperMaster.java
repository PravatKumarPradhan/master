package com.sdgt.medcare.master.entity.unit;


import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.SubDepartmentMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="unit_sub_department_mapper")
public class UnitSubDepartmentMapperMaster  extends BaseMaster {
    @Column(name="unit_sub_department_mapper_code",length = 50)
    private String unitSubDepartmentMapperCode;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;



    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="sub_department_id")
    private SubDepartmentMaster subDepartmentMaster;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="department_id")
    private DepartmentMaster departmentMaster;


    public UnitSubDepartmentMapperMaster() {
    }

    public UnitSubDepartmentMapperMaster(String unitSubDepartmentMapperCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, SubDepartmentMaster subDepartmentMaster, DepartmentMaster departmentMaster) {
        this.unitSubDepartmentMapperCode = unitSubDepartmentMapperCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.subDepartmentMaster = subDepartmentMaster;
        this.departmentMaster = departmentMaster;
    }

    public String getUnitSubDepartmentMapperCode() {
        return unitSubDepartmentMapperCode;
    }

    public void setUnitSubDepartmentMapperCode(String unitSubDepartmentMapperCode) {
        this.unitSubDepartmentMapperCode = unitSubDepartmentMapperCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public UnitMaster  getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public SubDepartmentMaster getSubDepartmentMaster() {
        return subDepartmentMaster;
    }

    public void setSubDepartmentMaster(SubDepartmentMaster subDepartmentMaster) {
        this.subDepartmentMaster = subDepartmentMaster;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

}
