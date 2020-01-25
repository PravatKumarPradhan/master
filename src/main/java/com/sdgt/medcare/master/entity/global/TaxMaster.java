package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "m_tax")
public class TaxMaster extends BaseMaster {

	private float percentage = 0;
	
	/*@OneToMany(fetch = FetchType.LAZY)
	private List<ServiceUnitMaster> listServiceUnitMaster;
*/
	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	/*public List<ServiceUnitMaster> getListServiceUnitMaster() {
		return listServiceUnitMaster;
	}

	public void setListServiceUnitMaster(List<ServiceUnitMaster> listServiceUnitMaster) {
		this.listServiceUnitMaster = listServiceUnitMaster;
	}*/

}
