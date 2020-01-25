package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "m_supplier", schema = "public")
//@SequenceGenerator(name = "supplier_seq", sequenceName = "public.supplier_seq", allocationSize = 1)
public class SupplierMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityMaster cityMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private StateMaster stateMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryMaster countryMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_mode_id")
    private PaymentModeMaster paymentModeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_term_id")
    private PaymentTermsMaster paymentTermsMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_and_condition_id")
    private TermsAndConditionsMaster termsAndConditionsMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private CurrencyMaster currencyMaster;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "payment_credit_days")
    private Integer paymentCreditDays;

    @Column(name = "contact_person_name1")
    private String contactPersonName1;

    @Column(name = "contact_person_name2")
    private String contactPersonName2;

    @Column(name = "contact_person_email1")
    private String contactPersonEmail1;

    @Column(name = "contact_person_email2")
    private String contactPersonEmail2;

    @Column(name = "contact_person_phone_number1")
    private String contactPersonPhoneNumber1;

    @Column(name = "contact_person_phone_number2")
    private String contactPersonPhoneNumber2;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "company_website", nullable = true)
    private String companyWebsite;

    @Column(name = "gst_no")
    private String gstNo;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "name")
    private String name;

    @Column(name = "drug_license_no")
    private String drugLicenseNo;

    @Column(name = "is_contact_person_primary1", nullable = false)
    private Boolean contactPersonIsPrimary1 = false;

    @Column(name = "is_contact_person_primary2", nullable = false)
    private Boolean contactPersonIsPrimary2 = false;

    @Column(name = "is_gst_supplier", nullable = false)
    private Boolean gSTSupplier = false;

    @Column(name = "is_loan_vendor", nullable = false)
    private Boolean loanVendor = false;

    public SupplierMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public CityMaster getCityMaster() {
        return cityMaster;
    }

    public void setCityMaster(CityMaster cityMaster) {
        this.cityMaster = cityMaster;
    }

    public PaymentModeMaster getPaymentModeMaster() {
        return paymentModeMaster;
    }

    public void setPaymentModeMaster(PaymentModeMaster paymentModeMaster) {
        this.paymentModeMaster = paymentModeMaster;
    }

    public PaymentTermsMaster getPaymentTermsMaster() {
        return paymentTermsMaster;
    }

    public void setPaymentTermsMaster(PaymentTermsMaster paymentTermsMaster) {
        this.paymentTermsMaster = paymentTermsMaster;
    }

    public TermsAndConditionsMaster getTermsAndConditionsMaster() {
        return termsAndConditionsMaster;
    }

    public void setTermsAndConditionsMaster(TermsAndConditionsMaster termsAndConditionsMaster) {
        this.termsAndConditionsMaster = termsAndConditionsMaster;
    }

    public CurrencyMaster getCurrencyMaster() {
        return currencyMaster;
    }

    public void setCurrencyMaster(CurrencyMaster currencyMaster) {
        this.currencyMaster = currencyMaster;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Integer getPaymentCreditDays() {
        return paymentCreditDays;
    }

    public void setPaymentCreditDays(Integer paymentCreditDays) {
        this.paymentCreditDays = paymentCreditDays;
    }

    public String getContactPersonName1() {
        return contactPersonName1;
    }

    public void setContactPersonName1(String contactPersonName1) {
        this.contactPersonName1 = contactPersonName1;
    }

    public String getContactPersonName2() {
        return contactPersonName2;
    }

    public void setContactPersonName2(String contactPersonName2) {
        this.contactPersonName2 = contactPersonName2;
    }

    public String getContactPersonEmail1() {
        return contactPersonEmail1;
    }

    public void setContactPersonEmail1(String contactPersonEmail1) {
        this.contactPersonEmail1 = contactPersonEmail1;
    }

    public String getContactPersonEmail2() {
        return contactPersonEmail2;
    }

    public void setContactPersonEmail2(String contactPersonEmail2) {
        this.contactPersonEmail2 = contactPersonEmail2;
    }

    public String getContactPersonPhoneNumber1() {
        return contactPersonPhoneNumber1;
    }

    public void setContactPersonPhoneNumber1(String contactPersonPhoneNumber1) {
        this.contactPersonPhoneNumber1 = contactPersonPhoneNumber1;
    }

    public String getContactPersonPhoneNumber2() {
        return contactPersonPhoneNumber2;
    }

    public void setContactPersonPhoneNumber2(String contactPersonPhoneNumber2) {
        this.contactPersonPhoneNumber2 = contactPersonPhoneNumber2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrugLicenseNo() {
        return drugLicenseNo;
    }

    public void setDrugLicenseNo(String drugLicenseNo) {
        this.drugLicenseNo = drugLicenseNo;
    }

    public Boolean getContactPersonIsPrimary1() {
        return contactPersonIsPrimary1;
    }

    public void setContactPersonIsPrimary1(Boolean contactPersonIsPrimary1) {
        this.contactPersonIsPrimary1 = contactPersonIsPrimary1;
    }

    public Boolean getContactPersonIsPrimary2() {
        return contactPersonIsPrimary2;
    }

    public void setContactPersonIsPrimary2(Boolean contactPersonIsPrimary2) {
        this.contactPersonIsPrimary2 = contactPersonIsPrimary2;
    }

    public Boolean getgSTSupplier() {
        return gSTSupplier;
    }

    public void setgSTSupplier(Boolean gSTSupplier) {
        this.gSTSupplier = gSTSupplier;
    }

    public Boolean getLoanVendor() {
        return loanVendor;
    }

    public void setLoanVendor(Boolean loanVendor) {
        this.loanVendor = loanVendor;
    }

    public StateMaster getStateMaster() {
        return stateMaster;
    }

    public void setStateMaster(StateMaster stateMaster) {
        this.stateMaster = stateMaster;
    }

    public CountryMaster getCountryMaster() {
        return countryMaster;
    }

    public void setCountryMaster(CountryMaster countryMaster) {
        this.countryMaster = countryMaster;
    }
}
