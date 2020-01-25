package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "m_general_ledger_master", schema = "public")
public class GeneralLedgerMaster extends BaseMaster {


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
