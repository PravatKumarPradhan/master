package com.sdgt.medcare.master.entity.unit;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
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

@Table(name="unit_dept_mapper")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class UnitDepartmentMapper  extends BaseMaster {

    @MastersFieldCustomAnnotation(displayName = "Code",visibleToUser = false,sequence = 10)
    @Column (name = "code",unique = true,length = 50)
    public String code;

    @MastersFieldCustomAnnotation(displayName = "Description",visibleToUser = false,sequence = 11)
    @Column(name = "description")
    public String desc;

    @MastersFieldCustomAnnotation(visibleToUser = false)
    @Column(name="unit_department_mapper_code",length = 50)
    private String unitDepartmentMapperCode;

    @MastersFieldCustomAnnotation(displayName ="Organisation" ,sequence = 1,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @MastersFieldCustomAnnotation(displayName ="Unit",sequence = 2,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @MastersFieldCustomAnnotation(displayName ="Department" ,sequence = 3,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="department_master_id")
    private DepartmentMaster departmentMaster;



    public UnitDepartmentMapper() {

    }
    public String getUnitDepartmentMapperCode() {
        return unitDepartmentMapperCode;
    }

    public void setUnitDepartmentMapperCode(String unitDepartmentMapperCode) {
        this.unitDepartmentMapperCode = unitDepartmentMapperCode;
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

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
