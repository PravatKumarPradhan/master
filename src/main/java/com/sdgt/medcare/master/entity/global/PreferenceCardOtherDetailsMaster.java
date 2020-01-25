package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_preference_card_other_details", schema = "otims")
public class PreferenceCardOtherDetailsMaster extends BaseMaster {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private UnitMaster unitMaster;

	@JsonDeserialize
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "preference_card_id")
	private PreferenceCardMaster preferenceCardMaster;
	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "preference_Card_id")
	private PreferenceCardMaster preferenceCard;*/

	@Column(name = "position_details")
	private String postionDetails;

	@Column(name = "preparation")
	private String preparation;

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

	public String getPostionDetails() {
		return postionDetails;
	}

	public void setPostionDetails(String postionDetails) {
		this.postionDetails = postionDetails;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public PreferenceCardMaster getPreferenceCardMaster() {
		return preferenceCardMaster;
	}

	public void setPreferenceCardMaster(PreferenceCardMaster preferenceCardMaster) {
		this.preferenceCardMaster = preferenceCardMaster;
	}
}
