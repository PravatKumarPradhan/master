package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "m_patient_source_master", schema = "public")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class PatientSourceMaster extends BaseMaster {


	@MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "OrganizationMaster_id")

	private OrganizationMaster organizationMaster;


	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public PatientSourceMaster() {
	}
}

