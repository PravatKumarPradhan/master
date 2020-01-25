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
@Table(name = "m_referral", schema = "public")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class ReferralMaster extends BaseMaster {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="organization_id")
	@MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReferralTypeMaster_id")
	@MastersFieldCustomAnnotation(displayName = "Referral Type",sequence = 3,nullable = false)
	private ReferralTypeMaster referralTypeMaster;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="areaMaster_id")
	@MastersFieldCustomAnnotation(displayName = "Area",sequence = 7)
	private AreaMaster areaMaster;

	@Column (name="pin_code",length = 10)
	@MastersFieldCustomAnnotation(displayName = "Pincode",sequence = 8)
	private Integer pinCode;

	@Column(name="contact_no",length = 20)
	@MastersFieldCustomAnnotation(displayName = "Contact No",sequence = 9)
	private String contactNo;

	@Column(name="contact_person_name",length = 255)
	@MastersFieldCustomAnnotation(displayName = "Contact Person",sequence = 10)
	private String contactPersonName;

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public ReferralTypeMaster getReferralTypeMaster() {
		return referralTypeMaster;
	}

	public void setReferralTypeMaster(ReferralTypeMaster referralTypeMaster) {
		this.referralTypeMaster = referralTypeMaster;
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

	public AreaMaster getAreaMaster() {
		return areaMaster;
	}

	public void setAreaMaster(AreaMaster areaMaster) {
		this.areaMaster = areaMaster;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public ReferralMaster() {
	}
}
