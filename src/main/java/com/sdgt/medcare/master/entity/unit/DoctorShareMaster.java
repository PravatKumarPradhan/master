package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.org.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "m_doctor_share")
public class DoctorShareMaster extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @MastersFieldCustomAnnotation(displayName = "Unit",sequence = 3)
    @JsonIgnoreProperties("organizationMaster")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 4)
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @JsonIgnoreProperties({"organizationMaster","subDepartmentMasters"})
    @MastersFieldCustomAnnotation(displayName = "Department",sequence = 5)
    private DepartmentMaster departmentMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_master_id")
    @JsonIgnoreProperties({"unitMaster","genderMaster","maritalStatus","nationalityMaster","occupationMaster"
            ,"raceMaster","identificationTypeMaster","departmentMaster","organizationMaster","listunitmaster","facultyMaster"})
    @MastersFieldCustomAnnotation(displayName = "Doctor",sequence = 6)
    private EmployeeMasterDetails employeeMasterDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @MastersFieldCustomAnnotation(displayName = "Group",sequence = 7)
    private GroupMaster groupMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subgroup_id")
    @MastersFieldCustomAnnotation(displayName = "SubGroup",sequence = 8)
    private SubGroupMaster subGroupMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    @MastersFieldCustomAnnotation(displayName = "Service ID",sequence = 9)
    private ServiceMaster serviceMaster;

    @MastersFieldCustomAnnotation(displayName = "Service Code",sequence = 10)
    @Column(name = "service_code")
    private String serviceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financialclass_id")
    @MastersFieldCustomAnnotation(displayName = "Financial Class",sequence = 11)
    private FinancialClassMaster financialClassMaster;

    @Column(name = "effective_date")
    @MastersFieldCustomAnnotation(displayName = "Effective Date",sequence = 12)
    private Date effectiveDate;

    @Column(name = "share_Percentage")
    @MastersFieldCustomAnnotation(displayName = "Share Percentage",sequence = 13)
    private float sharePercentage;

    @Column(name = "share_Amount")
    @MastersFieldCustomAnnotation(displayName = "Share Amount",sequence = 14)
    private float shareAmount;

    public  DoctorShareMaster(){
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

    public GroupMaster getGroupMaster() {
        return groupMaster;
    }

    public void setGroupMaster(GroupMaster groupMaster) {
        this.groupMaster = groupMaster;
    }

    public SubGroupMaster getSubGroupMaster() {
        return subGroupMaster;
    }

    public void setSubGroupMaster(SubGroupMaster subGroupMaster) {
        this.subGroupMaster = subGroupMaster;
    }

    public ServiceMaster getServiceMaster() {
        return serviceMaster;
    }

    public void setServiceMaster(ServiceMaster serviceMaster) {
        this.serviceMaster = serviceMaster;
    }

    public FinancialClassMaster getFinancialClassMaster() {
        return financialClassMaster;
    }

    public void setFinancialClassMaster(FinancialClassMaster financialClassMaster) {
        this.financialClassMaster = financialClassMaster;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public float getSharePercentage() {
        return sharePercentage;
    }

    public void setSharePercentage(float sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    public float getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(float shareAmount) {
        this.shareAmount = shareAmount;
    }

    public EmployeeMasterDetails getEmployeeMasterDetails() {
        return employeeMasterDetails;
    }

    public void setEmployeeMasterDetails(EmployeeMasterDetails employeeMasterDetails) {
        this.employeeMasterDetails = employeeMasterDetails;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

}
