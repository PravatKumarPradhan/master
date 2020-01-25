package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="m_modality",schema="public")

public class ModalityMaster extends BaseMaster {

	
		
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EquipmentMaster_id")
	private EquipmentMaster equipmentMaster;*/
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SpecialityMaster_id")
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SubSpecialityMaster_id")
	private SubSpecialityMaster subSpecialityMaster;

	/*public EquipmentMaster getEquipmentMaster() {
		return equipmentMaster;
	}

	public void setEquipmentMaster(EquipmentMaster equipmentMaster) {
		this.equipmentMaster = equipmentMaster;
	}
*/
	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public SubSpecialityMaster getSubSpecialityMaster() {
		return subSpecialityMaster;
	}

	public void setSubSpecialityMaster(SubSpecialityMaster subSpecialityMaster) {
		this.subSpecialityMaster = subSpecialityMaster;
	}
	

	
	
	
}