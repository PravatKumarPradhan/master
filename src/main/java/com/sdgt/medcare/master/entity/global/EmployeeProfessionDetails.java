package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Blob;
import java.util.Date;

/**
 * @author Pravat Kumar Pradhan
 */

@Entity
@Table (name = "m_employee_profession_details", schema = "employee")
public class EmployeeProfessionDetails extends BaseMaster {
    @Column (name = "company_name")
    private String companyName;

    @Column (name = "from_date")
    @Temporal (TemporalType.TIMESTAMP)
    public Date fromDate;

    @Column (name = "to_date")
    @Temporal (TemporalType.TIMESTAMP)
    public Date toDate;

    @Column (name = "remark", length = 10485760)
    private String remark;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_master_id")
    private EmployeeMasterDetails employeeMasterDetails;

    @Column
    private Blob attachment;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public EmployeeMasterDetails getEmployeeMasterDetails() {
        return employeeMasterDetails;
    }

    public void setEmployeeMasterDetails(EmployeeMasterDetails employeeMasterDetails) {
        this.employeeMasterDetails = employeeMasterDetails;
    }

    public Blob getAttachment() {
        return attachment;
    }

    public void setAttachment(Blob attachment) {
        this.attachment = attachment;
    }

}
