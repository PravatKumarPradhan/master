package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.CityMaster;
import com.sdgt.medcare.master.entity.global.CountryMaster;
import com.sdgt.medcare.master.entity.global.StateMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name="m_organization")
public class OrganizationMaster extends BaseMaster {

    @Column(name="org_Code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String orgCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id")
    @MastersFieldCustomAnnotation(displayName = "Country",sequence = 4)
    private CountryMaster countryMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="state_id")
    @MastersFieldCustomAnnotation(displayName = "State",sequence = 5)
    private StateMaster stateMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    @MastersFieldCustomAnnotation(displayName = "City",sequence = 6)
    private CityMaster cityMaster;


    @Column(name="pin_code")
    @MastersFieldCustomAnnotation(displayName = "Pincode",sequence = 7)
    private Integer pinCode;

    @Column(name="fax_no")
    @MastersFieldCustomAnnotation(displayName = "Fax",sequence = 8)
    private Integer faxNo;

    @Column(name="contact_no",length = 20)
    @MastersFieldCustomAnnotation(displayName = "Contact No",sequence = 9)
    private String contactNo;

    @Column(name="email_id")
    @MastersFieldCustomAnnotation(displayName = "Email Id",sequence = 10)
    private String emailId;

    @Column(name="org_logo")
    @MastersFieldCustomAnnotation(displayName = "Logo",sequence = 11)
    private Blob orgLogo;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="organizationMaster",cascade = CascadeType.PERSIST)
    List <UnitMaster> unitMasterList;

    public OrganizationMaster(String orgCode,CountryMaster countryMaster,StateMaster  stateMaster,CityMaster  cityMaster, Integer pinCode, Integer faxNo, String contactNo, String emailId, Blob orgLogo) {
        this.orgCode = orgCode;
        this.countryMaster = countryMaster;
        this.stateMaster = stateMaster;
        this.cityMaster = cityMaster;
        this.pinCode = pinCode;
        this.faxNo = faxNo;
        this.contactNo = contactNo;
        this.emailId = emailId;
        this.orgLogo = orgLogo;
    }

    public OrganizationMaster() {
    }


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public Blob getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(Blob orgLogo) {
        this.orgLogo = orgLogo;
    }


}
