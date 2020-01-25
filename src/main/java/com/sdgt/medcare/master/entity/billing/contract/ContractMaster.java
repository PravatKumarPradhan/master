package com.sdgt.medcare.master.entity.billing.contract;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.CompanyTypeMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Getter
@Setter
@Entity
@Table(name = "m_contract", schema = "service")
public class ContractMaster extends AbstractBaseEntity {
    @Column(name = "code")
    protected String code;

    @Column(name = "persist_status")
    private String persistStatus;

    @Column(name = "date")
    private Date date;

    @OneToOne
    @JoinColumn
    private CompanyTypeMaster companyType;

    @OneToOne
    @JoinColumn
    private CompanyMaster company;

    @OneToOne
    @JoinColumn
    private EmployeeMasterDetails employeeDetails;

    @Column(name = "contact_ref_no")
    private String referenceNumber;

    @Column(name = "effective_start_date")
    private Date effectiveStartDate;

    @Column(name = "effective_end_date")
    private Date effectiveEndDate;

    @Column(name = "description")
    private String desc;

    @OneToOne
    @JoinColumn
    private OrganizationMaster organization;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<UnitContractMapper> unitContractMapperList;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<ContractConfiguration> contractConfigurationCollection;
}
