package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
@Table(name = "m_employee_type", schema = "employee")
public class EmployeeTypeMaster extends BaseMaster {

	@Column
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
	private Boolean isDoctor;
	@Column
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
	private Boolean isStaff;
	@Column
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 5,editableByUser = false)
	private Boolean isStudent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrganizationMaster_id")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 6,editableByUser = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UnitMaster_id")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 7,editableByUser = false)
	private UnitMaster unitMaster;


	public Boolean getIsDoctor() {

		return isDoctor;
	}

	public void setIsDoctor(Boolean isDoctor) {

		this.isDoctor = isDoctor;
	}

	public Boolean getIsStaff() {

		return isStaff;
	}

	public void setIsStaff(Boolean isStaff) {

		this.isStaff = isStaff;
	}

	public Boolean getIsStudent() {

		return isStudent;
	}

	public void setIsStudent(Boolean isStudent) {

		this.isStudent = isStudent;
	}

	public OrganizationMaster getOrganizationMaster() {

		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public UnitMaster getUnitMaster()
	{
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster)
	{
		this.unitMaster = unitMaster;
	}



}
