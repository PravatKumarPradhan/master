package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.AreaMaster;
import com.sdgt.medcare.master.entity.global.CityMaster;
import com.sdgt.medcare.master.entity.global.CountryMaster;
import com.sdgt.medcare.master.entity.global.StateMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.Date;

@Table(name = "m_company")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class CompanyMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="organization_master_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_type_id")
    @MastersFieldCustomAnnotation(displayName = "Company Type",sequence = 3,nullable = false)
    private CompanyTypeMaster companyTypeMaster;

    @Column(name = "validity_from")
    @MastersFieldCustomAnnotation(displayName = "Validity From",sequence = 4)
    private Date validityFrom;

    @Column(name = "validity_to")
    @MastersFieldCustomAnnotation(displayName = "Validity To",sequence = 5)
    private Date validityTo;

    @Column(name = "credit_limit",length = 10)
    @MastersFieldCustomAnnotation(displayName = "Credit Limit",sequence = 6)
    private Float creditLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @MastersFieldCustomAnnotation(displayName = "Country",sequence = 7)
    private CountryMaster countryMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @MastersFieldCustomAnnotation(displayName = "State",sequence = 8)
    private StateMaster stateMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityMaster_id")
    @MastersFieldCustomAnnotation(displayName = "City",sequence = 9)
    private CityMaster cityMaster;

    @Column(name = "pin_code", length = 10)
    @MastersFieldCustomAnnotation(displayName = "Pincode",sequence = 10)
    private Integer pinCode;

    @Column(name = "fax_no")
    @MastersFieldCustomAnnotation(displayName = "Fax",sequence =11)
    private Integer faxNo;

    @Column(name = "contact_no", length = 20)
    @MastersFieldCustomAnnotation(displayName = "Contact No",sequence = 12)
    private String contactNo;

    @Column(name = "email_id")
    @MastersFieldCustomAnnotation(displayName = "Email Id",sequence = 13)
    private String emailId;

    @Column(name = "contact_person_name")
    @MastersFieldCustomAnnotation(displayName = "Contact Person",sequence = 14)
    private String contactPersonName;

    @Column(name="payment_terms",length = 255)
    @MastersFieldCustomAnnotation(displayName = "Payment Terms",sequence = 15)
    private  String paymentTerms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 16,editableByUser = false)
    private AreaMaster areaMaster;

    @Column(name = "is_self")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 17,editableByUser = false)
    private Boolean isSelf;

    @Column(name = "company_logo")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 18,editableByUser = false)
    private Blob companyLogo;

    @Column(name = "mobile_number", length = 20)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 19,editableByUser = false)
    private String mobileNumber;

    @Column(name = "company_code", unique = true, length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 20,editableByUser = false)
    private String companyCode;


    public CompanyTypeMaster getCompanyTypeMaster() {
        return companyTypeMaster;
    }

    public void setCompanyTypeMaster(CompanyTypeMaster companyTypeMaster) {
        this.companyTypeMaster = companyTypeMaster;
    }

    public AreaMaster getAreaMaster() {
        return areaMaster;
    }

    public void setAreaMaster(AreaMaster areaMaster) {
        this.areaMaster = areaMaster;
    }

    public Boolean getSelf() {
        return isSelf;
    }

    public void setSelf(Boolean self) {
        isSelf = self;
    }

    public Blob getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(Blob companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }


    public CompanyMaster(String companyCode, OrganizationMaster organizationMaster, CountryMaster countryMaster, StateMaster stateMaster, CityMaster cityMaster, CompanyTypeMaster companyTypeMaster, AreaMaster areaMaster, Boolean isSelf, Integer pinCode, Integer faxNo, String contactNo, String emailId, Blob companyLogo, String contactPersonName, String mobileNumber) {
        this.companyCode = companyCode;
        this.organizationMaster = organizationMaster;
        this.countryMaster = countryMaster;
        this.stateMaster = stateMaster;
        this.cityMaster = cityMaster;
        this.companyTypeMaster = companyTypeMaster;
        this.areaMaster = areaMaster;
        this.isSelf = isSelf;
        this.pinCode = pinCode;
        this.faxNo = faxNo;
        this.contactNo = contactNo;
        this.emailId = emailId;
        this.companyLogo = companyLogo;
        this.contactPersonName = contactPersonName;
        this.mobileNumber = mobileNumber;
    }


    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public CountryMaster getCountryMaster() {
        return countryMaster;
    }

    public void setCountryMaster(CountryMaster countryMaster) {
        this.countryMaster = countryMaster;
    }

    public StateMaster getStateMaster() {
        return stateMaster;
    }

    public void setStateMaster(StateMaster stateMaster) {
        this.stateMaster = stateMaster;
    }

    public CityMaster getCityMaster() {
        return cityMaster;
    }

    public void setCityMaster(CityMaster cityMaster) {
        this.cityMaster = cityMaster;
    }


    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Integer getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(Integer faxNo) {
        this.faxNo = faxNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getValidityFrom() {
        return validityFrom;
    }

    public void setValidityFrom(Date validityFrom) {
        this.validityFrom = validityFrom;
    }

    public Date getValidityTo() {
        return validityTo;
    }

    public void setValidityTo(Date validityTo) {
        this.validityTo = validityTo;
    }


    public CompanyMaster() {
    }
}
