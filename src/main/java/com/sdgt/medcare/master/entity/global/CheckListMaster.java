package com.sdgt.medcare.master.entity.global;


import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="m_checklist",schema = "otims")
@Data
public class CheckListMaster extends BaseMaster {

	@Column(name="module")
	private String module;

	@Column(name="checkListStatus")
	private String checkListStatus;

	@Column(name="checklist_type")
	private String checkListType;

	@Column(name="checklist_header")
	private String checkListHeader;

	@Column(name="content_details")
	private String contentDetails;

	@Column(name="manditory")
	private boolean manditory;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	private UnitMaster unitMaster;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="org_id")
	private OrganizationMaster organizationMaster;

}
