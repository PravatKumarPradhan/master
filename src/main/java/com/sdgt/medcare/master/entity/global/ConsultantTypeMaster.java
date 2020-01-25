package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "m_consultant_type_master", schema = "adt")
public class ConsultantTypeMaster extends BaseMaster
{
	@Column(name="cosultantTypeCode")
	private String consultantTypeCode;

	public String getConsultantTypeCode() {
		return consultantTypeCode;
	}

	public void setConsultantTypeCode(String consultantTypeCode) {
		this.consultantTypeCode = consultantTypeCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrganizationMaster_id")
	private OrganizationMaster organizationMaster;


	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}



}
