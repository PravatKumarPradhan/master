package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "m_hierarchy_master", schema = "adt")

public class HierarchyMaster extends BaseMaster {


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

