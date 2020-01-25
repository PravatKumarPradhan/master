package com.sdgt.medcare.master.entity.billing.discount.templete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.billing.contract.UnitContractMapper;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.CompanyTypeMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import lombok.Data;

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
@Data
@Entity
@Table(name = "m_discount_template", schema = "service")
public class DiscountTemplateMaster extends BaseMaster {
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

    @OneToOne
    @JoinColumn
    private OrganizationMaster organization;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<UnitContractMapper> unitContractMapperList;

    @OneToMany(mappedBy = "discountTemplate", cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<DiscountTemplateConfiguration> discountTemplateConfigurationCollection;
}
