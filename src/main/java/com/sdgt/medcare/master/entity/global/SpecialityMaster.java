package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.GeneralLedgerMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)

  public class SpecialityMaster extends BaseMaster {

	@Column(name = "is_surgical_code")
	private char isSurgicalCode;

	@Column(name="is_clinical_speciality")
	private char isClinicalSpeciality;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialityMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ModalityMaster> listModalityMaster;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GeneralLedgerMaster_id")
	GeneralLedgerMaster generalLedgerMaster;


	public char getIsClinicalSpeciality() {
		return isClinicalSpeciality;
	}

	public void setIsClinicalSpeciality(char isClinicalSpeciality) {
		this.isClinicalSpeciality = (isClinicalSpeciality == '\u0000') ? 'A' : isClinicalSpeciality;
	}



	public char getIsSurgicalCode() {
		return isSurgicalCode;
	}

	public void setIsSurgicalCode(char isSurgicalCode) {
		this.isSurgicalCode = (isSurgicalCode == '\u0000') ? 'A' : isSurgicalCode;
	}



	public List<ModalityMaster> getListModalityMaster() {
		return listModalityMaster;
	}

	public void setListModalityMaster(List<ModalityMaster> listModalityMaster) {
		this.listModalityMaster = listModalityMaster;
	}

	public GeneralLedgerMaster getGeneralLedgerMaster() {
		return generalLedgerMaster;
	}

	public void setGeneralLedgerMaster(GeneralLedgerMaster generalLedgerMaster) {
		this.generalLedgerMaster = generalLedgerMaster;
	}


}
