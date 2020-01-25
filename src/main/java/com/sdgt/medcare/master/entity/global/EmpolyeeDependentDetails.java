package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "m_emp_dependent_details", schema = "employee")
public class EmpolyeeDependentDetails extends BaseMaster {


	@Column(name = "emp_doc_id")
	private String empDocId;

	@Column(name = "type_id")
	private String typeId;

	@Column(name = "prefix_id")
	private String prefixId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "relation_id")
	private String relationId;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "gendert_id")
	private String genderId;

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "identification_typet_id")
	private String identificationTypeId;

	@Column(name = "occupation_id")
	private String occupationId;

	@Column(name = "dob_kin")
	private Date dobKin;

	@Column(name = "is_nok")
	private char isNok;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")
	private GenderMaster genderMaster;
	
	
	@JoinColumn(name = "speciality_id")
	private String specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identification_type_id")
	private IdentificationTypeMaster identificationMaster;
	

	

	public String getEmpDocId() {
		return empDocId;
	}

	public void setEmpDocId(String empDocId) {
		this.empDocId = empDocId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(String prefixId) {
		this.prefixId = prefixId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getIdentificationTypeId() {
		return identificationTypeId;
	}

	public void setIdentificationTypeId(String identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}

	public Date getDobKin() {
		return dobKin;
	}

	public void setDobKin(Date dobKin) {
		this.dobKin = dobKin;
	}

	public char getIsNok() {
		return isNok;
	}

	public void setIsNok(char isNok) {
		this.isNok = isNok;
	}

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	public String getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(String specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public IdentificationTypeMaster getIdentificationMaster() {
		return identificationMaster;
	}

	public void setIdentificationMaster(IdentificationTypeMaster identificationMaster) {
		this.identificationMaster = identificationMaster;
	}

	


	
}
