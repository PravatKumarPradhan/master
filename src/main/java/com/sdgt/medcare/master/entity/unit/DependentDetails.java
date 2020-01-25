package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.GenderMaster;
import com.sdgt.medcare.master.entity.global.IdentificationTypeMaster;
import com.sdgt.medcare.master.entity.global.OccupationMaster;
import com.sdgt.medcare.master.entity.global.PrefixMaster;
import com.sdgt.medcare.master.entity.global.RelationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


/**
 * @author Pravat Kumar Pradhan
 */
@Entity
@Table(name = "m_dependent_details", schema = "employee")
public class DependentDetails extends BaseMaster {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrefixMaster_id")
	private PrefixMaster prefixMaster;

	@Column(name = "name")
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GenderMaster_id")
	private GenderMaster genderMaster;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "date_of_birth")
	@Temporal(TemporalType.TIMESTAMP)
	public Date dob;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdentificationTypeMaster_id")
	private IdentificationTypeMaster identificationTypeMaster;

	@Column(name = "identification_no")
	private String identificationNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OccupationMaster_id")
	private OccupationMaster OccupationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RelationMaster_id")
	private RelationMaster relationMaster;

	@Column(name = "is_nok")
	private Boolean isNok;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_master_id")
	@JsonIgnoreProperties("listDependentDetails")
	private EmployeeMasterDetails employeeMasterDetails;


	@Column(name="age")
	private String age;

	public PrefixMaster getPrefixMaster() {

		return prefixMaster;
	}

	public void setPrefixMaster(PrefixMaster prefixMaster)
	{
		this.prefixMaster = prefixMaster;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) 	{
		this.name = name;
	}

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public IdentificationTypeMaster getIdentificationTypeMaster() {
		return identificationTypeMaster;
	}

	public void setIdentificationTypeMaster(IdentificationTypeMaster identificationTypeMaster) {
		this.identificationTypeMaster = identificationTypeMaster;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public OccupationMaster getOccupationMaster() {
		return OccupationMaster;
	}

	public void setOccupationMaster(OccupationMaster occupationMaster) {
		OccupationMaster = occupationMaster;
	}

	public RelationMaster getRelationMaster() {
		return relationMaster;
	}
	public void setRelationMaster(RelationMaster relationMaster) {
		this.relationMaster = relationMaster;
	}
	public Boolean getIsNok() {
		return isNok;
	}

	public void setIsNok(Boolean isNok) {
		this.isNok = isNok;
	}
	public EmployeeMasterDetails getEmployeeMasterDetails() {
		return employeeMasterDetails;
	}
	public void setEmployeeMasterDetails(EmployeeMasterDetails employeeMasterDetails) {
		this.employeeMasterDetails = employeeMasterDetails;
	}
}
