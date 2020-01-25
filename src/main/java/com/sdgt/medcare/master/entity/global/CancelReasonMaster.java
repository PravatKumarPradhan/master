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
@Table(name="m_cancel_reason")
public class CancelReasonMaster extends BaseMaster {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrganizationMaster_id")
	private OrganizationMaster organizationMaster;
	
	@Column(name="category")
	private String category;

	public OrganizationMaster getOrganizationMaster()
	{
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
