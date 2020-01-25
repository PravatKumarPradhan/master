package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_emp_contact_details", schema = "employee")
public class EmployeeContactDetails extends BaseMaster {

	@Column(name = "local_address")
	private String localAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "localCountryMaster_id")
	private CountryMaster localCountryMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "localStateMaster_id")
	private StateMaster localStateMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "localCityMaster_id")
	private CityMaster localCityMaster;

	@Column(name = "local_pincode")
	private String localPincode;

	@Column(name = "local_contact_no")
	private String localContactNo;

	@Column(name = "permanent_address")
	private String permanentAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permanentCountryMaster_id")
	private CountryMaster permanentCountryMaster;

	@OneToOne(fetch =FetchType.LAZY)
	@JsonIgnoreProperties("employeeContactDetails")
	@JoinColumn(name="employee_master_details")
	private EmployeeMasterDetails employeeMasterDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permanentStateMaster_id")
	private StateMaster permanentStateMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permanentCityMaster_id")
	private CityMaster permanentCityMaster;

	@Column(name = "permanent_pincode")
	private String permanentPincode;

	@Column(name = "permanent_contact_no")
	private String permanentContactNo;



	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public CountryMaster getLocalCountryMaster() {
		return localCountryMaster;
	}

	public void setLocalCountryMaster(CountryMaster localCountryMaster) {
		this.localCountryMaster = localCountryMaster;
	}

	public StateMaster getLocalStateMaster() {
		return localStateMaster;
	}

	public void setLocalStateMaster(StateMaster localStateMaster) {
		this.localStateMaster = localStateMaster;
	}

	public CityMaster getLocalCityMaster() {
		return localCityMaster;
	}

	public void setLocalCityMaster(CityMaster localCityMaster) {
		this.localCityMaster = localCityMaster;
	}

	public String getLocalPincode() {
		return localPincode;
	}

	public void setLocalPincode(String localPincode) {
		this.localPincode = localPincode;
	}

	public String getLocalContactNo() {
		return localContactNo;
	}

	public void setLocalContactNo(String localContactNo) {
		this.localContactNo = localContactNo;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public CountryMaster getPermanentCountryMaster() {
		return permanentCountryMaster;
	}

	public void setPermanentCountryMaster(CountryMaster permanentCountryMaster) {
		this.permanentCountryMaster = permanentCountryMaster;
	}

	public StateMaster getPermanentStateMaster() {
		return permanentStateMaster;
	}

	public void setPermanentStateMaster(StateMaster permanentStateMaster) {
		this.permanentStateMaster = permanentStateMaster;
	}

	public CityMaster getPermanentCityMaster() {
		return permanentCityMaster;
	}

	public void setPermanentCityMaster(CityMaster permanentCityMaster) {
		this.permanentCityMaster = permanentCityMaster;
	}

	public String getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public String getPermanentContactNo() {
		return permanentContactNo;
	}

	public void setPermanentContactNo(String permanentContactNo) {
		this.permanentContactNo = permanentContactNo;
	}

	public EmployeeMasterDetails getEmployeeMasterDetails() {
		return employeeMasterDetails;
	}

	public void setEmployeeMasterDetails(EmployeeMasterDetails employeeMasterDetails) {
		this.employeeMasterDetails = employeeMasterDetails;
	}
}
