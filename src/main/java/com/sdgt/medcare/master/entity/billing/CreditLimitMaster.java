package com.sdgt.medcare.master.entity.billing;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.IdentificationTypeMaster;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.CompanyTypeMaster;
import com.sdgt.medcare.master.entity.org.EmployeeTypeMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@Entity
@Table(name = "m_credit_limit", schema = "service")
public class CreditLimitMaster extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "payer_type")
    private CompanyTypeMaster payerType;

    @OneToOne
    @JoinColumn(name = "company_id")
    private CompanyMaster companyMaster;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeMasterDetails employee;

    @OneToOne
    @JoinColumn(name = "employee_type_id")
    private EmployeeTypeMaster employeeType;

    @OneToOne
    @JoinColumn(name = "identification_type")
    private IdentificationTypeMaster identificationType;

    @Column(name = "identification_no")
    private String identificationNo;
}
