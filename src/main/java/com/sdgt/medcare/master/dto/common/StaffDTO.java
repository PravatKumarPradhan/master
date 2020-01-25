package com.sdgt.medcare.master.dto.common;

import com.sdgt.medcare.master.entity.unit.EmployeeDepartmentDetails;

import java.util.List;

public class StaffDTO {
    private Long systemId;
    private  Boolean staffStatus;
    private String staffCode;
    private String staffName;
    private List<Long>  staffDepartment;
    private Long staffGenderId;
    private Long staffMaritalId   ;
    private Long staffNationalityId;
    private Long staffPrefixId;
    private List<Long> staffDependent;

    private List <EmployeeDepartmentDetails>  employeeDepartmentDetailsList;


    public Long getSystemId() {

        return systemId;
    }

    public void setSystemId(Long systemId) {

        this.systemId = systemId;
    }

    public Boolean getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(Boolean staffStatus) {

        this.staffStatus = staffStatus;
    }

    public String getStaffCode() {

        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public List<Long> getStaffDepartment() {
        return staffDepartment;
    }

    public void setStaffDepartment(List<Long> staffDepartment) {
        this.staffDepartment = staffDepartment;
    }

    public Long getStaffGenderId() {
        return staffGenderId;
    }

    public void setStaffGenderId(Long staffGenderId) {

        this.staffGenderId = staffGenderId;
    }

    public Long getStaffMaritalId() {
        return staffMaritalId;
    }

    public void setStaffMaritalId(Long staffMaritalId) {

        this.staffMaritalId = staffMaritalId;
    }

    public Long getStaffNationalityId() {

        return staffNationalityId;
    }

    public void setStaffNationalityId(Long staffNationalityId) {

        this.staffNationalityId = staffNationalityId;
    }

    public Long getStaffPrefixId() {

        return staffPrefixId;
    }

    public void setStaffPrefixId(Long staffPrefixId) {

        this.staffPrefixId = staffPrefixId;
    }

    public List<Long> getStaffDependent() {
        return staffDependent;
    }

    public void setStaffDependent(List<Long> staffDependent) {
        this.staffDependent = staffDependent;
    }
}
