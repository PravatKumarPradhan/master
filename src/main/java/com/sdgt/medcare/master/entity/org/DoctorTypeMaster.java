package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_doctor_type", schema = "employee")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class DoctorTypeMaster extends BaseMaster {

	@Column
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
	private String docTypeCode;

	@MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="organization_id")

	private OrganizationMaster organizationMaster;

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public DoctorTypeMaster() {
	}
}
