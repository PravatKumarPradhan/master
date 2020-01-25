package com.sdgt.medcare.master.entity.org;



import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.AreaMaster;
import com.sdgt.medcare.master.entity.global.CityMaster;
import com.sdgt.medcare.master.entity.global.CountryMaster;
import com.sdgt.medcare.master.entity.global.StateMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="m_associate_company")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class AssociatedCompanyMaster  extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    @Column(name = "credit_limit",length = 10)
    @MastersFieldCustomAnnotation(displayName = "Credit Limit",sequence = 3)
    private Float creditLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id")
    @MastersFieldCustomAnnotation(displayName = "Country",sequence = 4)
    private CountryMaster countryMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="state_id")
    @MastersFieldCustomAnnotation(displayName = "State",sequence = 5)
    private StateMaster stateMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cityMaster_id")
    @MastersFieldCustomAnnotation(displayName = "City",sequence = 6)
    private CityMaster cityMaster;

    @Column(name="pin_code",length = 10)
    @MastersFieldCustomAnnotation(displayName = "Pincode",sequence = 7)
    private Integer pinCode;

    @Column(name="fax_no",length = 20)
    @MastersFieldCustomAnnotation(displayName = "Fax",sequence = 8)
    private Integer faxNo;

    @Column(name="contact_no",length = 20)
    @MastersFieldCustomAnnotation(displayName = "Contact No",sequence = 9)
    private String contactNo;

    @Column(name="email_id",length = 50)
    @MastersFieldCustomAnnotation(displayName = "Email Id",sequence = 10)
    private String emailId;

    @Column(name="contact_person_name",length = 255)
    @MastersFieldCustomAnnotation(displayName = "Contact Person",sequence = 11)
    private String contactPersonName;

    @Column(name="payment_terms",length = 255)
    @MastersFieldCustomAnnotation(displayName = "Payment Terms",sequence = 12)
    private  String paymentTerms;


    @Column(name="mobile_number",length = 20)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 13,editableByUser = false)
    private  String mobileNumber;


    @Column(name="associated_company_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 14,editableByUser = false)
    private String associatedCompanyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="area_id")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 15,editableByUser = false)
    private AreaMaster areaMaster;

    public String getAssociatedCompanyCode() {
        return associatedCompanyCode;
    }

    public void setAssociatedCompanyCode(String associatedCompanyCode) {
        this.associatedCompanyCode = associatedCompanyCode;
    }

    public Float getCreditLimit() {        return creditLimit;    }

    public void setCreditLimit(Float creditLimit) {
        this.creditLimit = creditLimit;
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


    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }


    public AreaMaster getAreaMaster() {
        return areaMaster;
    }

    public void setAreaMaster(AreaMaster areaMaster) {
        this.areaMaster = areaMaster;
    }

    public AssociatedCompanyMaster() {
    }

    public AssociatedCompanyMaster(String associatedCompanyCode, Integer pinCode, Integer faxNo, String contactNo, String emailId, String contactPersonName, String mobileNumber, OrganizationMaster organizationMaster, CountryMaster countryMaster, StateMaster stateMaster, CityMaster cityMaster, AreaMaster areaMaster) {
        this.associatedCompanyCode = associatedCompanyCode;
        this.pinCode = pinCode;
        this.faxNo = faxNo;
        this.contactNo = contactNo;
        this.emailId = emailId;
        this.contactPersonName = contactPersonName;
        this.mobileNumber = mobileNumber;
        this.organizationMaster = organizationMaster;
        this.countryMaster = countryMaster;
        this.stateMaster = stateMaster;
        this.cityMaster = cityMaster;
        this.areaMaster = areaMaster;
    }

}
