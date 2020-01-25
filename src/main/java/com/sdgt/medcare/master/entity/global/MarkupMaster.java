package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_markup", schema = "inventory")
@SequenceGenerator(name="markup_seq", sequenceName="inventory.markup_seq", allocationSize=1)
@DynamicUpdate
public class MarkupMaster extends BaseMaster {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_master_id")
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_master_id")
	private UnitMaster unitMaster;

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "markupMaster", cascade = CascadeType.ALL)
	private List<MarkupDetail> markupDetailList;*/

	public MarkupMaster() {
	}

	public MarkupMaster(Long id) {
		this.id = id;
	}

	public MarkupMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public MarkupMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public MarkupMaster(OrganizationMaster organizationMaster, UnitMaster unitMaster) {
		this.organizationMaster = organizationMaster;
		this.unitMaster = unitMaster;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

}
