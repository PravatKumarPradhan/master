package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "m_icu_type", schema = "adt")

public class ICUTypeMaster extends BaseMaster {


	@Column(name = "is_close_icu")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
	private char isCloseICU;

	@Column(name = "is_open_icu")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
	private char isOpenICU;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrganizationMaster_id")
	@MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
	private OrganizationMaster organizationMaster;

	
	public char getIsCloseICU() {
		return isCloseICU;
	}

	public void setIsCloseICU(char isCloseICU) {
		this.isCloseICU = isCloseICU;
	}

	public char getIsOpenICU() {
		return isOpenICU;
	}

	public void setIsOpenICU(char isOpenICU) {
		this.isOpenICU = isOpenICU;
	}

	
	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}


}
