package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@Table(name="m_faculty_master",schema = "employee")
public class FacultyMaster extends BaseMaster {

    @Column(name="faculty_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String facultyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;


    @OneToMany(fetch = FetchType.LAZY)
    private List<EmployeeMasterDetails> employeeDetails;

    public List<EmployeeMasterDetails> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<EmployeeMasterDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public FacultyMaster(String facultyCode, OrganizationMaster organizationMaster) {
        this.facultyCode = facultyCode;
        this.organizationMaster = organizationMaster;
    }

    public FacultyMaster() {
	}
}
