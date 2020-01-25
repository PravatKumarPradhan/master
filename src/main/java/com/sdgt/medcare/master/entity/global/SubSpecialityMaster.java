package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.GeneralLedgerMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class SubSpecialityMaster extends BaseMaster {

	@Column(name = "is_modality")
	private char isModality;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GeneralLedgerMaster_id")
	private GeneralLedgerMaster generalLedgerMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SpecialityMaster_id")
	private SpecialityMaster specialityMaster;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subSpecialityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ModalityMaster> modalityMastersList;

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public char getIsModality() {
		return isModality;
	}

	public void setIsModality(char isModality) {
		this.isModality = (isModality == '\u0000') ? 'A' : isModality;
	}

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public List<ModalityMaster> getModalityMastersList() {
		return modalityMastersList;
	}

	public void setModalityMastersList(List<ModalityMaster> modalityMastersList) {
		this.modalityMastersList = modalityMastersList;
	}

	public GeneralLedgerMaster getGeneralLedgerMaster() {
		return generalLedgerMaster;
	}

	public void setGeneralLedgerMaster(GeneralLedgerMaster generalLedgerMaster) {
		this.generalLedgerMaster = generalLedgerMaster;
	}

}
